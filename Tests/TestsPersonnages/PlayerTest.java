package TestsPersonnages;

import Items.*;
import Personnages.Animal;
import Personnages.NPC;
import Personnages.NoSpeechAvailable;
import Personnages.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest extends HumanTest
{

	private final ArrayList<String> speeches1 = new ArrayList<>();
	private final ArrayList<String> speeches2 = new ArrayList<>();
	private final ArrayList<Item> inventory1 = new ArrayList<>();
	private final ArrayList<Item> inventory2 = new ArrayList<>();
	private final Clothes clothes = new Clothes("Coat", 7);
	private final Food food = new Food("Pudding", 4);
	private final Misc misc = new Misc("junk", 1);
	private final MeleeWeapon melee = new MeleeWeapon("knife", 2);
	private final String name = "Robert";
	private Player p1;
	private Player p2;
	private NPC npc1;
	private Animal animal1;

	@BeforeEach
	void initPlayer()
	{
		speeches1.add("Bonjour");
		speeches1.add("Gros moche");
		speeches1.add("should be in English tho");
		speeches2.add("Hello");
		speeches2.add("Ugly one");
		speeches2.add("In English tho");
		inventory1.add(clothes);
		inventory1.add(food);
		inventory1.add(misc);
		inventory1.add(melee);
		inventory2.add(clothes);
		inventory2.add(food);
		inventory2.add(misc);
		inventory2.add(melee);
	}

	//Testing a character trading with another one
	@Test
	void trade() {
		p1 = new Player(null, inventory1, name, speeches1);
		p2 = new Player(null, inventory2, name, speeches2);
		Misc p1Item = new Misc("Some", 3);
		Misc p2Item = new Misc("Thing", 5);
		p1.addItem(p1Item);
		p2.addItem(p2Item);
		p1.trade(p2, p1Item, p2Item);
		assertTrue(p1.getItems().contains(p2Item));
		assertTrue(p2.getItems().contains(p1Item));
		assertFalse(p2.getItems().contains(p2Item));
		assertFalse(p1.getItems().contains(p1Item));
	}

	//Testing trade fail conditions
	@Test
	void tradeFail()
	{
		p1 = new Player(null, inventory1, name, speeches1);
		p2 = new Player(null, inventory2, name, speeches2);
		p1.trade(null,melee, clothes);
		p1.trade(p2, melee, null);
		p1.trade(p2, null, clothes);
		p2.removeItem(clothes);
		p1.trade(p2, food, clothes);
	}

	//Testing pet an animal, null an a Player
	@Test
	void pet() throws NoSpeechAvailable
	{
		animal1 = new Animal(null, null, "ADogIGuess", speeches1);
		p1 = new Player(null, inventory1, name, speeches2);
		p2 = new Player(null, inventory2, name, speeches2);
		p1.pet(animal1);
		p1.pet(null);
		p1.pet(p2);
	}

	//Testing if an item has been taken
	@Test
	void take()
	{
		p1 = new Player(null, inventory1, name, speeches1);
		Misc p1Item = new Misc("Some", 3);
		p1.take(p1Item);
		assertTrue(p1.getItems().contains(p1Item));
	}

	//Testing if we can eat
	@Test
	void eat() throws InvalidTarget
	{
		p1 = new Player(null, inventory1, name, speeches1);
		Integer initHunger = p1.getHunger();
		p1.starve();
		p1.use(food, p1);
		assertEquals(p1.getHunger(),initHunger);
		assertFalse(p1.getItems().contains(food));
	}

	@Test
	void healTestFail()
	{
		p1 = new Player(null, inventory1, name, speeches1);
		int initHP = p1.getHp();
		p1.heal(-5);
		assertEquals(initHP, p1.getHp());
	}

	@Test
	void damageFail()
	{
		p1 = new Player(null, inventory1, name, speeches1);
		int initHP = p1.getHp();
		p1.takeDamage(-5);
		assertEquals(initHP, p1.getHp());
	}

	//Testing if a character can wear smth
	@Test
	void wear()
	{
		p1 = new Player(null, inventory1, name, speeches1);
		Integer heatInit = p1.getBodyHeat();
		p1.chill();
		p1.chill();
		assertNotEquals(heatInit, p1.getBodyHeat());
		p1.wear(clothes);
		assertFalse(p1.getItems().contains(clothes));
		assertEquals(heatInit, p1.getBodyHeat());
	}

	//Reverse it
	@Test
	void takeOff()
	{
		p1 = new Player(null, inventory1, name, speeches1);
		p1.wear(clothes);
		p1.takeOff(clothes);
		assertTrue(p1.getItems().contains(clothes));
	}
}