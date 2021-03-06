package modele.Crossings;

import modele.Items.Item;
import modele.Tiles.Direction;
import modele.Tiles.Tile;
import modele.Tiles.TileError;
import modele.Tiles.UnknownDirection;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class Crossing
{

	public static final int NB_TYPE_CROSSINGS = 1;

	public static Crossing linkTiles(int from, int to, HashMap<Integer, Tile> tiles, Direction dir, Random random)
	{
		Crossing crossing;

		switch (random.nextInt(NB_TYPE_CROSSINGS))
		{
			case 0 -> crossing = new Pathway(random.nextBoolean());
			default -> crossing = null;
		}

		try
		{
			tiles.get(from).setNearbyTile(to, crossing, dir); // int tileID, Crossing crossing, Direction dir
			tiles.get(to).setNearbyTile(from, crossing, Direction.invert(dir));
		}
		catch (TileError | UnknownDirection tileError)
		{
			tileError.printStackTrace();
		}

		return crossing;
	}

	boolean isOpen; // package private

	protected Crossing(boolean isOpen)
	{
		this.isOpen = isOpen;
	}

	protected Crossing()
	{
		this.isOpen = true;
	}

	public abstract void tryOpen(List<Item> items) throws CantOpenCrossing;

	public boolean isOpen()
	{
		return this.isOpen;
	}
}