package Personnages;

import Items.Item;
import Tiles.Tile;

import java.util.List;

public class Dog extends Animal {

	public Dog(Tile tile, List<Item> items, String name, int health, List<String> speeches)
	{
		super(tile, items, name, health, speeches);
	}

	public Dog(Tile tile, List<Item> items, String name, List<String> speeches)
	{
		super(tile, items, name, speeches);
	}


}