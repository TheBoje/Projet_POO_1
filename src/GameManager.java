import Crossings.Crossing;
import Personnages.*;
import Tiles.Tile;

import java.util.ArrayList;

public class GameManager {

	/***********************************ATTRIBUTES***********************************/

	private World world;
	private Interpreteur interpreteur;
	private Player player;

	/***********************************CONSTRUCTOR***********************************/

	public GameManager() {
		this.world = new World(4);
		this.interpreteur = new Interpreteur(this);
		try
		{
			this.player = world.getPlayer();
		} catch (Exception e)
		{
			System.out.println("Can't find player");
		}
	}

	/***********************************METHODS***********************************/

	/**
	 *
	 * @param direction
	 */
	public void go(String direction) throws Exception
	{
		System.out.format("The player is asked to go to [%s]\n", direction);
		this.world.movePlayer(this.player, this.player.getTile().getCrossings().get(Integer.parseInt(direction)));
	}
	/**
	 *
	 * @param to
	 */
	public void talk(String to) {
		System.out.format("The player is asked to talk to [%s]\n", to);
		// TODO - implement GameManager.talk
		//throw new UnsupportedOperationException();
	}

	public void nextTurn() {
		try
		{
			interpreteur.read();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		// TODO - implement GameManager.nextTurn
		//throw new UnsupportedOperationException();
	}

	/***********************************GETTERS***********************************/
	public void getDirections()
	{
		ArrayList<Crossing> playerCrossings = this.player.getTile().getCrossings();
		for (int i = 0; i < playerCrossings.size(); i++)
		{
			System.out.format("\t[%d] %s \n", i, playerCrossings.get(i).getClass().getSimpleName());
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