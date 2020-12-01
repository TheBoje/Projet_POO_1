package Crossings;

import Tiles.Tile;

import java.util.ArrayList;

public class Door extends Crossing
{

	private boolean isOpen;

	public Door(ArrayList<Tile> tiles, boolean isOpen)
	{
		super(true, tiles);
		this.isOpen = true;
	}

	public Door()
	{
		super(true, new ArrayList<>());
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