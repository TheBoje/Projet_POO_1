package Tiles;

public enum Direction
{
	N, E, S, W;

	public int toIndex()
	{
		return toIndex(this);
	}
	public static int toIndex(Direction dir)
	{
		switch (dir)
		{
			case N -> {
				return 0;
			}
			case E -> {
				return 1;
			}
			case S -> {
				return 2;
			}
			case W -> {
				return 3;
			}
			default -> {
				return -1;
			}
		}
	}

	public static Direction intToDirection(int input)
	{
		switch (input)
		{
			case 0 -> {
				return N;
			}
			case 1 -> {
				return E;
			}
			case 2 -> {
				return S;
			}
			case 3 -> {
				return W;
			}
			default -> {
				throw new IllegalArgumentException();
			}
		}
	}

	public static Direction invert(Direction input)
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
				throw new IllegalArgumentException();
			}
		}
	}

	@Override
	public String toString()
	{
		switch (this)
		{
			case N -> {
				return "N";
			}
			case E -> {
				return "E";
			}
			case S -> {
				return "S";
			}
			case W -> {
				return "W";
			}
			default -> {
				throw new IllegalArgumentException();
			}
		}
	}

	public static Direction stringToDir(String input)
	{
		switch (input.toLowerCase())
		{
			case "n" -> {
				return N;
			}
			case "e" -> {
				return E;
			}
			case "s" -> {
				return S;
			}
			case "w" -> {
				return W;
			}
			default -> {
				throw new IllegalArgumentException();
			}
		}
	}
}
