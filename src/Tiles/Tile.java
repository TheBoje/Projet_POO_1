package Tiles;

import java.util.*;

import Crossings.Crossing;
import Items.*;
import Personnages.Personnage;

public class Tile {

	private ArrayList<Personnage> personnages;
	private ArrayList<Crossing> crossings;
	private ArrayList<Item> items;

	public ArrayList<Item> search() {
		return this.items;
	}

	public void addCrossing(Crossing crossing)
	{
		this.crossings.add(crossing);
	}


	public void take(Item item) {
		// TODO - implement Tile.take
		throw new UnsupportedOperationException();
	}

	public Tile() {
		this.personnages = new ArrayList<>();
		this.items = new ArrayList<>();
		this.crossings = new ArrayList<>();
	}

	public void print()
	{
		System.out.format("\tTILE : \n");
		System.out.format("\t\tCrossing count : %d\n", this.crossings.size());
	}

	public ArrayList<Personnage> getPersonnages()
	{
		return this.personnages;
	}

	public ArrayList<Crossing> getCrossings()
	{
		return this.crossings;
	}

}