package Game;

import Items.Item;

import java.util.Random;

public class Main
{
	public static void main(String[] args)
	{
		Item item = Item.generateRandomItem(new Random());
		System.out.println(item.toString());

		GameManager gameManager = new GameManager();
		gameManager.printWorld();
		while (true)
		{
			gameManager.nextTurn();
		}
	}
}
