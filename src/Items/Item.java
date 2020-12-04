package Items;

import Personnages.Personnage;

public abstract class Item
{
	public static final int DEFAULT_VALUE = 5;

	private String name;
	private int value;

	public Item(String name)
	{
		this.name = name;
	}

	public Item(String name, int value)
	{
		this.name = name;
		this.value = value;
	}

	public abstract void use(Personnage perso) throws Exception;

    public String getName()
	{
		return this.name;
	}

	public int getValue()
	{
		return this.value;
	}
}