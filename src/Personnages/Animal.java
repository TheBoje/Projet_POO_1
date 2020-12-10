package Personnages;

import Items.InvalidTarget;
import Items.Item;
import Tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal extends Personnage
{

	public static final String[] PenguinSpeeches = {"[Undistinguising Penguin noises]"};
	public static final String[] WhiteBearSpeeches = {"[growl]", "[grrrrr]"};
	public static final String[] KillerWhaleSpeeches = {"[Weird killer whale noises]"};
	public static final String[] WhiteWolfSpeeches = {"OOooowhooo!"};
	public static final String[] SealSpeeches = {"[Cute seal noises]"};

	public static final String[][] AnimalSpeeches = {PenguinSpeeches, WhiteBearSpeeches, KillerWhaleSpeeches, WhiteWolfSpeeches, SealSpeeches};

	public Animal(Tile tile, List<Item> items, String name, int health, List<String> speeches)
	{
		super(tile, items, name, health, speeches);
	}

	public Animal(Tile tile, List<Item> items, String name, List<String> speeches)
	{
		super(tile, items, name, speeches);
	}

	// Affiche les caractéristiques visible par le joueur
	public void print()
	{
		System.out.println("----- " + this.getName() + " -----");
		System.out.println(this.isAlive() ? "Vivant" : "Mort");
	}

	@Override
	public String talk() throws NoSpeechAvailable
	{
		return this.getRandomSpeech();
	}

	// Ecrit une ligne de dialogue choisi aléatoirement dans la liste "speeches"
	public void pet() throws NoSpeechAvailable
	{
		System.out.println("- " + this.getRandomSpeech());
	}

	public void protectTerritory(Random rn)
	{
		List<Personnage> targets = new ArrayList<>(this.getTile().getPersonnages());
		targets.remove(this);

		try
		{
			this.attack(null, targets.get(rn.nextInt(targets.size())));
		}
		catch (InvalidTarget invalidTarget)
		{
			invalidTarget.printStackTrace();
		}
	}

}