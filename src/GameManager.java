import Crossings.Crossing;
import Personnages.*;
import Tiles.Tile;

import java.util.ArrayList;

public class GameManager {

	private World world;
	private Interpreteur interpreteur;
	private Player player;

	public GameManager() {
		this.world = new World(4);
		this.interpreteur = new Interpreteur(this);
		try
		{
			this.player = world.getPlayer();
		} catch (Exception e)
		{
			System.out.println("Can't find player");
			//e.printStackTrace();
		}
	}

	public void printWorld()
	{
		this.world.print();
	}

	/**
	 * 
	 * @param direction
	 */
	public void go(String direction) {
		System.out.format("The player is asked to go to [%s]\n", direction);
		// TODO - implement GameManager.go
		//throw new UnsupportedOperationException();
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

	public void getDirections()
	{
		ArrayList<Crossing> playerCrossings = this.player.getTile().getCrossings();
		for (int i = 0; i < playerCrossings.size(); i++)
		{
			System.out.format("\t[%d] %s \n", i, playerCrossings.get(i).getClass().getSimpleName());
		}
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

}