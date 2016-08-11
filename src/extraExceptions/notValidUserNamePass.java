package extraExceptions;

@SuppressWarnings("serial")
public class notValidUserNamePass extends Exception
{
	public notValidUserNamePass(String message)
	{
		super(message);
	}
}
