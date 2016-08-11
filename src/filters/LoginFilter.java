package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "/LoginFilter" , urlPatterns = { "/jspPages/*","/Controller" })
public class LoginFilter implements Filter {

    public LoginFilter() 
    {
    }
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		String command = request.getParameter("command");
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		if(command==null)
		{
			command="";
		}
		
		
		//If Session Excepired redirect to login page!
		if (session==null &&( command.equals("home") 
				|| command.equals("logout") 
				|| command.equals("createNewDeposit") 
				|| command.equals("viewClientDeposits")	
				|| command.equals("viewClientProfile") 
				|| command.equals("viewClientActivities")
				|| command.equals("depositToAccount") 
				|| command.equals("withdrawFromAccount")
				|| command.equals("viewUserAccount") 
				|| command.equals("viewSystemProp")
				|| command.equals("createNewDeposit") 
				|| command.equals("viewClientDeposits")
				|| command.equals("makeDeposit")
				|| command.equals("createDeposit") 
				|| command.equals("transactionHappend")
				|| command.equals("deposit") 
				|| command.equals("withdraw")
				|| command.equals("updateClientDetails")
				|| command.equals("preOpenDeposit") 
				|| command.equals("transactionHappend")
				|| command.equals("deposit") 
				|| command.equals("withdraw")
				|| command.equals("")))
		{	
			String nextPage="/Login.jsp";

			request.getServletContext().getRequestDispatcher(nextPage).forward(request, response);
			
			return;
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException
	{}
	public void destroy() 
	{}
}
