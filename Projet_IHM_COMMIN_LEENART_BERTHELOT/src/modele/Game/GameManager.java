package modele.Game;

import modele.Crossings.CantOpenCrossing;
import modele.Crossings.ClosedCrossing;
import modele.Crossings.Crossing;
import modele.Interpreteur.Interpreteur;
import modele.Interpreteur.Order;
import modele.Items.InvalidTarget;
import modele.Items.Item;
import modele.Personnages.*;
import modele.Tiles.Direction;
import modele.Tiles.Tile;
import modele.Tiles.UnknownDirection;

import java.util.List;
import java.util.Random;

public class GameManager
{

	/***********************************ATTRIBUTES***********************************/

	private final World world;
	private final Interpreteur interpreteur;
	private Player player;
	private Random rn;

	/***********************************CONSTRUCTOR***********************************/

	public GameManager()
	{
		this.rn = new Random();
		this.world = new World(this.rn.nextInt(20) + 5); // On génère entre 5 et 25 tiles
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

	public String initGame()
	{
		 String str = ("You were in a touristic flight over the arctic ice floe. But as if the karma wanted to punish you for " +
				"\nbeing part of such a polluting trip, your plane crashed and you're the only survivor...");
	 	str +=("You've learned your lesson though and now you have to make your way out of here before starving or \nfreezing to death. Your best " +
				"shot here seems to be : finding someone and explain them what happened to you. \n" +
				"Type your actions and press enter to confirm it, try typing \"help\" and see everything you can do and how.");

	 	return str;
	}

	public void endGame()
	{
		System.out.println("You won!\nThe Chief scientist saved you!\nThanks for playing our game");
	}

	// Lancé par l'interpréteur, déplace le player dans la direction <dir>.
	// Retourne ClosedCrossing si le crossing est fermé, et UnknownDirection
	// si l'input n'est pas valide
	public void go(Direction dir) throws ClosedCrossing, UnknownDirection
	{
		Tile actualTile = this.player.getTile();
		Tile targetTile = this.world.getTile(actualTile.getNextTileID(dir));
		if (actualTile.getCrossing(dir) != null)
		{
			if (!actualTile.getCrossing(dir).isOpen())
			{
				throw new ClosedCrossing();
			}
			else
			{
				actualTile.remotePersonnage(this.player);
				targetTile.addPersonnage(this.player);
				this.player.setTile(targetTile);
				this.player.starve();
				this.player.chill();
			}
		}
		else
		{
			throw new UnknownDirection();
		}
	}

	// Lancé par l'interpréteur. Demande un speech au personnage ciblé.
	// Le personnage ciblé est représenté par son index dans la liste
	// des personnages de la tile.
	public String talk(int index) throws InputError, NoSpeechAvailable, GameWonException
	{
		if (index >= 0 && index < this.player.getTile().getPersonnages().size())
		{
			return this.player.getTile().getPersonnage(index).talk();
			//System.out.format("[%s]: %s\n", this.player.getTile().getPersonnage(index).getName(), talk_res);
		}
		else
		{
			throw new InputError();
		}
	}

	// Lancé par l'interpréteur. Lance l'utilisation de l'objet sélectionné.
	// Le premier argument est l'index de l'objet dans l'inventairesélectionné,
	// et le deuxieme argument est l'index du personnage cible.
	// Note: il faut que l'objet existe dans l'inventaire.
	public void use(String[] args) throws InputError, InvalidTarget
	{
		int item_index = Integer.parseInt(args[0]);
		int character_index = Integer.parseInt(args[1]);
		if (character_index >= 0 && character_index < this.player.getTile().getPersonnages().size())
		{
			this.player.use(this.player.getItem(item_index), this.player.getTile().getPersonnage(character_index));
		}
		else
		{
			throw new InputError();
		}
	}

	public void use(int item_index, int character_index) throws InputError, InvalidTarget
	{
		if (character_index >= 0 && character_index < this.player.getTile().getPersonnages().size())
		{
			this.player.use(this.player.getItem(item_index), this.player.getTile().getPersonnage(character_index));
		}
		else
		{
			throw new InputError();
		}
	}

	// Lancé par l'interpréteur. Permet de prendre l'objet d'index précisé
	// sur la tile pour le placer dans l'inventaire du player.
	public void take(int index) throws InputError
	{
		if (index >= 0 && index < this.player.getItems().size())
		{
			Item toTake = this.player.getTile().getItem(index);
			this.player.take(toTake);
			this.player.getTile().take(toTake);
		}
		else
		{
			throw new InputError();
		}

	}

	// Gestion des tours du player. A chaque tour, le player entre
	// une commande dans la console. Il n'y a pas de limite de tours
	// et le dernier tour est pour l'instant defini par l'action de
	// discuter avec le chef scientifique (ou mourir).
	public boolean nextTurn()
	{
		if (player.isAlive())
		{
			try
			{
				List<Personnage> persos = this.player.getTile().getPersonnages();
				for(Personnage perso : persos)
				{
					if(perso instanceof Animal)
					{
						((Animal) perso).protectTerritory(this.rn);
					}
					else if(perso instanceof NPC)
					{
						((NPC) perso).react(persos.get(this.rn.nextInt(persos.size())));
					}
				}

				//interpreteur.read();
			} catch (Exception e)
			{
				if (e instanceof GameWonException)
				{
					this.endGame();
					this.quit();
				}
				else
				{
					System.out.format("Error: %s\n", e.getClass().getSimpleName());
					if (e.getMessage() != null)
					{
						System.out.format("%s\n", e.getMessage());
					}
					//this.nextTurn();
				}
			}
			return true;
		}
		else
		{
			System.out.format("player died\n");
			return false;
		}
	}

	// Lancé par l'interpréteur. Permet de lancé l'ouverture d'un crossing
	// qui est fermé. La direction d'input représente la direction cible pour
	// l'ouverture du crossing
	public void open(Direction dir) throws CantOpenCrossing, UnknownDirection
	{
		Tile tile = this.player.getTile();
		if (tile != null)
		{
			tile.getCrossing(dir).tryOpen(this.player.getItems());
		}
		else
		{
			throw new CantOpenCrossing();
		}
	}

	// Ferme l'application
	public void quit()
	{
		System.out.println("Closing app");
		this.interpreteur.closeScanner();
		System.exit(1);
	}

	// Lance le message d'aide de tous les ordres (voir order.java)
	public void help()
	{
		System.out.format("LIST OF COMMANDS:\n");
		Order[] orders = Order.values();
		for (Order o : orders)
		{
			help(o);
		}
	}

	// Lance le message d'aide de l'ordre précisé
	public void help(Order order)
	{
		System.out.format("[%s] %s\n", order.getString(), order.getHelpMessage());
	}

	// Lancé par l'interpréteur. Affiche tous les crossings
	// disponibles, tous les personnages présents sur la
	// tile et tous les objets sur la tile.
	public void look() throws UnknownDirection
	{
		Tile tile = this.player.getTile();
		Crossing[] crossings = tile.getCrossings();
		int count = 0;
		for (Crossing c : crossings)
		{
			if (c != null)
			{
				count++;
			}
		}
		System.out.format("This tile contains;\n[%d] crossings:\n", count);
		this.getDirection();
		System.out.format("[%d] characters:\n", tile.getPersonnages().size());
		this.getPersonnagesOnTile();
		System.out.format("[%d] items:\n", tile.getItems().size());
		this.getItemsOnTile();
	}

	/***********************************GETTERS***********************************/
	// Liste toutes les directions de la tile
	public Crossing[] getDirection() throws UnknownDirection
	{
		return this.player.getTile().getCrossings();
		/*Crossing[] playerCrossings = this.player.getTile().getCrossings();
		for (int i = 0; i < playerCrossings.length; i++)
		{
			if (playerCrossings[i] != null)
			{
				System.out.format("\t[%s] %s - %s\n",
						Direction.intToDirection(i).toString(),
						playerCrossings[i].getClass().getSimpleName(),
						playerCrossings[i].isOpen() ? "open" : "close");
			}
		}*/
	}

	// Liste les usages de tous les objets dans l'inventaire du player
	public void getUsage()
	{
		List<Item> items = player.getItems();

		for (int i = 0; i < items.size(); i++)
		{
			System.out.format("\t[%d] %s - %s\n", i, items.get(i).getName() ,items.get(i).getUsage());
		}
	}

	// Liste tous les personnages présents sur la tile du player
	public List<Personnage> getPersonnagesOnTile()
	{
		return this.player.getTile().getPersonnages();
		/*
		for (int i = 0; i < this.player.getTile().getPersonnages().size(); i++)
		{
			Personnage p = this.player.getTile().getPersonnage(i);
			if (!(p instanceof Player))
			{
				System.out.format("\t[%d] %s\n", i, this.player.getTile().getPersonnage(i).getName());
			}
			else
			{
				System.out.format("\t[%d] %s (you)\n", i, this.player.getTile().getPersonnage(i).getName());
			}
		}*/
	}

	// Liste tous les items présents sur la tile du player
	public List<Item> getItemsOnTile()
	{
		//List<Item> items = this.player.getTile().getItems();
		return this.player.getTile().getItems();
		/*if (items.size() == 0)
		{
			System.out.format("\tNo item on this tile\n");
		}
		else
		{
			for (int i = 0; i < items.size(); i++)
			{
				System.out.format("\t[%d] %s\n", i, items.get(i).getName());
			}
		}*/
	}

	// Liste tous les items présents dans l'inventaire du player
	public List<Item> getInventory()
	{
		return this.player.getItems();
		//List<Item> items = this.player.getItems();
		/*if (items.size() == 0)
		{
			System.out.format("Your inventory is empty\n");
		}
		else
		{
			for (int i = 0; i < items.size(); i++)
			{
				System.out.format("\t[%d] %s\n", i, items.get(i).getName());
			}
		}*/
	}

	public World getWorld()
	{
		return this.world;
	}

	/***********************************SETTERS***********************************/
	/***********************************DISPLAY***********************************/
	public void printWorld()
	{
		this.world.print();
	}

	public void printPlayer()
	{
		this.player.print();
	}
}