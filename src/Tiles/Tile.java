package Tiles;

import Crossings.Crossing;
import Items.Item;
import Personnages.Personnage;
import Personnages.Player;

import java.util.ArrayList;
import java.util.List;

public class Tile
{
	/***********************************ATTRIBUTES***********************************/

	private List<Personnage> personnages;
	private int nearbyTilesID[];
	private Crossing nearbyCrossing[];
	private List<Item> items;

	/***********************************CONSTRUCTORS***********************************/

	public Tile()
	{
		this.personnages = new ArrayList<>();
		this.items = new ArrayList<>();
		this.nearbyTilesID = new int[4];
		this.nearbyCrossing = new Crossing[4];
	}

	public Tile(List<Personnage> personnages, List<Item> items, int tilesID[], Crossing crossings[])
	{
		this.personnages = personnages;
		this.items = items;
		this.nearbyTilesID = tilesID;
		this.nearbyCrossing = crossings;
	}

	/***********************************METHODS***********************************/

	public List<Item> search()
	{
		return this.items;
	}

	public void take(Item item)
	{
		this.removeItem(item);
	}

	private void removeItem(Item item)
	{
		this.items.remove(item);
	}

	/***********************************GETTERS***********************************/

	public List<Personnage> getPersonnages()
	{
		return this.personnages;
	}

	public Personnage getPersonnage(int index)
	{
		return this.personnages.get(index);
	}

	public Crossing[] getCrossings()
	{
		return this.nearbyCrossing;
	}

	public Crossing getCrossing(Direction dir) throws UnknownDirection
	{
		if (dir != null)
		{
			return this.nearbyCrossing[dir.getIndex()];
		}
		else
		{
			throw new UnknownDirection();
		}
	}

	public int getNextTileID(Direction dir) throws UnknownDirection
	{
		if (dir != null)
		{
			return this.nearbyTilesID[dir.getIndex()];
		}
		else
		{
			throw new UnknownDirection();
		}
	}

	public Item getItem(int index)
	{
		if (index >= 0 && index < this.items.size())
		{
			return this.items.get(index);
		}
		else
		{
			throw new IndexOutOfBoundsException();
		}
	}

	public List<Item> getItems()
	{
		return this.items;
	}

	/***********************************SETTERS***********************************/
	public void setNearbyTile(int tileID, Crossing crossing, Direction dir) throws TileError
	{
		if (tileID >= 0)
		{
			int index = dir.getIndex();
			this.nearbyTilesID[index] = tileID;
			this.nearbyCrossing[index] = crossing;
		}
		else
		{
			throw new TileError();
		}
	}

	public void addPersonnage(Personnage personnage)
	{
		if (personnage != null)
		{
			this.personnages.add(personnage);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public void addPersonnages(List<Personnage> personnages)
	{
		if (personnages.contains(null))
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.personnages.addAll(personnages);
		}
	}

	public void remotePersonnage(Personnage personnage)
	{
		if (personnage != null)
		{
			this.personnages.remove(personnage);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public void addItem(Item item)
	{
		if (item != null)
		{
			this.items.add(item);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	/***********************************DISPLAY***********************************/

	public void print()
	{
		System.out.format("========== TILE ==========\n");
		for (int i = 0; i < 4; i++)
		{
			if (nearbyCrossing[i] != null)
			{
				try
				{
					System.out.format("\t[%s] %s - %s\n", Direction.intToDirection(i).toString(), nearbyCrossing[i].getClass().getSimpleName(), nearbyCrossing[i].isOpen() ? "open" : "close");
				} catch (UnknownDirection ignore)
				{
				}
			}
		}
		if (this.personnages.size() > 0)
		{
			System.out.format("========== CHARACTERS ==========\n");
			for (Personnage personnage : this.personnages)
			{
				if (personnage instanceof Player)
				{
					((Player) personnage).printDebug();
				}
				else
				{
					personnage.print();
				}

			}
		}
		if (this.items.size() > 0)
		{
			System.out.format("========== ITEMS ==========\n");

			for (Item item : this.items)
			{
				System.out.format("\t%s\n", item.getName());
			}
		}
	}
}