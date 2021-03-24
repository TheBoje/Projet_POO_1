package modele.Personnages;

public class NoSpeechAvailable extends Exception
{
	@Override
	public String getMessage()
	{
		return "This character doesn't have any speech available.\n";
	}
}
