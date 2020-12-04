package TestsItems;

import Items.Clothes;
import Items.Food;
import Items.Item;
import Items.Weapon;
import Personnages.NPC;
import Personnages.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest
{
    private final String CLOTHE_NAME_1 = "Caleçon";
    private final int WARMNESS_1 = 3;

    private final String FOOD_NAME_1 = "Kebab";
    private final int NUT_VALUE_1 = 5;

    private final String WEAPON_NAME_1 = "P90";
    private final int DAMAGES_1 = 6;



    private Clothes clothe;
    private Food food;
    private Weapon weapon;

    private Player player;
    private NPC npc;

    void itemTestInit()
    {
        this.clothe = new Clothes(CLOTHE_NAME_1, WARMNESS_1);
        this.food = new Food(FOOD_NAME_1, NUT_VALUE_1);
        //this.weapon = new Weapon(WEAPON_NAME_1, DAMAGES_1, 3);

        this.player = new Player(null, new ArrayList<>(), "Bertrand", new ArrayList<>());
        this.npc = new NPC(null, new ArrayList<>(), "Benoit", new ArrayList<>());
    }



    @Test
    void weaponDoDamage()
    {
        int lifeBefore;

        try
        {
            lifeBefore = this.npc.getHp();

            this.weapon.use(this.npc);
            assertNotEquals(lifeBefore, this.npc.getHp());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            lifeBefore = this.player.getHp();
            this.weapon.use(this.player);

            assertNotEquals(lifeBefore, this.player.getHp());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


    }
}
