package Tiles;

import java.util.*;

import Crossings.Crossing;
import Items.*;
import Personnages.Personnage;
import Personnages.Player;

public class Tile {
	/***********************************ATTRIBUTES***********************************/

	private ArrayList<Personnage> personnages;
	private ArrayList<Crossing> crossings;
	private ArrayList<Item> items;

	/***********************************CONSTRUCTORS***********************************/

	public Tile() {
		this.personnages = new ArrayList<>();
		this.items = new ArrayList<>();
		this.crossings = new ArrayList<>();
	}

	public Tile(ArrayList<Personnage> personnages, ArrayList<Item> items, ArrayList<Crossing> crossings) {
		this.personnages = personnages;
		this.items = items;
		this.crossings = crossings;
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

	public ArrayList<Crossing> getCrossings()
	{
		return this.crossings;
	}

	/***********************************SETTERS***********************************/

	public void addCrossing(Crossing crossing)
	{
		this.crossings.add(crossing);
	}

	public void addCrossings(ArrayList<Crossing> crossings)
	{
		this.crossings.addAll(crossings);
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
		System.out.format("\t\tCrossing count : %d\n", this.crossings.size());
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
//		for (Item item: this.items)
//		{
//			item.print();
//		}
	}
}