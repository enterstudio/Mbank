package clientDetails;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Actions.ClientAction;
import Beans.ClientBean;
import Exceptions.ClientNotFoundException;
import Exceptions.CouldNotEstablishConnection;

//This Servlet will Take From the session the ClientAction And
//Will get A clientBean Using ClientAction offcurse and finnally will
//set on the request the clientBean For the jsp Page

@WebServlet("/viewClientDet")
public class viewClientDet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public viewClientDet() 
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

		ClientBean client;

		client = clientAction.viewClientDetails((String) session.getAttribute("userName"));

		request.setAttribute("clientBean", client);
	} 
	catch (SQLException | ClientNotFoundException | CouldNotEstablishConnection e) 
	{
		request.setAttribute("message", e.getMessage());
		e.printStackTrace();
		getServletContext().getRequestDispatcher("/jspPages/viewClientDetails.jsp").forward(request, response);
	}


	getServletContext().getRequestDispatcher("/jspPages/viewClientDetails.jsp").forward(request, response);
}

}
