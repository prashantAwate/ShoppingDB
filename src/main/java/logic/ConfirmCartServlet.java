package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;


@WebServlet("/confirmCart")
public class ConfirmCartServlet extends HttpServlet {
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
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("loggedinuser");
		 
		String uid=u.getUid();
				//out.print(uid);
		
		java.sql.Timestamp ts=new java.sql.Timestamp(new java.util.Date().getTime());
		//out.print(ts);
		
		HttpSession session2=request.getSession();
		float tprice=(Float)session2.getAttribute("tprice");
		//out.print(tprice);
		
		
		try {
			PreparedStatement ps=con.prepareStatement("insert into shopping(user_id,shoppingDate,totalprice) values(?,?,?)");
			ps.setString(1, uid);
			ps.setTimestamp(2, ts);
			ps.setFloat(3, tprice);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		out.print("<br/><p>Order is Confirmed</p></br>");
		out.print("<p>You will be emailed at "+u.getEmail()+"</p></br>");
		out.print("<p>You will receive msg on "+u.getContact()+"</p></br>");
		
		out.print("<a href='logout'>LOGOUT</a>");
		
		
		
	}

}
