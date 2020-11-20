package Characters;

import Tiles.*;
import java.util.*;
import Objects.*;

public abstract class Character {

	private Tile tile;
	private Collection<Object> objects;
	private static int DEFAULT_HP = 10;
	private String name;
	private int hp;
	private List<String> speeches;

	public String getName() {
		return this.name;
	}

	public abstract void print();

	public boolean isAlive() {
		// TODO - implement Character.isAlive
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cible
	 */
	public void attack(Character cible) {
		// TODO - implement Character.attack
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param amount
	 */
	public void takeDamage(int amount) {
		// TODO - implement Character.takeDamage
		throw new UnsupportedOperationException();
	}

}