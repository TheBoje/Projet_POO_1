package Tiles;

import Game.InputError;

public enum Direction
{
	N("n", 0), E("e", 1), S("s", 2), W("w", 3);
	private final String stringValue;
	private final int index;

	Direction(String s, int index)
	{
		this.stringValue = s;
		this.index = index;
	}


	public static Direction invert(Direction input) throws UnknownDirection
	{
		switch (input)
		{
			case N -> {
				return S;
			}
			case E -> {
				return W;
			}
			case S -> {
				return N;
			}
			case W -> {
				return E;
			}
			default -> {
				throw new UnknownDirection();
			}
		}
	}

	public static Direction intToDirection(int index) throws UnknownDirection
	{
		Direction[] directions = Direction.values();
		Direction result = null;
		for (Direction i : directions)
		{
			if (i.getIndex() == index)
			{
				result = i;
				break;
			}
		}
		if (result == null)
		{
			throw new UnknownDirection();
		}
		else
		{
			return result;
		}
	}

	public static Direction stringToDirection(String input) throws InputError
	{
		Direction[] directions = Direction.values();
		Direction result = null;

		for (Direction dir : directions)
		{
			if (dir.getStringValue().equals(input.toLowerCase()))
			{
				result = dir;
				break;
			}
		}

		if (result == null)
		{
			throw new InputError();
		}
		else
		{
			return result;
		}
	}

	public int getIndex()
	{
		return this.index;
	}

	public String getStringValue()
	{
		return this.stringValue;
	}
}
