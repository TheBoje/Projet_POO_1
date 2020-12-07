package TestsTiles;

import Crossings.Door;
import Items.Item;
import Personnages.Personnage;
import Tiles.Direction;
import Tiles.Tile;
import Tiles.UnknownDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TileTests
{
	Tile tile1;
	Tile tile2;

	Random rn = new Random();

	@BeforeEach
	void init() throws UnknownDirection
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
	public void test_take()
	{
		int size = tile1.getItems().size();
		Item item = tile1.getItem(0);
		tile1.take(item);
		assertEquals(tile1.getItems().size(), size - 1);
		assertFalse(tile1.getItems().contains(item));
	}

	@Test
	public void test_take_null()
	{
		int size = tile1.getItems().size();
		tile1.take(null);
		assertEquals(tile1.getItems().size(), size);
	}

	@Test
	public void test_get_personnage_null()
	{

	}
}
