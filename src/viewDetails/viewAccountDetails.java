package viewDetails;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Actions.ClientAction;
import Beans.AccountBean;
import Beans.ClientBean;
import Exceptions.AccountNotFoundException;
import Exceptions.ClientNotFoundException;
import Exceptions.CouldNotEstablishConnection;
import Exceptions.NoRowsEffectedException;
import Util.UtilSite;

//Simple View Servlet : 
//Putting All the neccasary Attributes On The Request 
//for The JSP Pages will get an easy showing...


@WebServlet("/viewAccountDetails")
public class viewAccountDetails extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       

    public viewAccountDetails()
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
		String userName = (String) session.getAttribute("userName"); 

		ClientBean clientBean = clientAction.viewClientDetails(userName);
		
		AccountBean account = UtilSite.viewAccountDetailsWithClientId(clientBean.getClient_id());
		
		request.setAttribute("clientId", account.getClient_id());
		request.setAttribute("accountId", account.getAccount_id());
		request.setAttribute("balance", account.getBalance());
		request.setAttribute("creditLimit", account.getCredit_limit());
		request.setAttribute("comment", account.getComment());
		
		
    	getServletContext().getRequestDispatcher("/jspPages/viewAccountPage.jsp").forward(request, response);
		}
		catch (SQLException | ClientNotFoundException
				| CouldNotEstablishConnection | NoRowsEffectedException
				| AccountNotFoundException e) 
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/jspPages/viewAccountPage.jsp").forward(request, response);
		}

		
	}
	
}
