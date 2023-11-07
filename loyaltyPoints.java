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

public class loyaltyPoints extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException{
		
		Connection connection = null;
		
		String username = request.getParameter("username");
		String addPoints = request.getParameter("Add points");
		String spendPoints = request.getParameter("spend points");
		
		int spend;
		//int add;
		
		//add = Integer.parseInt(addPoints);
		spend = Integer.parseInt(spendPoints);
		
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/loyaltyPoints?serverTimezone=UTC","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}


		Statement checkPoints = null;
		try {
			checkPoints = connection.createStatement();
				 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
				if (addPoints != null && !addPoints.isEmpty()) 
				{ 
					int add = Integer.parseInt(addPoints);
					
					
					add = add + 100; 
					
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<html><body>" + username + "You now have " + add + "you have "
							+ "added" + request.getParameter(addPoints)+ " points </body></html>");
				}
				
				if (spendPoints != null)
				{
					spend = 100 - spend;
					
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<html><body>" + username + "You now have " + spend + "you have "
							+ "spent" + request.getParameter(spendPoints)+ " points </body></html>");
				}
				
				
				if (spend == 0)
				{
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<html><body>" + username + "You have spent all of your points. To spend more you "
							+ "must add more </body></html>");
				}

}

}