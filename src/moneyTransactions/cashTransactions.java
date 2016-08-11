package moneyTransactions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import extraExceptions.notValidUserNamePass;
import Actions.ClientAction;
import Exceptions.AccountNotFoundException;
import Exceptions.ClientNotFoundException;
import Exceptions.CouldNotDetarmineDepositTypeException;
import Exceptions.CouldNotEstablishConnection;
import Exceptions.CouldNotFoundTheRequastedProperties;
import Exceptions.NoRowsEffectedException;
import Exceptions.SystemPropertiesNotFoundException;
import Exceptions.WithdrawException;
import Util.UtilSite;

//This Servlet Is for The Deposit/Withdraw 's Account Action
//I'm Using The Same Servlet For Both Because there is no need 
//for A seperated servlets. The User Simply puts The AmountOfTransaction that he wishes
//and in This Servlet i'm getting also a hidden jspId . A simple Check alows me to define
//if the request came from deposit or from withdraw and base on that i'm making the neccesary 
//action.

//NEED TO MAKE ANOTHER VALIDATION WHEN TRYNIG TO MAKE TRANSACTION


@WebServlet("/cashTransactions")
public class cashTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public cashTransactions()
    {
        super();
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	
		//Get Action (withdraw/deposit)
		//And Updating the DB
		String action = request.getParameter("jspId");
    	
		try 
		{ 
    	
    	ClientAction clientAction = (ClientAction) request.getSession(false).getAttribute("clientAction");
		String userName = (String) request.getSession(false).getAttribute("userName");
		
		String sumOfActionStr = request.getParameter("sumOfAction");
		
		double sumOfAction = Double.parseDouble(sumOfActionStr);
		
		//Extra Validation For Action
		String userNameVal = request.getParameter("userNameVal");
		String userPassVal = request.getParameter("passwordVal");
		String trueUserPassword = (String) request.getSession(false).getAttribute("password");
		
		 
		if(!(userNameVal.equals(userName) && userPassVal.equals(trueUserPassword)))
		{
			throw new notValidUserNamePass("Not A valid UserName/Password");
		}
	

		
		if(action.equals("withdraw"))
		{
		clientAction.withdrawFromAccount(userName, sumOfAction);	
		request.setAttribute("message", "Success in Withdrawing from Your Account");
		}
		else if(action.equals("deposit"))
		{
		clientAction.depositToAccount(userName, sumOfAction);
		request.setAttribute("message", "Success in Depositing to Your Account");
		}
		else if (action==null || action.equals(""))
		{
			throw new CouldNotDetarmineDepositTypeException("Unkown Jsp ID : Could not config the type of Action");
		}
		
		//Get Session
		HttpSession session = request.getSession(false);
		
		//Get Account Info From Util Class: Because I have Updated The Db
		//And i want to put the new Updated Details On the request
		UtilSite.getAccountInfo(request, session);
		
		getServletContext().getRequestDispatcher("/jspPages/confirmAction.jsp").forward(request, response);

	}
	catch (CouldNotDetarmineDepositTypeException | SQLException | ClientNotFoundException 
			| CouldNotFoundTheRequastedProperties | WithdrawException 
			| CouldNotEstablishConnection | NoRowsEffectedException 
			| AccountNotFoundException | SystemPropertiesNotFoundException e) 
	{
		request.setAttribute("message", e.getMessage());
		e.printStackTrace();
		getServletContext().getRequestDispatcher("/jspPages/confirmAction.jsp").forward(request, response);
	} 
	catch (notValidUserNamePass e) 
	{
		request.setAttribute("message", e.getMessage());
		e.printStackTrace();
		if(action.equals("deposit"))
		{
		getServletContext().getRequestDispatcher("/jspPages/depositPage.jsp").forward(request, response);
		}
		else if(action.equals("withdraw"))
		{
			getServletContext().getRequestDispatcher("/jspPages/withdraw.jsp").forward(request, response);
		}
	}
    	
    	
    	
    }


}
