package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

@WebServlet("/home")
public class HomeClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps=con.prepareStatement("select * from category");
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				//out.println("<a href='products?cid="+rs.getInt(1)+"'>"+rs.getString(2)+"</a></br>");
				out.println("<a href='GetProduct.jsp?cid="+rs.getInt(1)+"'>"+rs.getString(2)+"</a></br>");
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
