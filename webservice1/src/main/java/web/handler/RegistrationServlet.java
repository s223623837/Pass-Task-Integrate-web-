package web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.RegistrationService;


/**
 * HTTP end-point to handle registration request.
 */
public class RegistrationServlet  extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {	
		System.out.println("[RegistrationServlet] GET");
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {		
		System.out.println("[RegistrationServlet] POST");
		
		String fName = req.getParameter("fname");
		String lName = req.getParameter("lname");
		String email = req.getParameter("email");
		String dob = req.getParameter("dob");
		
		// Call registration business logic
		//
		boolean registrationSuccessful =RegistrationService.register(fName, lName, email, dob);
		
		// Writes a status ok message: just to test servlet functionality.
		//
		resp.setContentType("application/json");
//		resp.setStatus(HttpServletResponse.SC_OK);				
//		resp.getWriter().println("{\"status\": \"ok\"}");
		 PrintWriter writer = resp.getWriter();

	        if (registrationSuccessful) {
	            // Registration successful
	            writer.println("{ \"status\": \"ok\" }");
	            resp.setStatus(HttpServletResponse.SC_OK);
	        } else {
	            // Registration failed
	            writer.println("{ \"status\": \"error\" }");
	            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // or appropriate error status
	        }
		
	}


}
