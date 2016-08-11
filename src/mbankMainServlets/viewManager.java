package mbankMainServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * ------------------------------------------------------------------
 * Basicly a Navigation Servlet Wich only takes the Command from 
 * the request and dispatches to the relavent  Page
 * ------------------------------------------------------------------
 */

@WebServlet("/viewManager")
public class viewManager extends HttpServlet
{
	private static final long serialVersionUID = 1L;
  
    public viewManager() 
    {
        super();
    }

@Override
protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException 
{

	String command = request.getParameter("command");
	
	String nextPage=null;
	
	//HOME PAGE
	//================================================
	if(command.equals("home"))
	{
		nextPage="/homePage.jsp";
	}
	
	//Deposits
	//=======================================================
	
	else if(command.equals("createNewDeposit"))
	{
		nextPage="/jspPages/createNewDeposit.jsp";
	}
	
	//Wich means that the request came from creteDeposit.jsp
	//We dispatch to the servlet for further logic stuff...
	else if(command.equals("makeDeposit"))
	{
		nextPage="/createNewDeposit";
	}
	else if(command.equals("viewClientDeposits"))
	{
		nextPage="/viewDeposits";
	}
	else if (command.equals("preOpenDeposit"))
	{
		nextPage="/preOpenDeposit";
	}

	
	
	//Client Details
	//=========================================================
	
	else if(command.equals("viewClientProfile"))
	{
		nextPage="/viewClientDet";
	}
	else if(command.equals("updateClientDetails"))
	{
		nextPage="/updateClientDetails";
	}
	else if(command.equals("viewClientActivities"))
	{
		nextPage="/viewActivities";
	}

	
	
	//Account Actions
	//=======================================================
	else if(command.equals("depositToAccount"))
	{
		nextPage="/jspPages/depositPage.jsp";
	}
	else if(command.equals("withdrawFromAccount"))
	{
		nextPage="/jspPages/withdraw.jsp";
	}
	else if(command.equals("viewUserAccount"))
	{
		nextPage="/viewAccountDetails";
	}
	else if(command.equals("viewSystemProp"))
	{
		nextPage="/viewSystemProp";
	}
	else if(command.equals("transactionHappend"))
	{
		nextPage="/cashTransactions";
	}
	//Logout
	else if (command.equals("logout"))
	{
		nextPage="/Logout";
	}
	
	 getServletContext().getRequestDispatcher(nextPage).forward(request, response);


}

}
