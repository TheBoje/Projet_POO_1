import java.util.Scanner;

public class Interpreteur {

	/***********************************ATTRIBUTES***********************************/

	private GameManager gameManager;
	private Scanner scanner;

	/***********************************CONSTRUCTOR***********************************/


	public Interpreteur(GameManager gameManager) {
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
			case "directions" -> gameManager.getDirections();
			case "go" -> gameManager.go(input_split[1]); // FIXME Crash si 1 seul argument
			case "talk" -> gameManager.talk(input_split[1]);

			default -> throw new Exception("wrong input interpreteur");
		}
	}

	/***********************************GETTERS***********************************/
	/***********************************SETTERS***********************************/
	/***********************************DISPLAY***********************************/

}