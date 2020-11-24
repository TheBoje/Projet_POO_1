package Personnages;

import Items.*;
import Tiles.*;
import java.util.List;

public class Player extends Human {

	private int hunger;
	private int bodyHeat;
	private static final int MAX_HUNGER = 10;
	private static final int MAX_HEAT = 10;

	public Player(Tile t, List<Item> items, String name, List<String> sp) {
		super(t, items, name, sp);
		this.bodyHeat = MAX_HEAT;
		this.hunger = MAX_HUNGER;
	}

	public boolean trade(Item input, Item output) {

	}

	/**
	 * 
	 * @param direction
	 */
	public void go(int direction) {

	}

	/**
	 * 
	 * @param animal
	 */
	public void pet(Animal animal) {
		// TODO - implement Player.pet
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param object
	 */
	public void take(Item object) {
		// TODO - implement Player.take
		throw new UnsupportedOperationException();
	}

	@Override
	public String talk()
	{
		return null;
	}

	/**
	 * 
	 * @param item
	 */
	public void eat(Item item) {

	}

	/**
	 * 
	 * @param item
	 */
	public void wear(Item item) {

	}
}