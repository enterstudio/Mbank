package viewDetails;

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
import Beans.ActivityBean;
import Beans.ClientBean;
import Exceptions.ActivityNotFoundException;
import Exceptions.CouldNotEstablishConnection;

//Simple View Servlet : 
//Putting All the neccasary Attributes On The Request 
//for The JSP Pages will get an easy showing...

@WebServlet("/viewActivities")
public class viewActivities extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public viewActivities() 
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
			List<ActivityBean> activityList = clientAction.viewClientActivities(clientBean.getClient_id());
			request.setAttribute("activityList", activityList);

		}
		catch (SQLException | CouldNotEstablishConnection | ActivityNotFoundException e)
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		 	getServletContext().getRequestDispatcher("/jspPages/viewActivitiesPage.jsp").forward(request, response);
		} 

    	
    	getServletContext().getRequestDispatcher("/jspPages/viewActivitiesPage.jsp").forward(request, response);
	}

}
