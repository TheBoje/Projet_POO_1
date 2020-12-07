package Interpreteur;

import Crossings.CantOpenCrossing;
import Crossings.ClosedCrossing;
import Game.GameManager;
import Game.InputError;
import Items.InvalidTarget;
import Personnages.NoSpeechAvailable;
import Tiles.Direction;
import Tiles.UnknownDirection;

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


	public void read() throws UnknownOrder, CantOpenCrossing, InputError, UnknownDirection, InsufficientArguments, NoSpeechAvailable, InvalidTarget
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

	public void exec(Request request) throws InputError, UnknownDirection, InsufficientArguments, CantOpenCrossing, NoSpeechAvailable, InvalidTarget, UnknownOrder
	{
		switch (request.getOrder())
		{
			case DEBUG -> this.gameManager.printWorld();
			case TAKE -> {
				if (request.argCount() < 1)
				{
					this.gameManager.help(Order.TAKE);
					throw new InsufficientArguments();
				}
				else
				{
					this.gameManager.take(request.getIntArg(0));
				}
			}
			case HELP -> {
				if (request.argCount() < 1)
				{
					this.gameManager.help();
				}
				else
				{
					this.gameManager.help(request.convertStringToOrder(request.getArg(0)));
				}
			}

			case QUIT -> this.gameManager.quit();
			case LOAD, SAVE, INFO -> this.gameManager.help(request.getOrder());
			case TALK -> {
				if (request.argCount() < 1)
				{
					this.gameManager.help(Order.TALK);
					throw new InsufficientArguments();
				}
				else
				{
					this.gameManager.talk(request.getIntArg(0));
				}

			}
			case OPEN -> {
				if (request.argCount() < 1)
				{
					this.gameManager.help(Order.OPEN);
					this.gameManager.getDirection();
				}
				else
				{
					this.gameManager.open(Direction.stringToDirection(request.getArg(0)));
				}
			}
			case LIST -> {
				if (request.argCount() < 1)
				{
					this.gameManager.help(Order.LIST);
				}
				else
				{
					switch (request.getArg(0).toLowerCase())
					{
						case "tile", "doors", "door", "crossing", "crossings", "dir", "direction", "directions" -> this.gameManager.getDirection();
						case "inv", "inventory" -> this.gameManager.getInventory();
						case "talk", "characters", "character", "chars", "char" -> this.gameManager.getPersonnagesOnTile();
						case "search", "items", "item" -> this.gameManager.getItemsOnTile();
						case "uses", "use", "usages", "usage" -> this.gameManager.getUsage();
						case "player", "data" -> this.gameManager.printPlayer();
						default -> throw new InputError();
					}
				}
			}
			case USE -> {
				if (request.argCount() < 2)
				{
					this.gameManager.help(Order.USE);
					throw new InsufficientArguments();
				}
				else
				{
					this.gameManager.use(request.getArgs());
				}
			}
			case GO -> {
				if (request.argCount() < 1)
				{
					this.gameManager.help(Order.GO);
					this.gameManager.getDirection();
				}
				else
				{
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
	}
}