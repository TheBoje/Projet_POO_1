package Crossings;

import Items.Item;

import java.util.List;

public abstract class Crossing
{
	boolean isOpen; // package private

	protected Crossing(boolean isOpen)
	{
		this.isOpen = isOpen;
	}

	protected Crossing()
	{
		this.isOpen = true;
	}
	public abstract void tryOpen(List<Item> items) throws CantOpenCrossing;

	public boolean isOpen()
	{
		return this.isOpen;
	}
}