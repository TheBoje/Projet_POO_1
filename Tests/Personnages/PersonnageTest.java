package Personnages;
import Items.*;
import Tiles.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonnageTest {

    private Player p1;
    private Player p2;


    @Test
    void Player(){
        p1 = new Player(null, null, null,null );
        assertEquals(null, p1.getTile());
        assertEquals(null, p1.getItems());
        assertEquals(null, p1.getName());
        assertEquals(null, p1.getRandomSpeech());
    }


    @Test
    void getName() {
        String name = "Arouf";
        p1 = new Player(null, null,name,null );
        assertEquals(name, p1.getName());
    }

    @Test
    void isAlive() {
        p1 = new Player(null, null,null,null );
        p2 = new Player(null, null,null,null );
        assertEquals(true, p1.isAlive());
        p1.takeDamage(10);
        assertEquals(false, p1.isAlive());
        p2.takeDamage(20);
        assertEquals(false, p2.isAlive());

    }

    @Test
    void attack() {
        p1 = new Player(null, null,null,null );
        p2 = new Player(null, null,null,null );
        int p2HP = p2.getHp();
        int dmg = 5;
        try {
            p1.attack(p2, dmg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotEquals(p2HP, p2.getHp());
        assertEquals(p2HP-dmg, p2.getHp());
    }

    @Test
    void attackKill() {
        p1 = new Player(null, null,null,null );
        p2 = new Player(null, null,null,null );
        int p2HP = p2.getHp();
        int dmg = p2HP;
        try {
            p1.attack(p2, dmg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotEquals(p2HP, p2.getHp());
        assertEquals(0, p2.getHp());
        assertEquals(false, p2.isAlive());
    }

    @Test
    void attackNull() {
        p1 = new Player(null, null,null,null );
        p2 = new Player(null, null,null,null );
        int p2HP = p2.getHp();
        try {
            p1.attack(p2, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(p2HP, p2.getHp());
        assertEquals(true, p2.isAlive());
    }


    @Test
    void takeDamage() {
        p1 = new Player(null, null,null,null );
        int dmg = 5, p1HP = p1.getHp();
        p1.takeDamage(dmg);
        assertEquals(p1HP - dmg, p1.getHp());
    }

    @Test
    void addItem() {
        p1 = new Player(null, new ArrayList<Item>(),null,null );
        Item item = new Item("swampWater");
        p1.addItem(item);
        assertEquals(true, p1.getItems().contains(item));
    }

    @Test
    void removeItem() {
        p1 = new Player(null, new ArrayList<Item>(),null,null );
        Item item1 = new Item("swampWater");
        Item item2 = new Item("ogreJuice");
        p1.addItem(item1);
        p1.addItem(item2);
        p1.removeItem(item1);
        assertNotEquals(true, p1.getItems().contains(item1));
    }

    @Test
    void getRandomSpeech()
    {
        List<String> sps = new ArrayList<String>();
        sps.add("blyat");
        p1 = new Player(null, new ArrayList<Item>(),null,sps );
        assertNotEquals(null, p1.getRandomSpeech());
    }

    @Test
    void setTile() {
        Tile tile = new Tile();
        p1 = new Player(null, new ArrayList<Item>(),null,null );
        p1.setTile(tile);
        assertEquals(tile, p1.getTile());
    }
     @Test
    void setTileNull() {
        p1 = new Player(null, new ArrayList<Item>(),null,null );
        p1.setTile(null);
        assertEquals(null, p1.getTile());
    }

}