package Personnages;

import Items.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest extends PersonnageTest {

    Player p1;

    @Test
    void talk() {
        List<String> sps = new ArrayList<String>();
        sps.add("blyat");
        p1 = new Player(null, new ArrayList<Item>(),null,sps );
        p1.talk();
    }
}