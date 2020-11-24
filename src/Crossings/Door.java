package Crossings;

import Tiles.Tile;

import java.util.ArrayList;

public class Door implements Crossing {

	private boolean isOpen;

	@Override
	public void setTiles(ArrayList<Tile> tiles)
	{
		this.tiles = tiles;
	}

	public void isOpen() {
		// TODO - implement Door.isOpen
		throw new UnsupportedOperationException();
	}

	public void open() {
		// TODO - implement Door.open
		throw new UnsupportedOperationException();
	}

	public void close() {
		// TODO - implement Door.close
		throw new UnsupportedOperationException();
	}

	public Door() {
		this.isOpen = false;
		this.tiles = null;
	}
}