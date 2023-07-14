package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Result;


@WebServlet("/viewCart")
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession();
		List<Integer> sps=(List<Integer>) session.getAttribute("cart");
		if(sps==null)
		{
			out.print("Your cart is Empty");
		}
		else
		{
			out.print("<table border=1>");
			PreparedStatement ps;
			ResultSet rs;
			
			try {
				ps=con.prepareStatement("select * from product where p_id=?");
				int cnt=0;
				float tprice=0;
				
				for(int n:sps)
				{
					ps.setInt(1, n);
					rs=ps.executeQuery();
					if(rs.next())
					{
						out.print("<tr>");
						out.print("<td>"+(++cnt)+"</td>");
						out.print("<td>"+rs.getString(2)+"</td>");
						out.print("<td>"+rs.getString(4)+"</td>");
						//out.print("<td><a href='delete'>DELETE</a></td>");
						out.print("</tr>");
						tprice+=Float.parseFloat(rs.getString(4));
					}
				}
				
				session.setAttribute("tprice",tprice);
				out.print("<tr>");
				out.print("<td colspan='2'>Total Price</td>");
				out.print("<td>"+tprice+"</td>");
				out.print("</tr>");
				out.print("</table>");
				
				out.print("<a href='confirmCart'>Confirm Cart</a>");
				out.print("</br><a href='home'>Go Back to Categories</a>");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
