package Game;

public class InputError extends Exception
{
	@Override
	public String getMessage()
	{
		return "Your input isn't correct. Checkout \"help <order>\" for more information.\n";
	}
}
