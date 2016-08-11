package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Actions.ClientAction;
import Beans.AccountBean;
import Beans.ClientBean;
import Exceptions.AccountNotFoundException;
import Exceptions.ClientNotFoundException;
import Exceptions.CouldNotEstablishConnection;
import Exceptions.NoRowsEffectedException;
import MySqlConnection.TestConnection;

//Basic Java Class That helps The System


public class UtilSite 
{
	//While using the system , the Db is being updated many times
	// And this Simple Method Basiclly updates on the request the New Data
	public static void getAccountInfo(HttpServletRequest request,HttpSession session)
	{
		try
		{
		ClientAction clientAction = (ClientAction) request.getSession(false).getAttribute("clientAction");
		String userName = (String) request.getSession(false).getAttribute("userName");
		
		//Sets The Acount Details On request For Page
		ClientBean client = clientAction.viewClientDetails(userName);
	
		//Gets the Account's Data From DB
		AccountBean account = viewAccountDetailsWithClientId(client.getClient_id());
		
		request.setAttribute("accountId", account.getAccount_id());
		request.setAttribute("accountBalance",account.getBalance());
		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (ClientNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (CouldNotEstablishConnection e) 
		{
			e.printStackTrace();
		}
		catch (NoRowsEffectedException e)
		{
			e.printStackTrace();
		} 
		catch (AccountNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static AccountBean viewAccountDetailsWithClientId(int clientId) throws SQLException, NoRowsEffectedException,
	CouldNotEstablishConnection, AccountNotFoundException 
	{
		final String SELECT_FROM_ACCOUNT_SQL_STATEMENT= ("select * from accounts where client_id=?");

		PreparedStatement statement=null;
		Connection connection = null;
		ResultSet resultSet=null;
		AccountBean accountBean=null;

		try{
			connection=TestConnection.getConnection();
			if(connection == null)
			{
				throw new CouldNotEstablishConnection("No Success In Establishing Connection");
			}
			statement=connection.prepareStatement(SELECT_FROM_ACCOUNT_SQL_STATEMENT);
			statement.setDouble(1,(clientId));
			resultSet=statement.executeQuery();
			if(!resultSet.next())
			{
				throw new AccountNotFoundException("No Account With Given Id");
			}
			else
			{
				int account_id=resultSet.getInt("account_id");
				int client_id=resultSet.getInt("client_id");
				double balance=resultSet.getDouble("balance");
				double credit_limit=resultSet.getDouble("credit_limit");
				String comment=resultSet.getString("comment");

				accountBean=new AccountBean(account_id, client_id, balance, credit_limit, comment);

				
			}
		}
		finally
		{
			statement.close();
			connection.close();
		}
		return accountBean;
	}
	
	public static Date getDateFromString(String strDate) throws ParseException
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = format.parse(strDate);
		return date;
	}
	
	
	
}
