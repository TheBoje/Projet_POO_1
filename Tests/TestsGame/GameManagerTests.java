package TestsGame;

import Crossings.CantOpenCrossing;
import Crossings.ClosedCrossing;
import Crossings.Crossing;
import Crossings.Door;
import Game.GameManager;
import Game.InputError;
import Items.Item;
import Personnages.Player;
import Tiles.Direction;
import Tiles.Tile;
import Tiles.TileError;
import Tiles.UnknownDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTests
{
	GameManager gm;

	@BeforeEach
	public void init()
	{
		gm = new GameManager();
	}

	@Test
	public void testGo() throws Exception
	{
		Tile tile = gm.getWorld().getTile(0);
		Player player = gm.getWorld().getPlayer();
		Crossing crossing = new Door();
		int dir = -1;
		for (int i = 0; i < 4; i++)
		{
			crossing = tile.getCrossing(Direction.intToDirection(i));
			if (crossing != null)
			{
				dir = i;
				break;
			}
		}
		crossing.tryOpen(new ArrayList<Item>());
		gm.go(Direction.intToDirection(dir));
		assertFalse(tile.getPersonnages().contains(player));
	}

	@Test
	public void testGoNull()
	{
		Throwable exception = assertThrows(UnknownDirection.class, () -> gm.go(null));
		assertEquals(exception.getClass(), UnknownDirection.class);
	}

	@Test
	public void testUseNull()
	{
		Throwable exception = assertThrows(NullPointerException.class, () -> gm.use(null));
		assertEquals(exception.getClass(), NullPointerException.class);
	}

	@Test
	public void testTake()
	{
		Throwable exception = assertThrows(InputError.class, () -> gm.take(999));
		assertEquals(exception.getClass(), InputError.class);
		exception = assertThrows(InputError.class, () -> gm.take(-1));
		assertEquals(exception.getClass(), InputError.class);
	}

	@Test
	public void testOpen() throws TileError, UnknownDirection, CantOpenCrossing
	{
		Tile tile = gm.getWorld().getTile(0);
		Crossing crossing = new Door(false);
		tile.setNearbyTile(1, crossing, Direction.N);
		gm.open(Direction.N);
		assertTrue(tile.getCrossing(Direction.N).isOpen());
	}

	@Test
	public void testOpenNull()
	{
		Throwable exception = assertThrows(UnknownDirection.class, () -> gm.open(null));
		assertEquals(exception.getClass(), UnknownDirection.class);
	}
}
