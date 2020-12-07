package Interpreteur;

public enum Order
{
	DEBUG("debug", "display debug world data"),
	TAKE("take", "take <item_index> (ex: take 8)\n\ttake item from the ground of the tile and put it in your inventory"),
	HELP("help", "display all order's help message"),
	QUIT("quit", "quit the game"),
	SAVE("save", "NOT IMPLEMENTED YET"),
	LOAD("load", "NOT IMPLEMENTED YET"),
	INFO("info", "NOT IMPLEMENTED YET"),
	TALK("talk", "talk <char_index> (ex: talk 2)\n\ttalk to the selected character"),
	OPEN("open", "open <crossing_dir> (ex: open N)\n\ttry to open the selected crossing"),
	LIST("list", """
			list <type> (ex: list inventory)
				display all entity for selected type such as :
				"crossing" for the crossing
				"inventory" for all the items in your inventory
				"crossing" for all the crossings of the tile
				"inventory" for all the items in your inventory
				"characters" for all the present characters
				"items" for all the items on the tile
				"usage" <item_index> to get the usage of the selected item"""),
	USE("use", "use <item_index> <character_index> (ex: use 1 0)\n\tuse the selected item onto the selected character"),
	GO("go", "go <dir_char> (ex: go N)\n\tgo to the selected direction "); // TODO Win action

	private final String stringValue;
	private final String helpMessage;

	Order(String s, String helpMessage)
	{
		this.stringValue = s;
		this.helpMessage = helpMessage;
	}

	public String getString()
	{
		return this.stringValue;
	}

	public String getHelpMessage()
	{
		return this.helpMessage;
	}
}
