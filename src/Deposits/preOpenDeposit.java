package Deposits;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Actions.ClientAction;
import Exceptions.AccountNotFoundException;
import Exceptions.ClientNotFoundException;
import Exceptions.CouldNotEstablishConnection;
import Exceptions.CouldNotFoundTheRequastedProperties;
import Exceptions.DepositNotFoundException;
import Exceptions.NoDepositForThatClientException;
import Exceptions.NoRowsEffectedException;
import Exceptions.PreOpenDepositException;
import Exceptions.SystemPropertiesNotFoundException;
import Exceptions.WithdrawException;

//This Servlet will simply get from the deposits.jsp 's page 
//the requested deposit that the user wishes to open...
//and will updated the DB...

@WebServlet("/preOpenDeposit")
public class preOpenDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public preOpenDeposit() 
    {
        super();
    }


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		
		try
		{
		HttpSession session = request.getSession(false);
	    ClientAction clientAction = (ClientAction) session.getAttribute("clientAction");
		String UserName = (String) session.getAttribute("userName");
		
		//Getting from the request the relevent deposit for action
		String strDepositId = (String)request.getParameter("depositId");
		
		int depositId=Integer.parseInt(strDepositId);
		
		//updating the DB
		clientAction.preOpenDeposit(UserName, depositId);
		
		request.setAttribute("message", "Succes In Pre Opening Deposit Id "+depositId);
		
		getServletContext().getRequestDispatcher("/viewDeposits").forward(request, response);
		}
		catch (SQLException | NoDepositForThatClientException |
				ClientNotFoundException | 
				CouldNotEstablishConnection | 
				DepositNotFoundException | 
				CouldNotFoundTheRequastedProperties |
				SystemPropertiesNotFoundException | 
				NoRowsEffectedException | 
				AccountNotFoundException | 
				WithdrawException | 
				PreOpenDepositException e) 
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/viewDeposits").forward(request, response);
		}
		

	
	}
}
