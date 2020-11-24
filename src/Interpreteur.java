import java.util.Scanner;

public class Interpreteur {

	private GameManager gameManager;
	private Scanner scanner;

	public void read() throws Exception
	{
		System.out.print("Action : ");
		String input = scanner.nextLine().toLowerCase();
		String[] input_split = input.split(" ");
		switch (input_split[0])
		{
			case "go" -> gameManager.go(input_split[1]);
			case "talk" -> gameManager.talk(input_split[1]);
			default -> throw new Exception("wrong input interpreteur");
		}
	}

	public Interpreteur(GameManager gameManager) {
		this.scanner = new Scanner(System.in);
		this.gameManager = gameManager;
	}

}