package modele.Items;

public class InvalidTarget extends Exception
{
	@Override
	public String getMessage()
	{
		return "You are not targeting anyone. Use \"list character\" to display all possible targets.\n";
	}
}
