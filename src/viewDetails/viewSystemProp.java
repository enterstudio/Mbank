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
import Beans.PropertiesBean;
import Exceptions.CouldNotEstablishConnection;
import Exceptions.SystemPropertiesNotFoundException;

//Simple View Servlet : 
//Putting All the neccasary Attributes On The Request 
//for The JSP Pages will get an easy showing...
@WebServlet("/viewSystemProp")
public class viewSystemProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public viewSystemProp() 
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
    		
    		//Making the list for the the jsp View
			List<PropertiesBean> propList = clientAction.viewSystemProperty();
			request.setAttribute("propList", propList);

		}
		catch (SQLException | CouldNotEstablishConnection
				| SystemPropertiesNotFoundException e)
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
	    	getServletContext().getRequestDispatcher("/jspPages/viewSystemProp.jsp").forward(request, response);
		}
    	
    	getServletContext().getRequestDispatcher("/jspPages/viewSystemProp.jsp").forward(request, response);
    }

}
