package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;


@WebServlet("/logincheck")
public class LogincheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uid=request.getParameter("uid");
		String pswd=request.getParameter("pswd");
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
		
		try {
			
			ps=con.prepareStatement("select * from users where u_id=? and password=?");
			ps.setString(1, uid);
			ps.setString(2, pswd);
			rs=ps.executeQuery();
			
			
			if(rs.next())
			{
				
				Cookie [] arr=request.getCookies();
				if(arr !=null)
				{
					for(Cookie c:arr)
					{
						if(c.getName().equals("error"))
						{
							c.setMaxAge(0);
							response.addCookie(c);
						}
					}
				}
				
				User u=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				HttpSession session=request.getSession();
				session.setAttribute("loggedinuser", u);
				
				
				if(rs.getString(1).equals(uid) && rs.getString(2).equals(pswd))
				{
					RequestDispatcher rd=request.getRequestDispatcher("/home");
					rd.forward(request, response);
					
					
				}
			}
			else
			{
				
				Cookie cookie=new Cookie("error","Wrong_uid/pswd");
				response.addCookie(cookie);
				response.sendRedirect("login.jsp");
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
