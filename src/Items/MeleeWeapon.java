package Items;

import Personnages.Personnage;

public class MeleeWeapon extends Weapon
{
	public MeleeWeapon(String name, int dmg)
	{
		super(name, dmg);
	}

	public MeleeWeapon(String name, int dmg, int value)
	{
		super(name, dmg, value);
	}

	public void use(Personnage personnage) throws InvalidTarget
	{
		if (personnage != null)
		{
			personnage.takeDamage(this.getDamages());
		}
		else
		{
			throw new InvalidTarget();
		}
	}


	@Override
	public String getUsage()
	{
		return "Use this item to attack a character in your area";
	}

}
