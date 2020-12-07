package TestsPersonnages;

import Items.*;
import Personnages.NPC;
import Personnages.NoSpeechAvailable;
import Personnages.Player;
import Tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonnageTest {

    private final ArrayList<String> speeches = new ArrayList<>();
    private final ArrayList<Item> inventory = new ArrayList<>();
    private final Clothes clothes = new Clothes("Coat", 7);
    private final Food food = new Food("Pudding", 4);
    private final Misc misc = new Misc("junk", 1);
    private final MeleeWeapon melee = new MeleeWeapon("knife", 2);
    private final String name = "Robert";
    private Player p1;
    private Player p2;
    private NPC npc1;
    private final Tile tile = new Tile();

    @BeforeEach
    void init() {
        speeches.add("Bonjour");
        speeches.add("Gros moche");
        speeches.add("should be in English tho");
        inventory.add(clothes);
        inventory.add(food);
        inventory.add(misc);
        inventory.add(melee);
    }

    //Testing Personnage constructor on Subclasses and null parameters
    @Test
    void Player() throws NoSpeechAvailable {
        p1 = new Player(null, null, null, null);
        assertNull(p1.getTile());
        assertNull(p1.getItems());
        assertNull(p1.getName());
        try {
            assertNull(p1.getRandomSpeech());
        } catch (NoSpeechAvailable e) {
            System.out.println(e);
        }

        p2 = new Player(tile, inventory, name, speeches);
        assertEquals(inventory, p2.getItems());
        assertEquals(name, p2.getName());
        assertNotNull(p2.getRandomSpeech());
        npc1 = new NPC(tile, inventory, name, speeches);
        assertEquals(inventory, npc1.getItems());
        assertEquals(name, npc1.getName());
        assertNotNull(npc1.getRandomSpeech());
    }

    //Simple getter test on name
    @Test
    void getName() {
        p1 = new Player(tile, inventory, name, speeches);
        assertEquals(name, p1.getName());
    }

    //Testing is a character dies when hp == 0
    @Test
    void isAlive() {
        p1 = new Player(tile, inventory, name, speeches);
        p2 = new Player(tile, inventory, name, speeches);
        assertTrue(p1.isAlive());
        assertTrue(p2.isAlive());
        p1.takeDamage(5);
        assertTrue(p1.isAlive());
        p1.takeDamage(5);
        assertFalse(p1.isAlive());
        p2.takeDamage(20);
        assertFalse(p2.isAlive());

    }

    //Testing a character attacking another one
    @Test
    void attack() throws InvalidTarget {
        p1 = new Player(tile, inventory, name, speeches);
        p2 = new Player(tile, inventory, name, speeches);
        int p2HP = p2.getHp();
        int p1HP = p1.getHp();
        p1.use(melee, p2);
        assertEquals(p2.getHp(), p2HP - melee.getDamages());
        p2.attack(melee, p1);
        assertEquals(p1.getHp(), p1HP - melee.getDamages());

    }

    //Testing if a character can kill another one
    @Test
    void attackKill() throws InvalidTarget {
        p1 = new Player(tile, inventory, name, speeches);
        p2 = new Player(tile, inventory, name, speeches);
        while (p2.isAlive()) {
            p1.use(melee, p2);
        }
        assertFalse(p2.isAlive());
    }

    //Testing the behavior of the method attack with null parameters
    @Test
    void attackNull() throws InvalidTarget {
        p1 = new Player(tile, inventory, name, speeches);
        p2 = new Player(tile, inventory, name, speeches);
        Integer p1HP = p1.getHp();
        try {
            p1.attack(melee, null);
            p2.attack(null, null);
        } catch (InvalidTarget e) {
            System.out.println(e);
        }
        p2.attack(null, p1);
        assertEquals(p1HP - 1, p1.getHp());

    }

    //Testing if a character takes damages the right way
    @Test
    void takeDamage() {
        p1 = new Player(tile, inventory, name, speeches);
        int dmg = 5, p1HP = p1.getHp();
        p1.takeDamage(dmg);
        assertEquals(p1HP - dmg, p1.getHp());
    }

    //Testing if an item is added correctly in the inventory
    @Test
    void addItem() {
        p1 = new Player(tile, inventory, name, speeches);
        Item item = new Misc("swampWater", 3);
        p1.addItem(item);
        assertTrue(p1.getItems().contains(item));
    }

    //Testing if an item is removed correctly form the inventory
    @Test
    void removeItem() {
        p1 = new Player(tile, inventory, name, speeches);
        p1.removeItem(melee);
        assertFalse(p1.getItems().contains(melee));
        Item item = new Misc("swampWater", 3);
        p1.addItem(item);
        p1.removeItem(item);
        assertFalse(p1.getItems().contains(item));
    }

    //Testing if a String is obtained
    @Test
    void getRandomSpeech() throws NoSpeechAvailable {
        p1 = new Player(tile, inventory, name, speeches);
        assertNotEquals(null, p1.getRandomSpeech());
    }

    //Setting the tile of a player
    @Test
    void setTile() {
        Tile tile = new Tile();
        p1 = new Player(tile, inventory, name, speeches);
        p1.setTile(tile);
        assertEquals(tile, p1.getTile());
    }

    //Setting a null tile to player because.. why not ?
    @Test
    void setTileNull() {
        p1 = new Player(tile, inventory, name, speeches);

        p1.setTile(null);
        assertNull(p1.getTile());
    }
}