package Items;

import Personnages.Personnage;
import Personnages.Player;


public class Food extends Item
{

	private int nutValue;
	private boolean hasBeenAte;

	public Food(String name, int nVal)
	{
		super(name);
		this.nutValue = nVal;
		this.hasBeenAte = false;
	}

	public Food(String name, int value, int nVal)
	{
		super(name, value);
		this.nutValue = nVal;
	}



	public void use(Personnage perso)
	{
		if(!this.hasBeenAte)
		{
			if (perso instanceof Player)
			{
				((Player) perso).fillHunger(this.nutValue);
				this.hasBeenAte = true;
				System.out.println("You ate " + this.getName());
			}
			else
			{
				System.out.println("You think that keep the food for yourself is a better idea");
			}
			System.out.println("There is nothing left");
		}
	}

	@Override
	public String toString()
	{
		String str = super.toString();
		str += "\tvaleur nutritionnelle - " + this.nutValue + "\n";
		return str;
	}

	public boolean getHasBeenAte()
	{
		return this.hasBeenAte;
	}

	@Override
	public String getUsage()
	{
		return "Use this to eat and staying alive";
	}
}
