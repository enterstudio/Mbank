package Deposits;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Actions.ClientAction;
import Beans.ClientBean;
import Beans.DepositsBean;
import Exceptions.CouldNotEstablishConnection;
import Exceptions.NoDepositForThatClientException;

//This Servlet will make a list of the the client Deposit 
//and will set them on the request so that the jsp page will be
//able to simply show them


@WebServlet("/viewDeposits")
public class viewDeposits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public viewDeposits() 
    {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		try 
		{
    		//Taking The clientAction From The Session
    		HttpSession session = request.getSession(false);
    		ClientAction clientAction = (ClientAction) session.getAttribute("clientAction");
    		
    		ClientBean clientBean = (ClientBean) session.getAttribute("clientBean");
    		
    		
    		//Making the list for the the jsp View
			List<DepositsBean> depositsList = clientAction.viewClientDeposits(clientBean.getClient_id());
			request.setAttribute("depositList", depositsList);

		}
		catch (SQLException | CouldNotEstablishConnection | NoDepositForThatClientException e)
		{
			request.setAttribute("message", e.getMessage());
	    	getServletContext().getRequestDispatcher("/jspPages/viewDeposits.jsp").forward(request, response);
	    	e.printStackTrace();
			
		} 

    	getServletContext().getRequestDispatcher("/jspPages/viewDeposits.jsp").forward(request, response);
	}

}
