package Crossings;

public class CantOpenCrossing extends Exception
{
	@Override
	public String getMessage()
	{
		return "You can't open this crossing. You maybe need an item for this.\n";
	}
}
