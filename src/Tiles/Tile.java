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

	/**
	 * 
	 * @param item
	 */
	public void take(Item item) {
		// TODO - implement Tile.take
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param crossings
	 */
	public Tile() {
		this.personnages = new ArrayList<>();
		this.items = new ArrayList<>();
		this.crossings = new ArrayList<>();
	}

	public void generateObjects() {
		// TODO - implement Tile.generateObjects
		throw new UnsupportedOperationException();
	}

	public ArrayList<Personnage> getPersonnages()
	{
		return this.personnages;
	}

}