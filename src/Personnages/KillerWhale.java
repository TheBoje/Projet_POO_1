package Personnages;

import Items.Item;
import Tiles.Tile;

import java.util.List;

public class KillerWhale extends Animal {

	public KillerWhale(int tile, List<Item> items, String name, int health, List<String> speeches)
	{
		super(tile, items, name, health, speeches);
	}

	public KillerWhale(int tile, List<Item> items, String name, List<String> speeches)
	{
		super(tile, items, name, speeches);
	}

}