package Crossings;

import Tiles.Tile;

import java.util.ArrayList;

public abstract class Crossing
{
	private boolean isOpen;

	protected Crossing(boolean isOpen)
	{
		this.isOpen = isOpen;
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