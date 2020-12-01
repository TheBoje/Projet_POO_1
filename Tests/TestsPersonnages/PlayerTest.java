package TestsPersonnages;

import Items.Clothes;
import Items.Item;
import Personnages.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest extends HumanTest
{

	private Player p1;
	private Player p2;

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
	void tradeSemiNull()
	{
		p1 = new Player(null, new ArrayList<Item>(), null, null);
		Item itemIn = new Clothes("Some", 5);
		try
		{
			assertFalse(p1.trade(itemIn, null));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		p1.addItem(itemIn);
		try
		{
			assertFalse(p1.trade(null, itemIn));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	void pet()
	{
		p1 = new Player(null, new ArrayList<Item>(), null, null);
		try
		{
			p1.pet(null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	void take()
	{

	}

	@Test
	void eat()
	{
	}

	@Test
	void wear()
	{
	}

	@Test
	void takeOff()
	{
	}

	@Test
	void testPrint()
	{
	}

	@Test
	void printDebug()
	{
	}
}