package Items;

import Personnages.Personnage;
import Personnages.Player;


public class Food extends Item
{

	private int nutValue;

	public Food(String name, int nVal)
	{
		super(name);
		this.nutValue = nVal;
	}

	public Food(String name, int value, int nVal)
	{
		super(name, value);
		this.nutValue = nVal;
	}



	public void use(Personnage perso)
	{
		if (perso instanceof Player)
		{
			// TODO appelle la modification de la jauge de bouffe du personnage
			((Player) perso).fillHunger(this.nutValue);
			System.out.println("You ate " + this.getName());
		}
		else
		{
			System.out.println("You think that keep the food for yourself is a better idea");
		}
	}
}
