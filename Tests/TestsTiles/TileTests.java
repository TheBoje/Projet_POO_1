package TestsTiles;

import Crossings.Door;
import Items.Item;
import Personnages.NPC;
import Personnages.Personnage;
import Tiles.Direction;
import Tiles.Tile;
import Tiles.TileError;
import Tiles.UnknownDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TileTests
{
	Tile tile1;
	Tile tile2;

	Random rn = new Random();

	@BeforeEach
	void init() throws UnknownDirection, TileError
	{
		tile1 = new Tile();
		tile2 = new Tile();
		for (int i = 0; i < 4; i++)
		{
			Personnage temp = Personnage.generateRandomPersonnage(rn, tile1);
			tile1.addPersonnage(temp);
			temp = Personnage.generateRandomPersonnage(rn, tile2);
			tile2.addPersonnage(temp);
		}

		for (int i = 0; i < 4; i++)
		{
			Item temp = Item.generateRandomItem(rn);
			tile1.addItem(temp);
			temp = Item.generateRandomItem(rn);
			tile2.addItem(temp);
		}

		Direction randomDir = Direction.intToDirection(rn.nextInt(3));
		Direction invertedRandomDir = Direction.invert(randomDir);
		tile1.setNearbyTile(1, new Door(true), randomDir);
		tile2.setNearbyTile(0, new Door(true), invertedRandomDir);
	}

	@Test
	public void testTake()
	{
		int size = tile1.getItems().size();
		Item item = tile1.getItem(0);
		tile1.take(item);
		assertEquals(tile1.getItems().size(), size - 1);
		assertFalse(tile1.getItems().contains(item));
	}

	@Test
	public void testTakeNull()
	{
		int size = tile1.getItems().size();
		tile1.take(null);
		assertEquals(tile1.getItems().size(), size);
	}

	@Test
	public void testGetPersonnage()
	{
		Personnage p = Personnage.generateRandomPersonnage(rn, tile1);
		tile1.addPersonnage(p);
		int i = tile1.getPersonnages().indexOf(p);
		assertEquals(tile1.getPersonnage(i), p);
	}

	@Test
	public void testGetCrossing() throws TileError, UnknownDirection
	{
		Door door = new Door(true);
		tile1.setNearbyTile(2, door, Direction.S);
		assertEquals(door, tile1.getCrossing(Direction.S));
	}

	@Test
	public void testGetCrossingNull()
	{
		Throwable exception = assertThrows(UnknownDirection.class, () -> tile1.getCrossing(null));
		assertEquals(exception.getClass(), UnknownDirection.class);
	}

	@Test
	public void testGetNextTileIDNull()
	{
		Throwable exception = assertThrows(UnknownDirection.class, () -> tile1.getNextTileID(null));
		assertEquals(exception.getClass(), UnknownDirection.class);
	}

	@Test
	public void testGetItem()
	{
		Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> tile1.getItem(-1));
		assertEquals(exception.getClass(), IndexOutOfBoundsException.class);
		exception = assertThrows(IndexOutOfBoundsException.class, () -> tile1.getItem(99999));
		assertEquals(exception.getClass(), IndexOutOfBoundsException.class);
	}

	@Test
	public void testSetNearbyTile()
	{
		Door door = null;
		Throwable exception = assertThrows(TileError.class, () -> tile1.setNearbyTile(-1, door, Direction.S));
		assertEquals(exception.getClass(), TileError.class);
	}

	@Test
	public void testAddPersonnage()
	{
		Personnage p = Personnage.generateRandomPersonnage(rn, null);
		tile1.addPersonnage(p);
		assertTrue(tile1.getPersonnages().contains(p));
	}

	@Test
	public void testAddPersonnageNull()
	{
		Throwable exception = assertThrows(NullPointerException.class, () -> tile1.addPersonnage(null));
		assertEquals(exception.getClass(), NullPointerException.class);
		List<Personnage> personnageList = new ArrayList<>();
		personnageList.add(Personnage.generateRandomPersonnage(rn, null));
		personnageList.add(null);
		exception = assertThrows(NullPointerException.class, () -> tile1.addPersonnages(personnageList));
		assertEquals(exception.getClass(), NullPointerException.class);
	}

	@Test
	public void testRemovePersonnage()
	{
		Personnage p = tile1.getPersonnage(0);
		tile1.remotePersonnage(p);
		assertFalse(tile1.getPersonnages().contains(p));
	}

	@Test
	public void testRemovePersonnageNull()
	{
		Throwable exception = assertThrows(NullPointerException.class, () -> tile1.remotePersonnage(null));
		assertEquals(exception.getClass(), NullPointerException.class);
	}

	@Test
	public void testAddItem()
	{
		Item item = Item.generateRandomItem(rn);
		tile1.addItem(item);
		assertTrue(tile1.getItems().contains(item));
	}

	@Test
	public void testAddItemNull()
	{
		Throwable exception = assertThrows(NullPointerException.class, () -> tile1.addItem(null));
		assertEquals(exception.getClass(), NullPointerException.class);
	}
}
