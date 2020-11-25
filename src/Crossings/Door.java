package Crossings;

import Tiles.Tile;

import java.util.ArrayList;

public class Door extends Crossing
{

	private boolean isOpen;

	public Door(ArrayList<Tile> tiles)
	{
		this.isOpen = false;
		this.tiles = null;
		this.tiles = tiles;
	}

	public Door()
	{
		this.isOpen = false;
		this.tiles = null;
		this.tiles = new ArrayList<Tile>();
	}

	public boolean isOpen()
	{
		return this.isOpen;
	}

	public void open()
	{
		this.isOpen = true;
	}

	public void close()
	{
		this.isOpen = false;
	}
}