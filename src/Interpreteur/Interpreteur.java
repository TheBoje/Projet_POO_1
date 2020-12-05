package Interpreteur;

import Crossings.*;
import Game.*;
import Tiles.*;

import java.util.Arrays;
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
		System.out.format("> ");
		String input = this.scanner.nextLine();
		String[] split_input = input.split(" ");
		String order = split_input[0];
		String[] args;
		if (split_input.length > 1)
		{
			args = Arrays.copyOfRange(split_input, 1, split_input.length);
		}
		else
		{
			args = null;
		}
		Request request = new Request(order, args);
		this.exec(request);
	}

	public void exec(Request request) throws InputError, UnknownDirection, InsufficientArguments
	{
		switch (request.getOrder())
		{
			case DEBUG -> {
				this.gameManager.printWorld();
			}
			case TAKE -> {
				if (request.argCount() == 0)
				{
					throw new InsufficientArguments();
				}
				else
				{
					this.gameManager.take(request.getIntArg(0));
				}
			}
			case HELP -> {
			}
			case QUIT -> {
			}
			case SAVE -> {
			}
			case LOAD -> {
			}
			case INFO -> {
			}
			case TALK -> {
			}
			case OPEN -> {
			}
			case LIST -> {
				if (request.argCount() == 0)
				{
					throw new InsufficientArguments();
				}
				else {
					switch (request.getArg(0).toLowerCase())
					{
						case "tile","doors","door","crossing","dir", "direction", "directions" -> this.gameManager.getDirection();
						case "inv", "inventory" -> this.gameManager.getInventory();
						case "talk", "characters","character","chars","char" -> this.gameManager.getPersonnagesOnTile();
						case "search", "items","item" -> this.gameManager.getItemsOnTile();
						case "uses", "use", "usages", "usage" -> this.gameManager.getUse();
						default -> throw new InputError();
					}
				}
			}
			case USE -> {

			}
			case GO -> {
				Direction dir = Direction.stringToDirection(request.getArg(0));
				try
				{
					this.gameManager.go(dir);
				} catch (ClosedCrossing e)
				{
					System.out.format("You can't go there, the door is closed. Try OPEN command\n");
				}
			}
		}
	}


	public void help()
	{
	}

	public void help(String arg)
	{
	}

	public void go() throws Exception
	{
	}

	public void use() throws Exception
	{
	}

	public void take()
	{
	}

	public void talk()
	{
	}

}