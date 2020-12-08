package Personnages;

import Items.InvalidTarget;
import Items.Item;
import Items.MeleeWeapon;
import Items.Weapon;
import Tiles.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Personnage
{
	public static final int MAX_LEN_INVENTORY_GENERATED = 3;
	public static final String[] AnimalNames = {
			"Penguin",
			"White bear",
			"Killer whale",
			"White wolf",
			"Seal"
	};
	public static final String[] NPCsNames = {
			"Scientist",
			"Inuits",
			"Treasur seeker"
	};
	public static final String[][] PersonnagesNames = {AnimalNames, NPCsNames};
	private static final int DEFAULT_HP = 10;
	private final String name;
	private final List<String> speeches;
	private Tile tile;
	private List<Item> items;
	private int hp;


	public Personnage(Tile t, List<Item> i, String n, int health, List<String> sp)
	{
		this.tile = t;
		this.items = i;
		this.name = n;
		this.hp = health;
		this.speeches = sp;
	}

	public Personnage(Tile t, List<Item> i, String n, List<String> sp)
	{
		this.tile = t;
		this.items = i;
		this.name = n;
		this.hp = DEFAULT_HP;
		this.speeches = sp;
	}

	public static Personnage generateRandomPersonnage(Random random, Tile tile)
	{
		return generateRandomPersonnage(random, tile, random.nextInt(PersonnagesNames.length));
	}

	public static Personnage generateRandomPersonnage(Random random, Tile tile, int chosenPersonnage)
	{
		Personnage personnage;
		List<Item> inventory = new ArrayList<>();

		// On génère un inventaire d'objets aléatoires pour les personnages créés
		for (int i = 0; i < MAX_LEN_INVENTORY_GENERATED; i++)
		{
			inventory.add(Item.generateRandomItem(random));
		}

		switch (chosenPersonnage)
		{
			case 0 -> {
				int chosenAnimal = random.nextInt(AnimalNames.length);
				personnage = new Animal(tile, inventory, AnimalNames[random.nextInt(AnimalNames.length)], Arrays.asList(Animal.AnimalSpeeches[chosenAnimal]));
			}
			case 1 -> {
				int chosenNPC = random.nextInt(NPCsNames.length);
				personnage = new NPC(tile, inventory, NPCsNames[chosenNPC], Arrays.asList(NPC.NPCspeeches[chosenNPC]));
				personnage.addItem(Item.generateRandomItem(random, Item.CLOTHE_INDEX)); // Un pnj doit au moins avoir un vêtement
			}
			default -> personnage = null;
		}

		return personnage;
	}

	/***********************************METHODS***********************************/


	public abstract void print();

	public abstract String talk() throws NoSpeechAvailable, GameWonException;

	public boolean isAlive()
	{
		return (this.hp > 0);
	}


	public void attack(Weapon weapon, Personnage target) throws InvalidTarget
	{
		if (weapon == null)
		{
			Weapon bareHand = new MeleeWeapon("bareHands", 1);
			bareHand.use(target);
		}
		else
		{
			weapon.use(target);
		}
	}

	public void takeDamage(int amount)
	{
		this.hp -= amount;
		if (this.hp <= 0)
		{
			this.hp = 0;
			this.dropInventory();
			this.printDeath();
			this.tile.remotePersonnage(this);
		}
	}


	public boolean addItem(Item object)
	{
		return items.add(object);
	}

	public boolean removeItem(Item toRemove)
	{
		return items.remove(toRemove);
	}

	public String getRandomSpeech() throws NoSpeechAvailable
	{
		if (this.speeches == null || this.speeches.size() == 0)
		{
			throw new NoSpeechAvailable();
		}
		else
		{
			Random random = new Random();

			int r = random.nextInt(this.speeches.size());
			return this.speeches.get(r);
		}
	}

	public void dropInventory()
	{
		// Vide le premier élément de l'inventaire n fois où n est le nombre d'éléments initial
		for (int i = 0; i < this.items.size(); i++)
		{
			this.dropItem(0);
		}
	}

	public void dropItem(int index) throws IndexOutOfBoundsException
	{
		if (index >= 0 && index < this.items.size())
		{
			this.tile.addItem(this.items.get(index));
			this.items.remove(index);
		}
		else
		{
			throw new IndexOutOfBoundsException();
		}
	}

	/***********************************GETTERS***********************************/
	public Tile getTile()
	{
		return this.tile;
	}

	/***********************************SETTERS***********************************/
	public void setTile(Tile tile)
	{
		this.tile = tile;
	}

	public List<Item> getItems()
	{
		return this.items;
	}

	public void setItems(List<Item> items)
	{
		this.items = new ArrayList<Item>();
	}

	public Item getItem(int index)
	{
		return this.items.get(index);
	}

	public String getName()
	{
		return this.name;
	}

	public int getHp()
	{
		return this.hp;
	}

	public void printDeath()
	{
		System.out.format("[%s] died\n", this.getName());
	}

}