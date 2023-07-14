package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int spid=Integer.parseInt(request.getParameter("selectedPid"));
		
		HttpSession session=request.getSession();
		List<Integer> products=(List<Integer>)session.getAttribute("cart");
		if(products==null)
		{
			products=new ArrayList<>();
		}
		products.add(spid);
		session.setAttribute("cart", products);
		
		out.println("</br>Selected product::"+spid+"added to cart");
		out.println("</br>There are"+products.size()+"products in cart<br/>");
		
		out.println("</br><a href='viewCart.jsp'>View Cart<a>");
		out.print("</br><a href='home'>Go to Select more products<a>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
