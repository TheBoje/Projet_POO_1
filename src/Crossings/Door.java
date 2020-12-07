package Crossings;

import Items.Item;

import java.util.List;

public class Door extends Crossing
{
	public Door(boolean isOpen)
	{
		super(isOpen);
	}

	public Door()
	{
		super(true);
	}

	@Override
	public void tryOpen(List<Item> items)
	{
		this.open();
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