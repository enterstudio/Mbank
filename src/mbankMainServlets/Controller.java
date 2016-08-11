package mbankMainServlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilSite;

/*
 * All Of the request's goes threw here
 * the controller servlet does neccesary 
 * stuff and dispaches to the viewManager Servlet
 * 
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Controller() 
	{
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String command = (String)request.getParameter("command");
		
		
		if(command.equals("deposit") || command.equals("withdraw") || command.equals("transactionHappend"))
		{
			//Get Session
			HttpSession session = request.getSession(false);

			//Get Account Info From Util Class:
			UtilSite.getAccountInfo(request, session);
			
		}
		
		getServletContext().getRequestDispatcher("/viewManager").forward(request, response);
	}

}
