package Personnages;

import Items.Item;
import Tiles.Tile;

import java.util.List;

public class Penguin extends Animal {

	public Penguin(Tile tile, List<Item> items, String name, int health, List<String> speeches)
	{
		super(tile, items, name, health, speeches);
	}

	public Penguin(Tile tile, List<Item> items, String name, List<String> speeches)
	{
		super(tile, items, name, speeches);
	}

}