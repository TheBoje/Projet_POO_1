package Crossings;

import Tiles.Tile;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Crossing
{

	ArrayList<Tile> tiles = null;

	public void setTiles(ArrayList<Tile> tiles)
	{
		this.tiles = tiles;
		for (Tile tile : this.tiles)
		{
			tile.addCrossing(this);
		}
	}
}