package TestsItems;

import Items.*;
import Personnages.NPC;
import Personnages.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

public class ItemTest
{
    private final String CLOTHE_NAME_1 = "Caleçon";
    private final int WARMNESS_1 = 3;

    private final String FOOD_NAME_1 = "Kebab";
    private final int NUT_VALUE_1 = 5;

    private final String WEAPON_NAME_1 = "P90";
    private final int DAMAGES_1 = 6;
    private final int AMMOS_1 = 30;

    private final String WEAPON_NAME_1_1 = "Karg98";
    private final int DAMAGES_1_1 = 7;

    private final String WEAPON_NAME_2 = "Coutal";
    private final int DAMAGES_2 = 3;

    private final String WEAPON_NAME_3 = "Grenade";
    private final int DAMAGES_3 = 9;

    private final int HOW_MANY_STARVE = 5;


    private Clothes clothe;
    private Food food;

    private RangeWeapon rangeWeapon1;
    private RangeWeapon rangeWeapon2;
    private MeleeWeapon meleeWeapon;
    private ThrowWeapon throwWeapon;

    private Player player;
    private NPC npc;

    void itemTestInit()
    {
        this.clothe = new Clothes(CLOTHE_NAME_1, WARMNESS_1);
        this.food = new Food(FOOD_NAME_1, NUT_VALUE_1);


        this.rangeWeapon1 = new RangeWeapon(WEAPON_NAME_1, DAMAGES_1, AMMOS_1);
        this.rangeWeapon2 = new RangeWeapon(WEAPON_NAME_1_1, DAMAGES_1_1);
        this.meleeWeapon = new MeleeWeapon(WEAPON_NAME_2, DAMAGES_2);
        this.throwWeapon = new ThrowWeapon(WEAPON_NAME_3, DAMAGES_3);

        this.player = new Player(null, new ArrayList<>(), "Bertrand", new ArrayList<>());
    }

    // Test si les armes à distance perdent des munitions quand on les utilise sur une personne existante
    @Test
    void RangeWeapon1()
    {
        npc = new NPC(null, new ArrayList<>(), "Benoit", new ArrayList<>());

        // On vérifie que on à bien une balle en moins et que le npc à perdu des points de vie
        int ammos = this.rangeWeapon1.getAmmunitions();
        int health = npc.getHp();
        this.rangeWeapon1.use(npc);
        ammos--;
        assertEquals(ammos, this.rangeWeapon1.getAmmunitions());
        assertNotEquals(health, npc.getHp());


        // On vérifie que quand l'arme n'a plus de balles, on ne tire pas et ne blesse pas le npc
        health = this.npc.getHp();
        this.rangeWeapon2.use(npc);
        assertEquals(RangeWeapon.DEFAULT_AMMOS, this.rangeWeapon2.getAmmunitions());
        assertEquals(health, npc.getHp());
    }

    // Test du comportement des armes à distance quand on les utilise sur une personne inexistante
    @Test
    void RangeWeapon2()
    {
        int ammos = this.rangeWeapon1.getAmmunitions();
        this.rangeWeapon1.use(null);
        assertEquals(ammos, this.rangeWeapon1.getAmmunitions());

        this.rangeWeapon2.use(null);
        assertEquals(RangeWeapon.DEFAULT_AMMOS, this.rangeWeapon1.getAmmunitions());
    }

    @Test
    void MeleeWeapon()
    {
        npc = new NPC(null, new ArrayList<>(), "Benoit", new ArrayList<>());

        int hp = npc.getHp();
        this.meleeWeapon.use(npc);
        assertNotEquals(hp, npc.getHp());
    }

    @Test
    void ThrowWeapon()
    {
        npc = new NPC(null, new ArrayList<>(), "Benoit", new ArrayList<>());

        int hp = npc.getHp();
        assertFalse(this.throwWeapon.getHasBeenLaunched());
        this.throwWeapon.use(npc);
        assertNotEquals(hp, npc.getHp());
        assertTrue(this.throwWeapon.getHasBeenLaunched());
    }

    // On vérifie que la nourriture rend de la faim et qu'elle se consomme
    @Test
    void Food1()
    {
        int hunger = this.player.getHunger();

        for(int i = 0; i < HOW_MANY_STARVE; i++)
        {
            this.player.starve();
        }

        this.food.use(this.player);
        assertNotEquals(hunger, this.player.getHunger());
        assertTrue(this.food.getHasBeenAte());
    }

    @Test
    void Food2()
    {
        Food f = new Food(FOOD_NAME_1, NUT_VALUE_1);

        assertTrue(f.getHasBeenAte());
        f.use(null);
        assertTrue(f.getHasBeenAte());
        f.use(this.npc);
        assertTrue(f.getHasBeenAte());
    }

}
