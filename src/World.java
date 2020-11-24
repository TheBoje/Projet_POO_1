import java.util.*;
import Tiles.*;
import Personnages.*;

public class World {

	private ArrayList<Tile> tiles;

	public World() {
		// TODO - implement World.World
		//throw new UnsupportedOperationException();
	}

	public void init() {
		// TODO - implement World.init
		throw new UnsupportedOperationException();
	}

	public Player getPlayer() throws Exception
	{
		for (Tile tile:tiles)
		{
			for (Personnage personnage: tile.getPersonnages())
			{
				if (personnage instanceof Player)
				{
					return (Player)personnage;
				}
			}
		}
		throw new Exception("Can't find player");
	}

}