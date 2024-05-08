package web.service;

/**
 * Business logic to handle login functions.
 * 
 * @author Ahsan.
 */
public class LoginService {

	/**
	 * Static method returns true for successful login, false otherwise.
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean login(String username, String password, String dob) {
		// Match a fixed user name and password.
		//
		if ("ahsan".equals(username) && "ahsan_pass".equals(password)) {
			  // Additional validation using dob (date of birth) parameter
			  if ("1990-01-01".equals(dob)) { // Replace with your validation logic
                return true; // Authentication successful
			  }
		}
		return false;
	}
	
	
}
