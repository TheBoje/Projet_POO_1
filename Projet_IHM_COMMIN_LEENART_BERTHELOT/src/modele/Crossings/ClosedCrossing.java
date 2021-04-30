package modele.Crossings;

public class ClosedCrossing extends Exception
{
	@Override
	public String getMessage()
	{
		return "This crossing is closed, try to open it. Checkout \"help open\" and list doors.";
	}
}
