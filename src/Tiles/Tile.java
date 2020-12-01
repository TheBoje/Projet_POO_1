package Tiles;

import java.util.*;

import Crossings.Crossing;
import Items.*;
import Personnages.Personnage;
import Personnages.Player;

public class Tile {
	/***********************************ATTRIBUTES***********************************/

	private ArrayList<Personnage> personnages;
	private int nearbyTilesID[];
	private Crossing nearbyCrossing[];
	private ArrayList<Item> items;

	/***********************************CONSTRUCTORS***********************************/

	public Tile() {
		this.personnages = new ArrayList<>();
		this.items = new ArrayList<>();
		this.nearbyTilesID = new int[4];
		this.nearbyCrossing = new Crossing[4];
	}

	public Tile(ArrayList<Personnage> personnages, ArrayList<Item> items, int tilesID[], Crossing crossings[]) {
		this.personnages = personnages;
		this.items = items;
		this.nearbyTilesID = tilesID;
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

	public int getNextTileID(Direction dir)
	{
		return this.nearbyTilesID[dir.toIndex()];
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