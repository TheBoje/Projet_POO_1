package modele.Personnages;

import modele.Items.Item;
import modele.Tiles.Tile;

import java.util.List;

public abstract class Human extends Personnage
{

	public Human(Tile tile, List<Item> items, String name, int health, List<String> speeches)
	{
		super(tile, items, name, health, speeches);
	}

	public Human(Tile tile, List<Item> items, String name, List<String> speeches)
	{
		super(tile, items, name, speeches);
	}

	public String talk() throws NoSpeechAvailable, GameWonException
	{
		return this.getRandomSpeech();
	}

}