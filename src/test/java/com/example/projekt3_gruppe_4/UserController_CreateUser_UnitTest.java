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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserController_CreateUser_UnitTest {
    @Mock
    private UserService userService;

    @Mock
    Model model;

    @Mock
    HttpSession session;

    @InjectMocks
    private UserController userController;

@Test
public void createUserHappyFlow() {
    //Precondition
    String username = "demo";
    String password = "demo";
    String role = "admin";
    User user = UserCreator.createUser(1, username
    , password ,role);
    given(session.getAttribute("loggedInUser")).willReturn(user);

    //Execution
    String result = userController.createUser(username,password,role,model,session);


    //postcondition
    assertEquals("redirect:/admin/opret-bruger?success=true", result);
    }
}
