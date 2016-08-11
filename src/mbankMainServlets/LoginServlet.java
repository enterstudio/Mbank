package mbankMainServlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Actions.ClientAction;
import Beans.AccountBean;
import Beans.ClientBean;
import Beans.PropertiesBean;
import Exceptions.AccountNotFoundException;
import Exceptions.ClientNotFoundException;
import Exceptions.CouldNotEstablishConnection;
import Exceptions.NoRowsEffectedException;
import Exceptions.SystemPropertiesNotFoundException;
import Exceptions.UserNameOrPasswordNotAvailableException;
import Menu.Mbank;
import Util.UtilSite;


//Login servlet will as named do the following:
//1.Try to make a login Based On The userName + Password
//2.set relevent Attributes on The Session 
//3.set a LastVisit Coockie 
//4.dispaches to the Mbank HomePage.jsp

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private ClientAction clientAction;

	
	public LoginServlet() 
	{
		super();
	}

	@Override
	public void init()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		HttpSession clientDetailsSession = request.getSession();
		String username = request.getParameter ("username");
		String password = request.getParameter("password");

		try 
		{
			//Try To make a login into the system
			//-----------------------------------------------------------------------------------------------------------
			clientAction = Mbank.getInstance().loginClient(username, password);
			
			
			//Taking the clientBean & AccountBean From The DB for Further usage 
			//througt the website...
			//-----------------------------------------------------------------------------------------------------------
			ClientBean clientBean = clientAction.viewClientDetails(username);
			AccountBean accountBean = UtilSite.viewAccountDetailsWithClientId(clientBean.getClient_id());
			
			//Set Both ClientAction & His user name On the session (user name because i need it thrgout the program)
			//We set the password also for future Validation
			//Also Putting the ClientBean That Is logged on the System
			//and offcurse the accountBean for showing his details on screen
			//-----------------------------------------------------------------------------------------------------------
			clientDetailsSession.setAttribute("clientAction",clientAction);
			clientDetailsSession.setAttribute("userName", username);
			clientDetailsSession.setAttribute("password", password);
			clientDetailsSession.setAttribute("clientBean", clientBean);
			clientDetailsSession.setAttribute("accountBean", accountBean);
			
			
			//Adding Last Visit Cookies(LastVisitCookies is A private method in this servlet)
			//-----------------------------------------------------------------------------------------------------------
			String lastVisit = LastVisitCookies(request, response);
			
			if(lastVisit==null)
			{
				lastVisit="Welcome " +username+" This Is Your First Time At Our Website";
			
			}
			clientDetailsSession.setAttribute("lastVisited", lastVisit);
			
//			
//			Because to website holds a header page which all of the
//			pages uses include for him i've added a small basic Jquery 
//			function to the head page(from the folder wrraperPage - file= MainHeader.jsp)
//			there will be here not best Practise (to say the least) and this section is 
//			basiclly holds a code which already exsits in The view System Prop Servlet
//			All of this is for exmine the Jquery libary and no more 
//			in the real world this section would be handled in a very much smarter way
    		
			//Making the list for the the jsp View
			List<PropertiesBean> propList = clientAction.viewSystemProperty();
			clientDetailsSession.setAttribute("propList", propList);
			
			
			//dispatcing to homePage JSP
			String nextPage="/homePage.jsp";
			getServletContext().getRequestDispatcher(nextPage).forward(request, response);
		} 
		catch (SQLException | ClientNotFoundException 
				| CouldNotEstablishConnection | NoRowsEffectedException
				| AccountNotFoundException | UserNameOrPasswordNotAvailableException
				| SystemPropertiesNotFoundException e) 
		{
			request.setAttribute("message",e.getMessage());
			e.printStackTrace();
			//dispatcing to homePage JSP
			String nextPage="/homePage.jsp";
			getServletContext().getRequestDispatcher(nextPage).forward(request, response);
		} 

	

	}

	
	private String LastVisitCookies(HttpServletRequest request,HttpServletResponse response)
	{
		String LastVisit = null;
		
		Cookie[] cookiesArr = request.getCookies();
		
		if(cookiesArr!=null)
		{
			for (int i = 0; i < cookiesArr.length; i++) 
			{
				if(cookiesArr[i].getName().equals("lastVisited"))
				{
					LastVisit=cookiesArr[i].getValue();
					request.setAttribute("lastVisited", cookiesArr[i].getValue());
				}
			}
		}
		String date = new Date().toString();
		Cookie cookie = new Cookie("lastVisited", date);
		
		cookie.setMaxAge(7*24*60*60);
		
		//Adding Cookie To The Response
		response.addCookie(cookie);
		
		return LastVisit;
	}

		
}
