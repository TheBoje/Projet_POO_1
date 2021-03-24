package TestsTiles;

import modele.Crossings.Pathway;
import modele.Items.Item;
import modele.Personnages.Personnage;
import modele.Tiles.Direction;
import modele.Tiles.Tile;
import modele.Tiles.TileError;
import modele.Tiles.UnknownDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TileTests
{
	Tile tile;

	Random rn = new Random();

	@BeforeEach
	void init() throws UnknownDirection, TileError
	{
		tile = new Tile();
		for (int i = 0; i < 4; i++)
		{
			Personnage temp = Personnage.generateRandomPersonnage(rn, tile);
			tile.addPersonnage(temp);
		}

		for (int i = 0; i < 4; i++)
		{
			Item temp = Item.generateRandomItem(rn);
			tile.addItem(temp);
		}

		Direction randomDir = Direction.intToDirection(rn.nextInt(3));
		tile.setNearbyTile(1, new Pathway(true), randomDir);
	}

	@Test
	public void testTake()
	{
		int size = tile.getItems().size();
		Item item = tile.getItem(0);
		tile.take(item);
		assertEquals(tile.getItems().size(), size - 1);
		assertFalse(tile.getItems().contains(item));
	}

	@Test
	public void testTakeNull()
	{
		int size = tile.getItems().size();
		tile.take(null);
		assertEquals(tile.getItems().size(), size);
	}

	@Test
	public void testGetPersonnage()
	{
		Personnage p = Personnage.generateRandomPersonnage(rn, tile);
		tile.addPersonnage(p);
		int i = tile.getPersonnages().indexOf(p);
		assertEquals(tile.getPersonnage(i), p);
	}

	@Test
	public void testGetCrossing() throws TileError, UnknownDirection
	{
		Pathway pathway = new Pathway(true);
		tile.setNearbyTile(2, pathway, Direction.S);
		assertEquals(pathway, tile.getCrossing(Direction.S));
	}

	@Test
	public void testGetCrossingNull()
	{
		Throwable exception = assertThrows(UnknownDirection.class, () -> tile.getCrossing(null));
		assertEquals(exception.getClass(), UnknownDirection.class);
	}

	@Test
	public void testGetNextTileIDNull()
	{
		Throwable exception = assertThrows(UnknownDirection.class, () -> tile.getNextTileID(null));
		assertEquals(exception.getClass(), UnknownDirection.class);
	}

	@Test
	public void testGetItem()
	{
		Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> tile.getItem(-1));
		assertEquals(exception.getClass(), IndexOutOfBoundsException.class);
		exception = assertThrows(IndexOutOfBoundsException.class, () -> tile.getItem(99999));
		assertEquals(exception.getClass(), IndexOutOfBoundsException.class);
	}

	@Test
	public void testSetNearbyTile()
	{
		Pathway pathway = null;
		Throwable exception = assertThrows(TileError.class, () -> tile.setNearbyTile(-1, pathway, Direction.S));
		assertEquals(exception.getClass(), TileError.class);
	}

	@Test
	public void testAddPersonnage()
	{
		Personnage p = Personnage.generateRandomPersonnage(rn, null);
		tile.addPersonnage(p);
		assertTrue(tile.getPersonnages().contains(p));
	}

	@Test
	public void testAddPersonnageNull()
	{
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> tile.addPersonnage(null));
		assertEquals(exception.getClass(), IllegalArgumentException.class);
		List<Personnage> personnageList = new ArrayList<>();
		personnageList.add(Personnage.generateRandomPersonnage(rn, null));
		personnageList.add(null);
		exception = assertThrows(IllegalArgumentException.class, () -> tile.addPersonnages(personnageList));
		assertEquals(exception.getClass(), IllegalArgumentException.class);
	}

	@Test
	public void testRemovePersonnage()
	{
		Personnage p = tile.getPersonnage(0);
		tile.remotePersonnage(p);
		assertFalse(tile.getPersonnages().contains(p));
	}

	@Test
	public void testRemovePersonnageNull()
	{
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> tile.remotePersonnage(null));
		assertEquals(exception.getClass(), IllegalArgumentException.class);
	}

	@Test
	public void testAddItem()
	{
		Item item = Item.generateRandomItem(rn);
		tile.addItem(item);
		assertTrue(tile.getItems().contains(item));
	}

	@Test
	public void testAddItemNull()
	{
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> tile.addItem(null));
		assertEquals(exception.getClass(), IllegalArgumentException.class);
	}
}
