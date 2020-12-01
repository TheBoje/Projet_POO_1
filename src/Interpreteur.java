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
		String[] input_split = input.split(" ");
		switch (input_split[0])
		{
			case "direction":
				gameManager.getDirections();
				break;
			case "go":
				gameManager.go(input_split[1]); // FIXME Crash si 1 seul argument
				break;
			case "talk":
				gameManager.talk(input_split[1]); // FIXME Crash si 1 seul argument
				break;
			case "help":
			case "?":
				if (input_split.length >= 2)
				{
					this.help(input_split[1]);
				}
				else
				{
					this.help();
				}
				break;

			default:
				throw new Exception("wrong input interpreteur");
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