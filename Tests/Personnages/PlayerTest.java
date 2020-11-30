package Personnages;

import Items.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest extends HumanTest {

    private Player p1;
    private Player p2;

    @Test
    void trade() throws Exception
    {
        p1 = new Player(null, new ArrayList<Item>(), null,null );
        Item itemIn = new Item("Some");
        Item itemOut = new Item("Thing");
        p1.addItem(itemOut);
        assertEquals(true,p1.trade(itemIn, itemOut));
    }

    @Test
    void tradeFail() throws Exception
    {
        p1 = new Player(null, new ArrayList<Item>(), null,null );
        Item itemIn = new Item("Some");
        Item itemOut = new Item("Thing");
        assertEquals(false,p1.trade(itemIn, itemOut));
    }

    @Test
    void tradeNull()
    {
        p1 = new Player(null, new ArrayList<Item>(), null,null );
        try {
            assertEquals(true,p1.trade(null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void tradeSemiNull()
    {
        p1 = new Player(null, new ArrayList<Item>(), null,null );
        Item itemIn = new Item("Some");
        try {
            assertEquals(false,p1.trade(itemIn, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        p1.addItem(itemIn);
        try {
            assertEquals(false,p1.trade(null, itemIn));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void pet()
    {
        p1 = new Player(null, new ArrayList<Item>(), null,null );
        try
        {
            p1.pet(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void take()
    {

    }

    @Test
    void eat() {
    }

    @Test
    void wear() {
    }

    @Test
    void takeOff() {
    }

    @Test
    void testPrint() {
    }

    @Test
    void printDebug() {
    }
}