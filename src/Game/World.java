package Game;

import Crossings.Crossing;
import Crossings.Door;
import Items.Clothes;
import Items.Food;
import Items.Item;
import Items.RangeWeapon;
import Personnages.Personnage;
import Personnages.Player;
import Tiles.Direction;
import Tiles.Tile;
import Tiles.TileError;
import Tiles.UnknownDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class World
{
	/***********************************ATTRIBUTES***********************************/

	private final Map<Integer, Tile> tilesMap;

	/***********************************CONSTRUCTOR***********************************/

	public World(int tilesAmount, int crossingsAmount)
	{
		Random rn = new Random();
		this.tilesMap = Tile.generateTiles(tilesAmount, rn);

		for (int i = 0; i < crossingsAmount; i++)
		{
			Direction randomDir = null;
			Direction invertedRandomDir = null;
			try
			{
				randomDir = Direction.intToDirection(rn.nextInt(3));
				invertedRandomDir = Direction.invert(randomDir);
				int randomIndex1 = rn.nextInt(tilesAmount);
				int randomIndex2;
				do
				{
					randomIndex2 = rn.nextInt(tilesAmount);
				}
				while (randomIndex2 == randomIndex1);

				Tile tileTemp = this.tilesMap.get(randomIndex1);
				tileTemp.setNearbyTile(randomIndex2, new Door(rn.nextBoolean()), randomDir);
				Tile tileTempInverted = this.tilesMap.get(randomIndex2);
				tileTempInverted.setNearbyTile(randomIndex1, new Door(rn.nextBoolean()), invertedRandomDir);
			} catch (UnknownDirection | TileError e)
			{
				// This is never gonna happen, although we need to catch it.
				System.out.format("World generation error: wrong Direction input \n");
			}
		}
		this.createPlayer();
	}

	/***********************************METHODS***********************************/

	public void createPlayer()
	{
		Random rn = new Random();
		Player player = new Player(this.tilesMap.get(0), new ArrayList<>(0), "Player", new ArrayList<>(0));
		player.addItem(Item.generateRandomItem(rn));
		player.addItem(Item.generateRandomItem(rn));
		player.addItem(Item.generateRandomItem(rn));
		this.tilesMap.get(0).addPersonnage(player);
	}

	/***********************************GETTERS***********************************/

	public Player getPlayer() throws Exception
	{
		for (Map.Entry<Integer, Tile> entry : this.tilesMap.entrySet())
		{
			Tile tile = entry.getValue();
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

	public Tile getTile(int index)
	{
		return this.tilesMap.get(index);
	}

	/***********************************SETTERS***********************************/
	/***********************************DISPLAY***********************************/

	public void print()
	{
		System.out.format("WORLD : \n");
		System.out.format("\tSize : %d\n", this.tilesMap.size());
		for (Map.Entry<Integer, Tile> entry : this.tilesMap.entrySet())
		{
			entry.getValue().print();
		}
	}
}