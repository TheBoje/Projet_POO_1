package Game;

import Crossings.CantOpenCrossing;
import Crossings.ClosedCrossing;
import Crossings.Crossing;
import Interpreteur.Interpreteur;
import Interpreteur.Order;
import Items.InvalidTarget;
import Items.Item;
import Personnages.NoSpeechAvailable;
import Personnages.Personnage;
import Personnages.Player;
import Tiles.Direction;
import Tiles.Tile;
import Tiles.UnknownDirection;

import java.util.List;

public class GameManager
{

	/***********************************ATTRIBUTES***********************************/

	private final World world;
	private final Interpreteur interpreteur;
	private Player player;

	/***********************************CONSTRUCTOR***********************************/

	public GameManager()
	{
		this.world = new World(5, 8);
		this.interpreteur = new Interpreteur(this);
		try
		{
			this.player = world.getPlayer();
		} catch (Exception e)
		{
			System.out.println("Can't find the player");
		}
	}

	/***********************************METHODS***********************************/

	public void go(Direction dir) throws ClosedCrossing
	{
		Tile actualTile = this.player.getTile();
		Tile targetTile = this.world.getTile(actualTile.getNextTileID(dir));
		if (!actualTile.getCrossing(dir).isOpen())
		{
			throw new ClosedCrossing();
		}
		else
		{
			actualTile.remotePersonnage(this.player);
			targetTile.addPersonnage(this.player);
			this.player.setTile(targetTile);
		}
	}

	public void talk(int index) throws InputError, NoSpeechAvailable
	{
		if (index >= 0 && index < this.player.getTile().getPersonnages().size())
		{
			String talk_res = this.player.getTile().getPersonnage(index).getRandomSpeech();
			System.out.format("[%s]: %s\n", this.player.getTile().getPersonnage(index).getName(), talk_res);
		}
		else
		{
			throw new InputError();
		}
	}

	public void use(String[] args) throws InputError, InvalidTarget // FIXME
	{
		int item_index = Integer.parseInt(args[0]);
		int character_index = Integer.parseInt(args[1]);
		if (character_index >= 0 && character_index < this.player.getTile().getPersonnages().size())
		{
			this.player.use(this.player.getItem(item_index), this.player.getTile().getPersonnage(character_index));
		}
		else
		{
			throw new InputError();
		}
	}

	public void take(int index) throws InputError
	{
		if (index >= 0 && index < this.player.getItems().size())
		{
			Item toTake = this.player.getTile().getItem(index);
			this.player.take(toTake);
			this.player.getTile().take(toTake);
		}
		else
		{
			throw new InputError();
		}

	}

	public boolean nextTurn()
	{
		if (player.isAlive())
		{
			try
			{
				interpreteur.read();
			} catch (Exception e)
			{
				System.out.format("Error: %s\n", e.getClass().getSimpleName());
				System.out.format("%s\n", e.getMessage());
				this.nextTurn();
			}
			return true;
		}
		else
		{
			System.out.format("player died\n");
			return false;
		}
	}

	public void open(Direction dir) throws CantOpenCrossing
	{
		this.player.getTile().getCrossing(dir).tryOpen(this.player.getItems());
	}

	public void quit()
	{
		System.out.println("Closing app");
		System.exit(1);
	}

	public void help()
	{
		System.out.format("LIST OF COMMANDS:\n");
		Order[] orders = Order.values();
		for (Order o : orders)
		{
			help(o);
		}
	}

	public void help(Order order)
	{
		System.out.format("[%s] %s\n", order.getString(), order.getHelpMessage());
	}

	/***********************************GETTERS***********************************/
	public void getDirection() throws UnknownDirection
	{
		Crossing[] playerCrossings = this.player.getTile().getCrossings();
		for (int i = 0; i < playerCrossings.length; i++)
		{
			if (playerCrossings[i] != null)
			{
				System.out.format("\t[%s] %s - %s\n",
						Direction.intToDirection(i).toString(),
						playerCrossings[i].getClass().getSimpleName(),
						playerCrossings[i].isOpen() ? "open" : "close");
			}
		}
	}

	public void getUsage()
	{
		List<Item> items = player.getItems();

		for (int i = 0; i < items.size(); i++)
		{
			System.out.format("\t[%d] %s\n", i, items.get(i).getUsage());
		}
	}

	public void getPersonnagesOnTile() // FIXME add player
	{
		if (this.player.getTile().getPersonnages().size() == 1)
		{
			System.out.format("\tNo character on this tile\n");
		}
		else
		{
			for (int i = 0; i < this.player.getTile().getPersonnages().size(); i++)
			{
				Personnage p = this.player.getTile().getPersonnage(i);
				if (!(p instanceof Player))
				{
					System.out.format("\t[%d] %s\n", i, this.player.getTile().getPersonnage(i).getName());
				}
			}
		}
	}

	public void getItemsOnTile()
	{
		List<Item> items = this.player.getTile().getItems();
		if (items.size() == 0)
		{
			System.out.format("\tNo item on this tile\n");
		}
		else
		{
			for (int i = 0; i < items.size(); i++)
			{
				System.out.format("\t[%d] %s\n", i, items.get(i).getName());
			}
		}
	}

	public void getInventory()
	{
		List<Item> items = this.player.getItems();
		if (items.size() == 0)
		{
			System.out.format("Your inventory is empty\n");
		}
		else
		{
			for (int i = 0; i < items.size(); i++)
			{
				System.out.format("\t[%d] %s\n", i, items.get(i).getName());
			}
		}
	}


	/***********************************SETTERS***********************************/
	/***********************************DISPLAY***********************************/
	public void printWorld()
	{
		this.world.print();
	}

	public void printPlayer()
	{
		this.player.printDebug();
	}

}