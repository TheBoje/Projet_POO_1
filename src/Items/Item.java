package Items;

import Personnages.Personnage;

public abstract class Item
{

	private String name;

	public Item(String name)
	{
		this.name = name;
	}

	public abstract void use(Personnage perso) throws Exception;

    public String getName()
	{
		return this.name;
	}
}