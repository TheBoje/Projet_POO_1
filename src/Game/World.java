package Game;

import Crossings.Crossing;
import Items.*;
import Personnages.NPC;
import Personnages.Personnage;
import Personnages.Player;
import Tiles.Direction;
import Tiles.Tile;
import Tiles.UnknownDirection;

import java.util.*;

public class World
{
	/***********************************ATTRIBUTES***********************************/

	private final HashMap<Integer, Tile> tilesMap;

	/***********************************CONSTRUCTOR***********************************/

	public World(int tilesAmount)
	{
		Random rn = new Random();
		this.tilesMap = Tile.generateTiles(tilesAmount, rn);

		this.createPlayer();

		this.addEndingTile(rn);
	}

	public void addEndingTile(Random rn)
	{
		Tile endTile = new Tile();
		Personnage endingNPC = new NPC(endTile, new ArrayList<>(), "Chief scientist", new ArrayList<>());
		endTile.addPersonnage(endingNPC);
		int idTile = this.tilesMap.size();
		this.tilesMap.put(idTile, endTile);

		boolean isSet = false;

		for(int i = this.tilesMap.size() - 2; i >= 0; i--)
		{
			for(int j = 0; j < Direction.values().length; j++)
			{
				if(this.tilesMap.get(i).getCrossings()[j] == null && !isSet)
				{
					try
					{
						Crossing.linkTiles(i, idTile, this.tilesMap, Direction.intToDirection(j), rn);
						isSet = true;
					}
					catch (UnknownDirection unknownDirection)
					{
						unknownDirection.printStackTrace();
					}
				}
			}

			if(isSet)
				break;
		}
	}

	/***********************************METHODS***********************************/
	// Crée le player sur la première case de la carte
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

	// Recherche du player dans la carte, retourne une exception si le player n'existe pas
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

	// Affiche le detail de chaque case du monde, surtout utilisé pour le debug
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