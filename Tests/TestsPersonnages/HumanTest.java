package TestsPersonnages;

import Items.Item;
import Personnages.GameWonException;
import Personnages.NoSpeechAvailable;
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
	void initHuman ()
	{
		sps.add("blyat");
		p1 = new Player(null, new ArrayList<Item>(), null, sps);
	}

	@Test
	void talk() throws NoSpeechAvailable, GameWonException
	{
		System.out.println(p1.talk());
	}
}