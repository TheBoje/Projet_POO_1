import Crossings.Crossing;
import Items.Item;
import Personnages.Personnage;
import Personnages.Player;
import Tiles.Direction;

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
		this.world = (World)Serializer.fromJSON("poggerino");
		//this.world = new World(3, 4);
		//Serializer.toJSON(new Food("poggerino", 10, 99), "nope");
		this.interpreteur = new Interpreteur(this);
		try
		{
			this.player = world.getPlayer();
		} catch (Exception e)
		{
			System.out.println("Can't find the player");
		}
		//Serializer.toJSON(this.world, "poggerino");

	}

	/***********************************METHODS***********************************/

	public void go(String direction) throws Exception
	{
		this.world.movePlayer(this.player, Direction.stringToDir(direction));
	}

	public void talk(String to)
	{
		System.out.format("The player is asked to talk to [%s]\n", to);
		// TODO - implement GameManager.talk
		//throw new UnsupportedOperationException();
	}

	public void use(String arg) // TODO fix index out of bounds
	{
		Item item = this.player.getItem(Integer.parseInt(arg));
		//this.player.use(item);
	}

	public void use(String arg1, String arg2)
	{
		// TODO me
	}

	public void take(String index) // TODO fix index out of bounds
	{
		Item temp = this.world.getTile(this.player.getTileID()).getItem(Integer.parseInt(index));
		this.world.getTile(this.player.getTileID()).take(temp);
		this.player.take(temp);
	}

	public void nextTurn()
	{
		try
		{
			interpreteur.read();
		} catch (Exception e)
		{
			System.out.format("Error: %s\n", e.getMessage());
			this.nextTurn();
		}
	}

	/***********************************GETTERS***********************************/
	public void getDirection()
	{
		Crossing[] playerCrossings = this.world.getTile(this.player.getTileID()).getCrossings();
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

	public void getUse()
	{
		List<Item> items = player.getItems();

		for (int i = 0; i < items.size(); i++)
		{
			System.out.format("\t\t[%d] %s\n", i, items.get(i).getName());
		}
	}

	public void getItemsOnTile()
	{
		List<Item> items = this.world.getTile(this.player.getTileID()).getItems();
		if (items.size() == 0)
		{
			System.out.format("\t\tTile is empty\n");
		}
		else
		{
			for (int i = 0; i < items.size(); i++)
			{
				System.out.format("\t\t[%d] %s\n", i, items.get(i).getName());
			}
		}
	}

	public void getTalk()
	{
		if (this.world.getTile(this.player.getTileID()).getPersonnages().size() == 1)
		{
			System.out.format("\tNo character to talk to\n");
		}
		else
		{
			for (int i = 0; i < this.world.getTile(this.player.getTileID()).getPersonnages().size(); i++)
			{
				Personnage p = this.world.getTile(this.player.getTileID()).getPersonnage(i);
				if (!(p instanceof Player))
				{
					System.out.format("\t\t[%d] %s\n", i, this.world.getTile(this.player.getTileID()).getPersonnage(i).getName());
				}
			}
		}
	}

	/***********************************SETTERS***********************************/
	/***********************************DISPLAY***********************************/
	public void printWorld()
	{
		this.world.print();
	}


}