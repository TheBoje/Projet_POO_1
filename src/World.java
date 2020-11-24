import Personnages.Personnage;
import Personnages.Player;
import Tiles.Tile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class World {

	private ArrayList<Tile> tiles;

	public World() {
		this.tiles = readJSON();
	}

	public void init() {
		// TODO - implement World.init
		throw new UnsupportedOperationException();
	}

	public ArrayList<Tile> readJSON() throws Exception
	{
		JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("Data/data.json"));

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