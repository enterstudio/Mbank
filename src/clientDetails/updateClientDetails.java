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
import Exceptions.CouldNotEstablishConnection;
import Exceptions.NoRowsEffectedException;


@WebServlet("/updateClientDetails")
public class updateClientDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public updateClientDetails()
	{
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		try 
		{
			//getting from the session relevent values
			HttpSession session = request.getSession(false);
			ClientAction clientAction = (ClientAction) session.getAttribute("clientAction");
			ClientBean client = (ClientBean) session.getAttribute("clientBean");

			
			//getting User Inputs From the request
			//------------------------------------------------------------
			String newAddress = request.getParameter("newAddress");
			String newEmail = request.getParameter("newEmail");
			String newPhone = request.getParameter("newPhone");
			
			//checking checkBox inputs
			String updateAll = request.getParameter("updateAll");
			String PhoneCheckBox = request.getParameter("phoneCheckBox");
			String emailCheckBox = request.getParameter("emailCheckBox");
			String addressCheckBox = request.getParameter("addressCheckBox");

			if((updateAll!=null) && updateAll.equals("on"))
			{
				client.setAddress(newAddress);
				client.setEmail(newEmail);
				client.setPhone(newPhone);
			}
			else if((PhoneCheckBox!=null) &&PhoneCheckBox.equals("on"))
			{
				client.setPhone(newPhone);
			}
			else if((emailCheckBox!=null) &&emailCheckBox.equals("on"))
			{
				client.setEmail(newEmail);
			}
			else if((addressCheckBox!=null) &&addressCheckBox.equals("on"))
			{
				client.setAddress(newAddress);
			}


			//Updating the DB
			clientAction.updateClientDetails(client);
			
			request.setAttribute("message", "Update Was Executed Succesfully");
			
			getServletContext().getRequestDispatcher("/viewClientDet").forward(request, response);

		}
		catch (SQLException | CouldNotEstablishConnection
				| NoRowsEffectedException e)
		{	
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/viewClientDet").forward(request, response);
		}

	}

}
