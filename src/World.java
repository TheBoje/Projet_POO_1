import Crossings.Crossing;
import Crossings.Door;
import Personnages.Personnage;
import Personnages.Player;
import Tiles.Tile;

import java.util.ArrayList;
import java.util.Arrays;

public class World
{

	private ArrayList<Tile> tiles;

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
			Crossing crossing = new Door(new ArrayList<Tile>(
					Arrays.asList(tiles.get(i), tiles.get(i + 1))));
			tiles.get(i).addCrossing(crossing);
			tiles.get(i + 1).addCrossing(crossing);
		}
	}

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