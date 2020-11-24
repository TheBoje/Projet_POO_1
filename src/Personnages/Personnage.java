package Personnages;

import Tiles.*;

import java.util.*;

import Items.*;

public abstract class Personnage
{

    private Tile tile;
    private List<Item> items;
    private static int DEFAULT_HP = 10;
    private String name;
    private int hp;
    private List<String> speeches;

    public String getName()
    {
        return this.name;
    }

    public abstract void print();

    public boolean isAlive()
    {
        return (this.hp > 0);
    }

    /**
     * @param target Cible recevant les dégats
     * @param amount Montant des dégats infligés à la cible
     */
    public void attack(Personnage target, int amount) throws Exception
    {
        if(target != null)
            target.takeDamage(amount);
        else
            throw new Exception("Character.attack : target is null");
    }

    /**
     * @param amount
     */
    public void takeDamage(int amount)
    {
        this.hp -= amount;

        if(this.hp < 0)
            this.hp = 0;
    }

}