package modele.Interpreteur;

public class UnknownOrder extends Exception
{
	@Override
	public String getMessage()
	{
		return "We don't understand the action you want to perform. Checkout \"help\".\n";
	}
}
