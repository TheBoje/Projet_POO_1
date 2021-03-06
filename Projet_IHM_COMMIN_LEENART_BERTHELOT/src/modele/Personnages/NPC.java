package modele.Personnages;

import modele.Items.InvalidTarget;
import modele.Items.Item;
import modele.Items.Weapon;
import modele.Tiles.Tile;

import java.util.Collection;
import java.util.List;

public class NPC extends Human
{
	public static final String[] ScientistSpeeches = {"Hello ! I'm a cool scientist", "Did you know your garbage ends up in Seal's stomach ?"};
	public static final String[] InuitSpeeches = {"[Inuktitut] Hello ! I'm a cool Inuit", "[Inuktitut] I'm a fisherman, and I'm fishing fish", "[Inuktitut] Oh ! Who are you ?"};
	public static final String[] TreasuSeekerSpeeches = {"Hello ! I'm a Treasure Seeker", "I am looking for treasures, can you help me out ?", "Did you find any treasure there ? I've been looking for AGES!"};
	public static final String[][] NPCspeeches = {ScientistSpeeches, InuitSpeeches, TreasuSeekerSpeeches};

	private boolean isHostile;
	private Weapon weapon = null;


	public NPC(Tile tile, List<Item> items, String name, int health, List<String> speeches)
	{
		super(tile, items, name, health, speeches);
		this.isHostile = false;
		for(Item item : items)
		{
			if(item instanceof Weapon)
			{
				this.weapon = (Weapon) item;
				break;
			}
		}
	}

	public NPC(Tile tile, List<Item> items, String name, List<String> speeches)
	{
		super(tile, items, name, speeches);
		this.isHostile = false;
	}

	public void print()
	{
		System.out.println("----- " + this.getName() + " -----");
		System.out.println(this.isAlive() ? "Vivant" : "Mort");
		Collection<Item> it = this.getItems();

		for (Item i : it)
		{
			System.out.println(i.toString());
		}
	}

	@Override
	public String talk() throws NoSpeechAvailable, GameWonException
	{
		if (this.getName().equals("Chief scientist"))
		{
			throw new GameWonException();
		}
		else
		{
			return this.getRandomSpeech();
		}
	}

	@Override
	public void takeDamage(int amount)
	{
		super.takeDamage(amount);
		this.isHostile = true;
	}

	public void react(Personnage target)
	{
		if(isHostile)
		{
			try
			{
				this.attack(this.weapon, target);
			} catch (InvalidTarget invalidTarget)
			{
				invalidTarget.printStackTrace();
			}
		}
	}
}