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


@WebServlet("/products")
public class ProductServlet extends HttpServlet {
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
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps=con.prepareStatement("select * from product where cat_id=?");
			String cid=request.getParameter("cid");
			
			ps.setString(1, cid);
			
			rs=ps.executeQuery();
			
			out.print("<form action='addToCart' method='get'>");
			out.println("Select Product::");
			out.println("<select name='selectedPid'>"); 
			while(rs.next())
			{	
				out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			out.println("</select>");
			out.print("</br><input type='submit' value='Add to Cart'>");
			out.print("</form>");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
