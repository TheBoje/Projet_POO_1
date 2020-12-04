import java.util.Scanner;

public class Interpreteur
{

	/***********************************ATTRIBUTES***********************************/

	private final GameManager gameManager;
	private final Scanner scanner;

	/***********************************CONSTRUCTOR***********************************/


	public Interpreteur(GameManager gameManager)
	{
		this.scanner = new Scanner(System.in);
		this.gameManager = gameManager;
	}

	/***********************************METHODS***********************************/

	public void read() throws Exception
	{
		System.out.print("> ");
		String input = scanner.nextLine().toLowerCase();
		String[] argv = input.split(" ");
		int argc = argv.length;
		switch (argv[0])
		{
			case "go":
				switch (argc)
				{
					case 1 -> gameManager.getDirection();
					default -> gameManager.go(argv[1]);
				}
				break;
			case "use":
				switch (argc)
				{
					case 1 -> gameManager.getUse();
					case 2 -> gameManager.use(argv[1]);
					case 3 -> gameManager.use(argv[2]); // TODO cas de l'arme ?
				}
				break;
			case "take":
				switch (argc)
				{
					case 1 -> gameManager.getItemsOnTile();
					case 2 -> gameManager.take(argv[2]);
				}
				break;
			case "talk":
				gameManager.talk(argv[1]);
				break;
			case "help":
			case "?":
				if (argv.length >= 2)
				{
					this.help(argv[1]);
				}
				else
				{
					this.help();
				}
				break;
			case "debug":
				gameManager.printWorld();
				break;
			default:
				throw new Exception("Wrong input interpreteur");
		}
	}

	public void help()
	{
		System.out.println("a really cool help thingy there "); // TODO Me !
	}

	public void help(String arg)
	{
		// TODO Me !
		// Il faut appeler la methode .help() de l'object appelé
		// Ajouter la méthode .help() pour tous les object
	}

	/***********************************GETTERS***********************************/
	/***********************************SETTERS***********************************/
	/***********************************DISPLAY***********************************/

}