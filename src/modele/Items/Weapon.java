package modele.Items;

import modele.Personnages.Personnage;

public abstract class Weapon extends Item
{
	private final int damages;

	public Weapon(String name, int dam)
	{
		super(name);
		this.damages = dam;
	}

	public Weapon(String name, int dam, int value)
	{
		super(name, value);
		this.damages = dam;
	}

	public abstract void use(Personnage personnage) throws InvalidTarget;

	public int getDamages()
	{
		return this.damages;
	}

	@Override
	public String toString()
	{
		String str = super.toString();
		str += "\tdégats - " + this.damages + "\n";
		return str;
	}

}
