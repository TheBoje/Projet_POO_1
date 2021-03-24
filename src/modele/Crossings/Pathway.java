package modele.Crossings;

import modele.Items.Item;

import java.util.List;

public class Pathway extends Crossing
{
	public Pathway(boolean isOpen)
	{
		super(isOpen);
	}

	public Pathway()
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