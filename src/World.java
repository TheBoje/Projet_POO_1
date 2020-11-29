import Crossings.Crossing;
import Crossings.Door;
import Personnages.Personnage;
import Personnages.Player;
import Tiles.Direction;
import Tiles.Tile;

import java.util.ArrayList;
import java.util.Random;

public class World
{
	/***********************************ATTRIBUTES***********************************/

	private ArrayList<Tile> tiles;

	/***********************************CONSTRUCTOR***********************************/

	public World(int size)
	{
		// Methode temporaire pour créer un monde de [size] cases, avec case [i] reliée à case [i+1]
		Random rn = new Random();
		this.tiles = new ArrayList<>();
		for (int i = 0; i < size; i++)
		{
			tiles.add(new Tile());
		}
		Tile beforeTile = null;
		Direction beforeDirection = null;
		for (int i = 0; i < tiles.size() - 1; i++)
		{
			Direction newDir = Direction.intToDirection(rn.nextInt(3));
			Tile newTile = new Tile(new ArrayList<>(), new ArrayList<>(), new Tile[4], new Crossing[4]);
			if (i != 0)
			{
				//beforeTile.setNearbyTile(newTile, new Door(true), );
			}


			beforeTile = newTile;
			beforeDirection = newDir;
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
		Player player = new Player(this.tiles.get(0), new ArrayList<>(0), "Good Player", new ArrayList<>(0));
		this.tiles.get(0).addPersonnage(player);
	}


	// TODO REDO ME
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