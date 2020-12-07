package Personnages;

import Items.Item;
import Tiles.Tile;

import java.util.List;

public class Animal extends Personnage
{

    /*
    "Penguin",
            "White bear",
            "Killer whale",
            "White wolf",
            "Seal"
    */

	// TODO remplire les speechs
	public static final String[] PenguinSpeeches = {};
	public static final String[] WhiteBearSpeeches = {};
	public static final String[] KillerWhaleSpeeches = {};
	public static final String[] WhiteWolfSpeeches = {};
	public static final String[] SealSpeeches = {};

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

	// Ecrit une ligne de dialogue choisi aléatoirement dans la liste "speeches"
	public void pet() throws NoSpeechAvailable
	{
		System.out.println("- " + this.getRandomSpeech());
	}

}