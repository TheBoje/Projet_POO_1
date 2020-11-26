import Crossings.Crossing;
import Crossings.Door;
import Items.Item;
import Personnages.Personnage;
import Personnages.Player;
import Tiles.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class World
{
	/***********************************ATTRIBUTES***********************************/

	private ArrayList<Tile> tiles;

	/***********************************CONSTRUCTOR***********************************/

	public World(int size)
	{
		// Methode temporaire pour créer un monde de [size] cases, avec case [i] reliée à case [i+1]
		this.tiles = new ArrayList<>();
		for (int i = 0; i < size; i++)
		{
			tiles.add(new Tile());
		}
		for (int i = 0; i < tiles.size() - 1; i++)
		{

			Crossing crossing = new Door(new ArrayList<>(Arrays.asList(tiles.get(i), tiles.get(i + 1))), true);
			tiles.get(i).addCrossing(crossing);
			tiles.get(i + 1).addCrossing(crossing);
		}
		this.createPlayer();
	}

	/***********************************METHODS***********************************/

	public Player getPlayer() throws Exception
	{
		for (Tile tile : tiles)
		{
			for (Personnage personnage : tile.getPersonnages())
			{
				if (personnage instanceof Player)
				{
					return (Player) personnage;
				}
			}
		}
		throw new Exception("Can't find player");
	}

	public void createPlayer()
	{
		Player player = new Player(this.tiles.get(0),new ArrayList<>(0), "Good Player", new ArrayList<>(0));
		this.tiles.get(0).addPersonnage(player);
	}

	public void movePlayer(Player player, Crossing crossing) throws Exception
	{
		if (crossing.isOpen())
		{
			Tile targetTile = crossing.getOtherWay(player.getTile());
			Tile oldTile = player.getTile();
			player.setTile(targetTile);
			oldTile.remotePersonnage(player);
			targetTile.addPersonnage(player);
		}
		else
		{
			System.out.println("Crossing is closed");
		}
	}

	/***********************************GETTERS***********************************/
	/***********************************SETTERS***********************************/
	/***********************************DISPLAY***********************************/

	public void print()
	{
		System.out.format("WORLD : \n");
		System.out.format("\tSize : %d\n", this.tiles.size());
		for (Tile tile : tiles)
		{
			tile.print();
		}
	}
}