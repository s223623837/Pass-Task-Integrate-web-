package web.service;

import org.junit.Assert;
import org.junit.Test;

public class LoginServiceBackendTest {

    @Test
    public void testLoginSuccess() {
        // Test for successful login
        String username = "ahsan";
        String password = "ahsan_pass";
        String dob = "1990-01-01"; // Valid date of birth

        boolean isAuthenticated = LoginService.login(username, password, dob);

        Assert.assertTrue(isAuthenticated);
    }

    @Test
    public void testLoginFailureInvalidCredentials() {
        // Test with invalid credentials
        String username = "invaliduser";
        String password = "invalidpass";
        String dob = "1990-12-31"; // Valid date of birth

        boolean isAuthenticated = LoginService.login(username, password, dob);

        Assert.assertFalse(isAuthenticated);
    }

    @Test
    public void testLoginFailureInvalidDob() {
        // Test with valid credentials but invalid date of birth
        String username = "validuser";
        String password = "validpass";
        String dob = "2005-01-01"; // Invalid date of birth

        boolean isAuthenticated = LoginService.login(username, password, dob);

        Assert.assertFalse(isAuthenticated);
    }

    @Test
    public void testLoginFailureEmptyCredentials() {
        // Test with empty username and password
        String username = "";
        String password = "";
        String dob = "2000-01-01"; // Valid date of birth

        boolean isAuthenticated = LoginService.login(username, password, dob);

        Assert.assertFalse(isAuthenticated);
    }

    @Test
    public void testLoginFailureSpecialCharacters() {
        // Test with special characters in username and password
        String username = "user@special";
        String password = "!@#$%^&*";
        String dob = "2000-01-01"; // Valid date of birth

        boolean isAuthenticated = LoginService.login(username, password, dob);

        Assert.assertFalse(isAuthenticated);
    }

    @Test
    public void testLoginFailureNonExistingUser() {
        // Test with valid credentials but non-existing user
        String username = "nonexistinguser";
        String password = "password";
        String dob = "1995-05-15"; // Valid date of birth

        boolean isAuthenticated = LoginService.login(username, password, dob);

        Assert.assertFalse(isAuthenticated);
    }

    @Test
    public void testLoginFailureAccountLocked() {
        // Test with valid credentials but account is locked
        String username = "lockeduser";
        String password = "lockedpass";
        String dob = "1980-08-10"; // Valid date of birth

        boolean isAuthenticated = LoginService.login(username, password, dob);

        Assert.assertFalse(isAuthenticated);
    }

    // Add more test cases to cover additional scenarios...
}

