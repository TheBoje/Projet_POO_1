package Tiles;

import java.util.*;

import Crossings.Crossing;
import Items.*;
import Personnages.Personnage;
import Personnages.Player;

public class Tile {
	/***********************************ATTRIBUTES***********************************/

	private List<Personnage> personnages;
	private int nearbyTilesID[];
	private Crossing nearbyCrossing[];
	private List<Item> items;

	/***********************************CONSTRUCTORS***********************************/

	public Tile() {
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

	public List<Item> search() {
		return this.items;
	}

	public void take(Item item) {
		// TODO - implement Tile.take
		throw new UnsupportedOperationException();
	}

	public void removeItem(Item item)
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

	public Crossing getCrossing(Direction dir)
	{
		return this.nearbyCrossing[dir.toIndex()];
	}

	public int getNextTileID(Direction dir)
	{
		return this.nearbyTilesID[dir.toIndex()];
	}

	public Item getItem(int index)
	{
		return this.items.get(index);
	}

	public List<Item> getItems()
	{
		return this.items;
	}

	/***********************************SETTERS***********************************/
	public void setNearbyTile(int tileID, Crossing crossing, Direction dir)
	{
		int index = dir.toIndex();
		this.nearbyTilesID[index] = tileID;
		this.nearbyCrossing[index] = crossing;
	}

	public void addPersonnage(Personnage personnage)
	{
		this.personnages.add(personnage);
	}

	public void addPersonnages(List<Personnage> personnages)
	{
		this.personnages.addAll(personnages);
	}

	public void remotePersonnage(Personnage personnage)
	{
		this.personnages.remove(personnage);
	}

	/***********************************DISPLAY***********************************/

	public void print()
	{
		System.out.format("\tTILE :\n");
		for (int i = 0; i < 4; i++)
		{
			if (nearbyCrossing[i] != null)
			{
				System.out.format("\t\t[%s] %s - %s\n", Direction.intToDirection(i).toString(), nearbyCrossing[i].getClass().getSimpleName(), nearbyCrossing[i].isOpen() ? "open" : "close");
			}
		}
		for (Personnage personnage: this.personnages)
		{
			if (personnage instanceof Player)
			{
				((Player)personnage).printDebug();
			}
			else
			{
				personnage.print();
			}

		}
	}
}