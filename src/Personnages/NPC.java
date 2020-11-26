package Personnages;

import Items.Item;
import Tiles.Tile;

import java.util.Collection;
import java.util.List;

public class NPC extends Human {

	private int speechCount;

	public NPC(Tile tile, List<Item> items, String name, int health, List<String> speeches)
	{
		super(tile, items, name, health, speeches);
	}

	public NPC(Tile tile, List<Item> items, String name, List<String> speeches)
	{
		super(tile, items, name, speeches);
	}

	public void print()
	{
		System.out.println("----- " + this.getName() + " -----");
		System.out.println(this.isAlive() ? "Vivant" : "Mort");
		Collection<Item> it = this.getItems();

		for(Item i : it)
		{
			System.out.println(i.toString());
		}
	}

	@Override
	public String talk()
	{
		return null;
	}
}