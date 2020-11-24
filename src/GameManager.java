import Personnages.*;

public class GameManager {

	private World world;
	private Interpreteur interpreteur;
	private Player player;

	public GameManager() {
		this.world = new World();
		this.interpreteur = new Interpreteur();
		this.player = world.getPlayer();
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param direction
	 */
	public void go(String direction) {
		// TODO - implement GameManager.go
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param to
	 */
	public void talk(String to) {
		// TODO - implement GameManager.talk
		throw new UnsupportedOperationException();
	}

	public void nextTurn() {
		// TODO - implement GameManager.nextTurn
		throw new UnsupportedOperationException();
	}

}