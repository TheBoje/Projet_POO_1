package Game;

import Crossings.ClosedCrossing;
import Crossings.Crossing;
import Interpreteur.Interpreteur;
import Items.Item;
import Personnages.Personnage;
import Personnages.Player;
import Tiles.Direction;
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

	}

	public void talk()
	{

	}

	public void use()
	{

	}

	public void take(int index) throws InputError
	{
		if (index >= 0 && index < this.player.getItems().size())
		{
			this.player.take(this.player.getTile().getItem(index));
		}
		else
		{
			throw new InputError();
		}

	}

	public void nextTurn()
	{
		try
		{
			interpreteur.read();
		} catch (Exception e)
		{
			System.out.format("Error: %s\n", e.getClass().getSimpleName()); // TODO Exception handler?
			this.nextTurn();
		}
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

	public void getUse() // FIXME NEEDS TO GET item.usage() (string)
	{
		List<Item> items = player.getItems();

		for (int i = 0; i < items.size(); i++)
		{
			System.out.format("\t\t[%d] %s\n", i, items.get(i).getName());
		}
	}

	public void getPersonnagesOnTile()
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
					System.out.format("\t\t[%d] %s\n", i, this.player.getTile().getPersonnage(i).getName());
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
				System.out.format("\t\t[%d] %s\n", i, items.get(i).getName());
			}
		}
	}

	public void getInventory()
	{

	}

	/***********************************SETTERS***********************************/
	/***********************************DISPLAY***********************************/
	public void printWorld()
	{
		this.world.print();
	}


}