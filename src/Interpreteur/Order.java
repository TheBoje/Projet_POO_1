package Interpreteur;

public enum Order
{
	// Représente l'ordre de l'input du player.
	// Pour ajouter un ordre, il faut ajouter un element ici,
	// en spécifiant les champs <stringInput> (message que doit rentrer  le player pour
	// que l'ordre soit recu, et <helpMessage> (message envoyé au player pour
	// décrire l'ordre).

	DEBUG("debug", "display debug world data"),
	TAKE("take", "take <item_index> (ex: take 8)\n\ttake item from the ground of the tile and put it in your inventory"),
	HELP("help", "display all order's help message"),
	QUIT("quit", "quit the game"),
	SAVE("save", "Not implemented yet"),
	LOAD("load", "Not implemented yet"),
	INFO("info", "Not implemented yet"),
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
	GO("go", "go <dir_char> (ex: go N)\n\tgo to the selected direction"),
	TRADE("trade", "Not implemented yet"),
	LOOK("look", "\"look\" to get a description of the tile you are on"),
	PLAYER("player", "\"player\" to get the status of your player");

	private final String stringValue;
	private final String helpMessage;

	Order(String stringInput, String helpMessage)
	{
		this.stringValue = stringInput;
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
