package Tiles;

public class UnknownDirection extends Exception
{
	@Override
	public String getMessage()
	{
		return "The direction you asked doesn't exists. Use [N/E/S/W] to target the good crossing. Use \"list crossings\" to see all of them.\n";
	}
}
