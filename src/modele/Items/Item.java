package modele.Items;

import modele.Personnages.Personnage;

import java.util.Random;

public abstract class Item
{
	public static final int MAX_VALUE = 10;
	public static final int MAX_CAR_VALUE = 5;

	public static final int CLOTHE_INDEX = 0;
	public static final int FOOD_INDEX = 1;
	public static final int RANGE_WEAPON_INDEX = 2;
	public static final int MELEE_WEAPON_INDEX = 3;
	public static final int THROW_WEAPON_INDEX = 4;
	public static final int MISC_INDEX = 5;

	public static final String[] ClothesNames = {
			"Socks",
			"Vincent's underwear",
			"Trousers",
			"T-shirt",
			"Sweat",
			"Coat"
	};

	public static final String[] FoodNames = {
			"Kebab",
			"Bread",
			"Steak"
	};

	public static final String[] RangeWeaponNames = {
			"Gun",
			"Rifle",
			"Submachine gun",
			"Bow"
	};

	public static final String[] MeleeWeaponNames = {
			"Knife",
			"Baseball bat",
			"Axe"
	};

	public static final String[] ThrowWeaponNames = {
			"Grenade",
			"Stone"
	};

	public static final String[] MiscNames = {
			"Plank",
			"Bottle"
	};

	public static final String[][] ItemsNames = {ClothesNames, FoodNames, RangeWeaponNames, MeleeWeaponNames, ThrowWeaponNames, MiscNames};

	private final String name;
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

	// V1
	public static Item generateRandomItem(Random random)
	{
		return generateRandomItem(random, random.nextInt(ItemsNames.length));
	}

	public static Item generateRandomItem(Random random, int chosenItem)
	{
		Item item;

		switch (chosenItem)
		{
			case 0 -> item = new Clothes(ClothesNames[random.nextInt(ClothesNames.length)], random.nextInt(MAX_VALUE), random.nextInt(MAX_CAR_VALUE));
			case 1 -> item = new Food(FoodNames[random.nextInt(FoodNames.length)], random.nextInt(MAX_VALUE), random.nextInt(MAX_CAR_VALUE));
			case 2 -> item = new RangeWeapon(RangeWeaponNames[random.nextInt(RangeWeaponNames.length)], random.nextInt(MAX_CAR_VALUE), random.nextInt(MAX_VALUE), random.nextInt(RangeWeapon.MAX_AMMOS));
			case 3 -> item = new MeleeWeapon(MeleeWeaponNames[random.nextInt(MeleeWeaponNames.length)], random.nextInt(MAX_CAR_VALUE), random.nextInt(MAX_VALUE));
			case 4 -> item = new ThrowWeapon(ThrowWeaponNames[random.nextInt(ThrowWeaponNames.length)], random.nextInt(MAX_CAR_VALUE), random.nextInt(MAX_VALUE));
			default -> item = new Misc(MiscNames[random.nextInt(MiscNames.length)], random.nextInt(MAX_VALUE));
		}

		return item;
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

	@Override
	public String toString()
	{
		String str = "Nom - " + this.name + "\n";
		str += "\tvaleur - " + this.value + "\n";
		return str;
	}

	public abstract String getUsage();
}