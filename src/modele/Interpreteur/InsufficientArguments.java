package modele.Interpreteur;

public class InsufficientArguments extends Exception
{
	@Override
	public String getMessage()
	{
		return "You didn't provide enough arguments to your request. Checkout \"help <order>\" for more information.\n";
	}
}
