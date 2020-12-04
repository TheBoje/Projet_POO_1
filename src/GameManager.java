import Crossings.Crossing;
import Personnages.Player;
import Tiles.Direction;

public class GameManager
{

	/***********************************ATTRIBUTES***********************************/

	private final World world;
	private final Interpreteur interpreteur;
	private Player player;

	/***********************************CONSTRUCTOR***********************************/

	public GameManager()
	{
		this.world = new World(5, 8);
		this.interpreteur = new Interpreteur(this);
		try
		{
			this.player = world.getPlayer();
		} catch (Exception e)
		{
			System.out.println("Can't find the player");
		}
	}

	/***********************************METHODS***********************************/

	public void go(String direction) throws Exception
	{
		this.world.movePlayer(this.player, Direction.stringToDir(direction));
	}

	public void talk(String to)
	{
		System.out.format("The player is asked to talk to [%s]\n", to);
		// TODO - implement GameManager.talk
		//throw new UnsupportedOperationException();
	}

	public void use(String arg)
	{
		// TODO me
	}

	public void use(String arg1, String arg2)
	{
		// TODO me
	}

	public void nextTurn()
	{
		try
		{
			interpreteur.read();
		} catch (Exception e)
		{
			System.out.format("Error: %s\n", e.getMessage());
			this.nextTurn();
		}
	}

	/***********************************GETTERS***********************************/
	public void getDirection()
	{
		Crossing[] playerCrossings = this.player.getTile().getCrossings();
		for (int i = 0; i < playerCrossings.length; i++)
		{
			if (playerCrossings[i] != null)
			{
				System.out.format("\t[%s] %s - %s\n",
						Direction.intToDirection(i).toString(),
						playerCrossings[i].getClass().getSimpleName(),
						playerCrossings[i].isOpen() ? "open" : "close");
			}
		}
	}

	public void getUse()
	{

	}

	/***********************************SETTERS***********************************/

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	/***********************************DISPLAY***********************************/
	public void printWorld()
	{
		this.world.print();
	}


}