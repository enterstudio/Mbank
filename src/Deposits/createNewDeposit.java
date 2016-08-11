package Deposits;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Actions.ClientAction;
import Beans.ClientBean;
import Exceptions.ClientNotFoundException;
import Exceptions.CouldNotDetarmineDepositTypeException;
import Exceptions.CouldNotEstablishConnection;
import Exceptions.CouldNotFoundTheRequastedProperties;
import Exceptions.NoRowsEffectedException;
import Exceptions.SystemPropertiesNotFoundException;
import Util.UtilSite;

//This Servlet Will Create New Deposit For The User:
//It Will Get The relevent data from the request and Will Update The 
//Db

@WebServlet("/createNewDeposit")
public class createNewDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public createNewDeposit()
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
    	ClientBean clientBean = (ClientBean)session.getAttribute("clientBean");
    	
    	
    	//Getting From The Request User Inputs
    	double AmountOfDeposit = Double.parseDouble(request.getParameter("amountOfDeposit"));
    	
    	//getUserClosingDate Is A private Method That simply gets all
    	//the neccasary values from the request and returns a String of The
    	//closing Date The User chose
    	String strDate = getUserClosingDate(request);
    	
    	//Formating the String strDate To Date
    	Date closingDate = UtilSite.getDateFromString(strDate);
    	
    	//Updating The DB
    	clientAction.createNewDeposit(clientBean.getClient_name(),AmountOfDeposit,closingDate);
    	
    	request.setAttribute("message", "A new Deposit Was Made For You");
    	
    	 getServletContext().getRequestDispatcher("/viewDeposits").forward(request, response);
    	}
    	catch (ParseException | SQLException | ClientNotFoundException 
    			| CouldNotDetarmineDepositTypeException | CouldNotFoundTheRequastedProperties 
    			| CouldNotEstablishConnection | NoRowsEffectedException | SystemPropertiesNotFoundException e)
    	{
    		request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		 	getServletContext().getRequestDispatcher("/viewDeposits").forward(request, response);
		} 

    }
    
    
    private String getUserClosingDate(HttpServletRequest request)
    {
    	
    	String year = request.getParameter("year");	
    	String strMonth = request.getParameter("month");
    	String strDay = request.getParameter("day");

    	int day = Integer.parseInt(strDay);
    	int month = Integer.parseInt(strMonth);
    	String strDate;
    	    	
    	if(day<10)
    	{
    		strDay= "0"+strDay;
    	}
    	if(month<10)
    	{
    		strMonth="0"+strMonth;
    	}

    	strDate=strDay+"/"+strMonth+"/"+year;
    	
    	return strDate;
    	
    	
    }
}
