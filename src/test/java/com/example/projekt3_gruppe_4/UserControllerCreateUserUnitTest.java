package com.example.projekt3_gruppe_4;

import com.example.projekt3_gruppe_4.controller.UserController;
import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.model.UserCreator;
import com.example.projekt3_gruppe_4.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserControllerCreateUserUnitTest {
    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @InjectMocks
    private UserController userController;

    @Test
    public void createUserHappyFlow() {
        // Preconditions
        String username = "demo"; // sætter et username til brugeren som logger ind
        String password = "demo"; // sætter et password til brugeren som logger ind
        String role = "admin"; // sætter rollen til admin til brugeren som logger ind
        User user = UserCreator.createUser(1, username, password, role); // opretter en admin bruger som simulerer den bruger som er logget ind
        given(session.getAttribute("loggedInUser")).willReturn(user); // simulerer at admin er logget ind i sessionen

        // Execution
        String result = userController.createUser("data", "data", "dataregistrering", model, session); // opretter en ny dataregistrerings bruger via controlleren

        // Postcondition
        assertEquals("redirect:/admin/opret-bruger?success=true", result); // verificere at der bliver returneret det som vi forventer
    }

    @Test
    public void createUserExceptionFlow(){
        // Precondition
        String username = "demo"; // sætter et username til brugeren som logger ind
        String password = "demo"; // sætter et password til brugeren som logger ind
        String role = "admin"; // sætter rollen til admin til brugeren som logger ind
        User user = UserCreator.createUser(1, username, password, role); // opretter en admin bruger som simulerer den bruger som er logget ind
        given(session.getAttribute("loggedInUser")).willReturn(user); // simulerer at admin er logget ind i sessionen

        String newUserUsername = ""; // sætter et tomt username til den nye bruger
        String newUserPassword = "data"; // sætter et password til den nye bruger
        String newUserRole = "dataregistrering"; // sætter en rolle til den nye bruger
        willThrow(new IllegalArgumentException("Brugernavn kan ikke være tomt!")).given(userService).registrerUser(newUserUsername, newUserPassword, newUserRole); // simulerer at userService kaster en exception ved oprettelse af den nye bruger

        // Execution
        String result = userController.createUser(newUserUsername, newUserPassword, newUserRole, model, session); // opretter en ny dataregistrerings bruger via controlleren

        // Postcondition
        assertEquals("opret-bruger", result); // verificere at der bliver returneret det som vi forventer
        verify(model).addAttribute("username", newUserUsername); // verificere at det nye username bliver sendt til frontend via model
        verify(model).addAttribute("password", newUserPassword); // verificere at det nye password bliver sendt til frontend via model
        verify(model).addAttribute("role", newUserRole); // verificere at den nye rolle bliver sendt til frontend via model
        verify(model).addAttribute("errorMessage", "Brugernavn kan ikke være tomt!"); // verificere at errorMessage bliver sendt til frontend via model
    }
}
