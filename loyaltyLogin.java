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

public class loyaltyLogin extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException{
		
		Connection connection = null;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/loyaltyPoints?serverTimezone=UTC","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}


		Statement checkLogin = null;
		try {
			checkLogin = connection.createStatement();
				 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
				ResultSet rs = null;
				try {
					rs = checkLogin.executeQuery("select * from user");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
			try {
			boolean isUser = false;
				while(rs.next()) {
					try {
						if(rs.getString(1).equalsIgnoreCase(username)&&rs.getString(2).equalsIgnoreCase(password)) {	
							isUser = true;
							break;
						}								
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

}
				if (isUser) { // if user login exists in database redirect loyaltyPoints page
					response.sendRedirect("loyaltyPoints.html");
				}
				else { // otherwise redirect user back to register page
					response.sendRedirect("index.html");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


}

}