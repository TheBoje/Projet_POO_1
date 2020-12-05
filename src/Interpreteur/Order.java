package Interpreteur;

public enum Order
{
	DEBUG("debug"),
	TAKE("take"),
	HELP("help"),
	QUIT("quit"),
	SAVE("save"),
	LOAD("load"),
	INFO("info"),
	TALK("talk"),
	OPEN("open"),
	LIST("list"),
	USE("use"),
	GO("go");

	private final String stringValue;

	Order(String s)
	{
		this.stringValue = s;
	}

	public String getString()
	{
		return this.stringValue;
	}

}
