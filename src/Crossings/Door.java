package Crossings;

import Tiles.Tile;

import java.util.ArrayList;

public class Door extends Crossing
{

	private boolean isOpen;

	public Door(boolean isOpen)
	{
		super(true);
		this.isOpen = true;
	}

	public Door()
	{
		super(true);
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