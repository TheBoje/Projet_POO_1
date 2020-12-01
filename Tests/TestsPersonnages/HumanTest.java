package TestsPersonnages;

import Items.Item;
import Personnages.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HumanTest extends PersonnageTest
{

    Player p1;

	@Test
	void talk()
	{
		List<String> sps = new ArrayList<String>();
		sps.add("blyat");
		p1 = new Player(null, new ArrayList<Item>(), null, sps);
		p1.talk();
	}
}