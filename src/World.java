import Crossings.Crossing;
import Crossings.Door;
import Items.Clothes;
import Items.Food;
import Personnages.Personnage;
import Personnages.Player;
import Tiles.Direction;
import Tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class World
{
	/***********************************ATTRIBUTES***********************************/

	private Map<Integer, Tile> tilesMap;

	/***********************************CONSTRUCTOR***********************************/

	public World(int tilesAmount, int crossingsAmount)
	{
		Random rn = new Random();
		this.tilesMap = new HashMap<>();
		for (int i = 0; i < tilesAmount; i++)
		{
			this.tilesMap.put(i, new Tile());
		}
		for (int i = 0; i < crossingsAmount; i++)
		{
			Direction randomDir = Direction.intToDirection(rn.nextInt(3));
			Direction invertedRandomDir = Direction.invert(randomDir);
			int randomIndex1 = rn.nextInt(tilesAmount);
			int randomIndex2;
			do
			{
				randomIndex2 = rn.nextInt(tilesAmount);
			}
			while (randomIndex2 == randomIndex1);

			Tile tileTemp = this.tilesMap.get(randomIndex1);
			tileTemp.setNearbyTile(randomIndex2, new Door(), randomDir);
			Tile tileTempInverted = this.tilesMap.get(randomIndex2);
			tileTempInverted.setNearbyTile(randomIndex1, new Door(), invertedRandomDir);
		}
		this.createPlayer();
	}

	/***********************************METHODS***********************************/

	public void createPlayer()
	{
		Player player = new Player(0, new ArrayList<>(0), "Good Player", new ArrayList<>(0));
		player.addItem(new Clothes("Manto", 10));
		player.addItem(new Food("Doritos", 2));
		this.tilesMap.get(0).addPersonnage(player);
	}


	public void movePlayer(Player player, Direction direction) throws Exception
	{
		int playerTileID = player.getTileID();
		Tile playerTile = this.getTile(playerTileID);
		if (playerTile.getCrossing(direction) != null)
		{
			Crossing playerTileCrossing = playerTile.getCrossing(direction);

			if (playerTileCrossing.isOpen())
			{
				int newTileID = playerTile.getNextTileID(direction);
				Tile nextTile = this.tilesMap.get(newTileID);

				player.setTile(newTileID);
				playerTile.remotePersonnage(player);
				nextTile.addPersonnage(player);
			}
			else
			{
				System.out.println("Crossing is closed");
			}
		}
		else
		{
			throw new Exception("Asked direction doesnt exists");
		}
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

	public Tile getTile(int ID)
	{
		return this.tilesMap.get(ID);
	}

	public int getTileAmount()
	{
		return this.tilesMap.size();
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