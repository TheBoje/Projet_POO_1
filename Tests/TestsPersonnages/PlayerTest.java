package TestsPersonnages;

import Items.Clothes;
import Items.Food;
import Items.Item;
import Personnages.Animal;
import Personnages.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest extends HumanTest
{

	private Player p1;
	private Food food;
	private Animal animal;


	@BeforeEach
	void init()
	{

	}

	@Test
	void trade() throws Exception
	{
		p1 = new Player(null, new ArrayList<Item>(), null, null);
		Item itemIn = new Clothes("Some", 3);
		Item itemOut = new Clothes("Thing", 5);
		p1.addItem(itemOut);
		assertTrue(p1.trade(itemIn, itemOut));
	}

	@Test
	void tradeFail() throws Exception
	{
		p1 = new Player(null, new ArrayList<Item>(), null, null);
		Item itemIn = new Clothes("Some", 5);
		Item itemOut = new Clothes("Thing", 5);
		assertFalse(p1.trade(itemIn, itemOut));
	}

	@Test
	void tradeNull()
	{
		p1 = new Player(null, new ArrayList<Item>(), null, null);
		try
		{
			assertTrue(p1.trade(null, null));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	void pet0()
	{
		p1 = new Player(null, new ArrayList<Item>(), null, null);
		p1.pet(null);
	}

	@Test
	void pet1()
	{
		p1 = new Player(null, new ArrayList<Item>(), null, null);
		animal = new Animal(null, null, null, new ArrayList<String>());
		p1.pet(animal);
	}

	@Test
	void take()
	{

	}

	@Test
	void eat(){
		food = new Food("Kebab", 6);
		p1 = new Player(null, new ArrayList<Item>(), null, null);
		int initHunger = p1.getHunger();
		p1.starve();
		assertEquals(p1.getHunger(), initHunger - 1);
		p1.addItem(food);
		p1.eat(food);
		assertEquals(p1.getHunger(), initHunger);
	}

	@Test
	void wear()
	{
	}

	@Test
	void takeOff()
	{
	}
}