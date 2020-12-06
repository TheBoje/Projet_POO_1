package TestsPersonnages;

import Items.Item;
import Personnages.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HumanTest extends PersonnageTest
{

    Player p1;
	List<String> sps = new ArrayList<String>();

	@BeforeEach
	void init ()
	{
		sps.add("blyat");
		p1 = new Player(null, new ArrayList<Item>(), null, sps);
	}

	@Test
	void talk()
	{
		p1.talk();
	}
}