package TestsCrossings;

import modele.Crossings.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CrossingsTests
{
	Crossing door1;
	Crossing door2;

	@BeforeEach
	void init()
	{
		door1 = new Pathway(true);
		door2 = new Pathway(false);
	}

	@Test
	public void test_isOpen()
	{
		assertTrue(door1.isOpen());
		assertFalse(door2.isOpen());
	}

	@Test
	public void test_open() throws CantOpenCrossing
	{
		assertTrue(door1.isOpen());
		assertFalse(door2.isOpen());
		door1.tryOpen(new ArrayList<>());
		assertTrue(door1.isOpen());
		door2.tryOpen(new ArrayList<>());
		assertTrue(door2.isOpen());
	}

}
