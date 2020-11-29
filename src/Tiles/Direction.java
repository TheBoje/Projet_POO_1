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
}
