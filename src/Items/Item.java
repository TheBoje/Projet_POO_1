package Items;

import Personnages.Personnage;

import java.util.Random;

public abstract class Item
{
	public static final int MAX_VALUE = 10;
	public static final int MAX_CAR_VALUE = 5;
	// TODO Traduire en anglais
	public static final String[] ClothesNames = {
			"Chaussette",
			"Caleçon de Vincent",
			"Pantalon",
			"T-shirt",
			"Sweat",
			"Manteau"
	};

	public static final String[] FoodNames = {
			"Kebab",
			"Pain",
			"Steak"
	};

	public static final String[] RangeWeaponNames = {
			"Pistolet",
			"Fusil",
			"Mitraillette",
			"Arc"
	};

	public static final String[] MeleeWeaponNames = {
			"Couteau",
			"Bate de baseball",
			"Hache"
	};

	public static final String[] ThrowWeaponNames = {
			"Grenade",
			"Cailloux"
	};

	public static final String[] MiscNames = {
			"Planche",
			"Bouteille",
			"Cailloux"
	};

	public static final String[][] ItemsNames = {ClothesNames, FoodNames, RangeWeaponNames, MeleeWeaponNames, ThrowWeaponNames, MiscNames};

	private String name;
	private int value;

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

	@Override
	public String toString()
	{
		String str = "Nom - " + this.name + "\n";
		str += "\tvaleur - " + this.value + "\n";
		return str;
	}

	public abstract String getUsage();
}