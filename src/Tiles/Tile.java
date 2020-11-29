package Tiles;

import java.util.*;

import Crossings.Crossing;
import Items.*;
import Personnages.Personnage;
import Personnages.Player;

public class Tile {
	/***********************************ATTRIBUTES***********************************/

	private ArrayList<Personnage> personnages;
	private Tile nearbyTiles[];
	private Crossing nearbyCrossing[];
	private ArrayList<Item> items;

	/***********************************CONSTRUCTORS***********************************/

	public Tile() {
		this.personnages = new ArrayList<>();
		this.items = new ArrayList<>();
		this.nearbyTiles = new Tile[4];
		this.nearbyCrossing = new Crossing[4];
	}

	public Tile(ArrayList<Personnage> personnages, ArrayList<Item> items, Tile tiles[], Crossing crossings[]) {
		this.personnages = personnages;
		this.items = items;
		this.nearbyTiles = tiles;
		this.nearbyCrossing = crossings;
	}

	/***********************************METHODS***********************************/

	public ArrayList<Item> search() {
		return this.items;
	}

	public void take(Item item) {
		// TODO - implement Tile.take
		throw new UnsupportedOperationException();
	}

	/***********************************GETTERS***********************************/

	public ArrayList<Personnage> getPersonnages()
	{
		return this.personnages;
	}

	public Crossing[] getCrossings()
	{
		return this.nearbyCrossing;
	}

	public Crossing getCrossing(Direction dir)
	{
		return this.nearbyCrossing[dir.toIndex()];
	}

	public Tile getNextTile(Direction dir)
	{
		return this.nearbyTiles[dir.toIndex()];
	}

	/***********************************SETTERS***********************************/
	public void setNearbyTile(Tile tile, Crossing crossing, Direction dir)
	{
		int index = dir.toIndex();
		this.nearbyTiles[index] = tile;
		this.nearbyCrossing[index] = crossing;
	}

	public void addPersonnage(Personnage personnage)
	{
		this.personnages.add(personnage);
	}

	public void addPersonnages(ArrayList<Personnage> personnages)
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
		System.out.format("\tTILE : \n");
		System.out.format("");
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