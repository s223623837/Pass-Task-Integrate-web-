package web.service;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class RegistrationBackendTest {

    @Test
    public void testRegistrationService() {
        // Test data
        String fName = "John";
        String lName = "Doe";
        String email = "johndoe@example.com";
        String dob = "1990-01-01";

        // Invoke the registration service method directly
        boolean registrationSuccessful = RegistrationService.register(fName, lName, email, dob);

        // Assert that registration was successful
        assertTrue(registrationSuccessful);
    }
}
