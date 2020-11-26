package Crossings;

import Tiles.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Crossing
{

	private ArrayList<Tile> tiles;
	private boolean isOpen;

	protected Crossing(boolean isOpen, ArrayList<Tile> tiles)
	{
		this.isOpen = isOpen;
		this.tiles = tiles;
	}

	public boolean isOpen()
	{
		return this.isOpen;
	}

	public void setTiles(ArrayList<Tile> tiles)
	{
		this.tiles = tiles;
		for (Tile tile : this.tiles)
		{
			tile.addCrossing(this);
		}
	}

	public Tile getOtherWay(Tile in) throws Exception
	{
		if (this.tiles.size() != 2)
		{
			String out = String.format("Number of Tiles linked to this crossing invalid, expected 2, has %d", this.tiles.size());
			throw new Exception(out);
		}
		else
		{
			int inIndex = this.tiles.indexOf(in);
			int outIndex = inIndex ^ 1; // XOR 1 -> flip 1 -> 0 ou 0 -> 1
			return this.tiles.get(outIndex);
		}
	}
}