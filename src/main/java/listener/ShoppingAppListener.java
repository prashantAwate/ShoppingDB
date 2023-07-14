package listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ShoppingAppListener implements ServletContextListener {

	Connection con;
	
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	
    	try {
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	String driver=sce.getServletContext().getInitParameter("driverclass");
    	String jdbcurl=sce.getServletContext().getInitParameter("jdbcurl");
    	String user=sce.getServletContext().getInitParameter("user");
    	String pswd=sce.getServletContext().getInitParameter("password");
    	
    	try {
    		Class.forName(driver);
    		con=DriverManager.getConnection(jdbcurl,user,pswd);
    		
    		sce.getServletContext().setAttribute("jdbccon", con);
    		System.out.println("con is set as context level attribute");
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    }
	
}
