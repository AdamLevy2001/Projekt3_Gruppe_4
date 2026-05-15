package com.example.projekt3_gruppe_4;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.model.UserCreator;
import com.example.projekt3_gruppe_4.repository.UserRepository;
import com.example.projekt3_gruppe_4.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceLoginUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void loginHappyFlow() {
        // Preconditions
        String username = "demo"; // sætter et username til brugeren der logger ind
        String password = "demo"; // sætter et password til brugeren der logger ind
        String hashedPassword = passwordEncoder.encode(password); // hasher password med BCrypt
        User user = UserCreator.createUser(1, username, hashedPassword, "admin"); // laver et user objekt som det kom fra databasen
        given(userRepository.findUserByUsername(username)).willReturn(user); // mock returnerer user fra databasen (simuleret)

        // Execution
        User result = userService.login(username, password); // kører metoden login, med demo, demo

        // Postcondition
        assertEquals(username, result.getUsername()); // kigger på om vi har fået det rigtige username i result
    }

    @Test
    public void loginExceptionFlow(){
        // Preconditions
        String username = "demo"; // sætter et username til brugeren der logger ind
        String password = "demo"; // sætter et password til brugeren der logger ind
        String hashedPassword = passwordEncoder.encode(password); // hasher password med BCrypt
        User user = UserCreator.createUser(1, username, hashedPassword, "admin"); // laver et user objekt som det kom fra databasen
        given(userRepository.findUserByUsername(username)).willReturn(user); // mock returnerer user fra databasen (simuleret)

        // Execution
        try {
            userService.login(username, "dem0"); // kører metoden login, med demo, og dem0 som password som ikke er korrekt
        } catch (IllegalArgumentException e){
            // Postcondition
            assertEquals("Brugernavn eller adgangskode er forkert!", e.getMessage()); // verificere at vi får den exception besked som vi forventer
        }
    }
}
