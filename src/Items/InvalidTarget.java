package Items;

public class InvalidTarget extends Exception
{
	@Override
	public String getMessage()
	{
		return "Ivalid target, use a weapon on someone";//TODO Me
	}
}
