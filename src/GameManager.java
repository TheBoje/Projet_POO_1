import Crossings.Crossing;
import Personnages.Player;
import Tiles.Direction;

import java.util.ArrayList;

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

	public void go(String direction) throws Exception
	{
		System.out.format("The player is asked to go to [%s]\n", direction.toUpperCase());
		this.world.movePlayer(this.player, Direction.stringToDir(direction));
	}

	public void talk(String to)
	{
		System.out.format("The player is asked to talk to [%s]\n", to);
		// TODO - implement GameManager.talk
		//throw new UnsupportedOperationException();
	}

	public void nextTurn()
	{
		try
		{
			interpreteur.read();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/***********************************GETTERS***********************************/
	public void getDirections()
	{
		Crossing[] playerCrossings = this.player.getTile().getCrossings();
		for (int i = 0; i < playerCrossings.length; i++)
		{
			if (playerCrossings[i] != null)
			{
				System.out.format("\t[%s] %s \n", Direction.intToDirection(i).toString() , playerCrossings[i].getClass().getSimpleName());
			}
		}
	}

	/***********************************SETTERS***********************************/

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	/***********************************DISPLAY***********************************/
	public void printWorld()
	{
		this.world.print();
	}


}