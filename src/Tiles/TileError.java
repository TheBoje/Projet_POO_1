package Tiles;

public class TileError extends Exception
{
	@Override
	public String getMessage()
	{
		return "Something went wrong creating tiles.\n";
	}
}
