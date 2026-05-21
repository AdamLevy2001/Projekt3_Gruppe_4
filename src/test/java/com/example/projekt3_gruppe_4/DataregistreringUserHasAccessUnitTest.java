package com.example.projekt3_gruppe_4;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.model.UserCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataregistreringUserHasAccessUnitTest {
    @Test
    public void hasAccessHappyFlow(){
        // Precondition
        User user = UserCreator.createUser(1,"data", "1234", "dataregistrering"); // opretter en dataregistrerings user

        // Execution
        boolean result = user.hasAccess("dataregistrering/opret-lejeaftale"); // kalder metoden hasAccess og finder ud af om brugeren har adgang til den side

        // Postcondition
        assertTrue(result); // verificere at brugeren faktisk har adgang til den side
    }

    @Test
    public void hasAccessExceptionFlow(){
        // Precondition
        User user = UserCreator.createUser(1,"data", "1234", "dataregistrering"); // opretter en dataregistrerings user

        // Execution
        boolean result = user.hasAccess("forretningsudvikler/dashboard"); // kalder metoden hasAccess og finder ud af om brugeren har adgang til den side

        // Postcondition
        assertFalse(result); // verificere at brugeren ikke har adgang til den side
    }
}
