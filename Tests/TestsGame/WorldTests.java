package TestsGame;

import Game.World;
import org.junit.jupiter.api.BeforeEach;

public class WorldTests
{
	World world;
	@BeforeEach
	public void init()
	{
		world = new World(10);
	}
}
