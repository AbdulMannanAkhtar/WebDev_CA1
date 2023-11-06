import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loyaltyRegister extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException{
		
		Connection connection = null;
		try {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/loyaltyPoints?serverTimezone=UTC","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if(request.getParameter("password2").equals(request.getParameter("password1")));
			PreparedStatement createUser = connection.prepareStatement(
					"INSERT into user "
					+ "(username, password, points)" +" VALUES (?, ?, ?)");
					//pass in the values as parameters
				    createUser.setString(1, request.getParameter("username"));
				    createUser.setString(2,  request.getParameter("password"));
				    int points = Integer.parseInt(request.getParameter("points"));
				    createUser.setInt(3, points);
					int rowsUpdated = createUser.executeUpdate();
					createUser.close();
				
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		response.sendRedirect("login.html");
		
		if(request.getParameter("password2")!=(request.getParameter("password")));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>The password you entered does not match!</body></html>");
		//response.sendRedirect("register.html");
					
	} 
		
		
	

}