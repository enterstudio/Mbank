package mbankMainServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This Servlet Will Kill The Session And Will Dispach
 * To The Login Page
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Logout() 
    {
        super();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	HttpSession session = request.getSession(false);
    	
    	 session.invalidate();
    	 
			String nextPage="/Login.jsp";
			getServletContext().getRequestDispatcher(nextPage).forward(request, response);
    }

}
