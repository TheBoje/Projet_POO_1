package Game;

public class Main
{
	public static void main(String[] args)
	{
		GameManager gameManager = new GameManager();
		gameManager.initGame();
		boolean continueGame = true;
		while (continueGame)
		{
			continueGame = gameManager.nextTurn();
		}
	}
	// TODO README.MD
}
