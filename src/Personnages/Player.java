package Personnages;

import Items.*;
import Tiles.*;

import java.util.ArrayList;
import java.util.List;

public class Player extends Human {

	private int hunger;
	private int bodyHeat;
	private List<Item> wearing;
	private static final int MAX_HUNGER = 10;
	private static final int MAX_HEAT = 10;

	public Player( Tile tile, List<Item> items, String name, List<String> sp) {
		super(tile, items, name, sp);
		this.bodyHeat = MAX_HEAT;
		this.hunger = MAX_HUNGER;
		this.wearing = new ArrayList<>();
		Item clothes = new Clothes("clothes", 5);
		this.wearing.add(clothes);
	}

	public boolean trade(Item input, Item output) {
		return (addItem(input) && removeItem(output));
	}

	public void pet(Animal animal) {
		animal.pet();
	}

	public void take(Item object) {
		this.addItem(object);
	}

	public void eat(Item item) {
		this.removeItem(item);
		/*if (this.hunger + item.nutValue > MAX_HUNGER)
		{
			this.hunger = MAX_HUNGER;
		}
		else
		{
			this.hunger += item.nutValue
		}*/
	}

	public void wear(Item item) {
		if (this.removeItem(item))
			this.wearing.add(item);
	}

	public void takeOff(Item item) {
		if (this.wearing.remove(item))
			this.addItem(item);
	}

	@Override
	public void print() {
		System.out.println("#########################Player status#########################\n \nHunger : ");
		if (this.hunger == 10) {
			System.out.println("Your stomach is full!\n");
		} else if (this.hunger < 10 && this.hunger > 6) {
			System.out.println("You're not hungry yet\n");
		} else if (this.hunger <= 6 && this.hunger >= 4) {
			System.out.println("Your stomach starts gurgling\n");
		} else {
			System.out.println("You're about to die from starvation\n");
		}
		System.out.println("Body heat : ");
		if (this.bodyHeat == 10) {
			System.out.println("You feel warm!\n");
		} else if (this.bodyHeat < 10 && this.bodyHeat > 6) {
			System.out.println("You're not cold yet\n");
		} else if (this.bodyHeat <= 6 && this.bodyHeat >= 4) {
			System.out.println("Your hands start freezing\n");
		} else {
			System.out.println("You're about to die from coldness\n");
		}
		System.out.println("Inventory :\n");
		for (Item current : getItems()) {
			System.out.format("- %s\n ", current.getName());
		}
		System.out.println("Wearing :\n");
		for (Item current : this.wearing) {
			System.out.format("- %s\n", current.getName());
		}
		System.out.println("################################################################");
	}

	public void  printDebug(){
		System.out.format("Player : %s\n",this.getName());
		if (this.getItems().size() > 0)
		{
			System.out.println("Inventory :");
			for (Item item : getItems()){
				System.out.format("\t%s\n ", item.getName());
			}
		}
		if (this.wearing.size() > 0)
		{
			System.out.println("Wearing :");
			for (Item item : this.wearing)
			{
				System.out.format("\t%s\n", item.getName());
			}
		}
		System.out.format("Hunger : %d\nBodyHeat : %d\n",this.hunger, this.bodyHeat);
	}
}