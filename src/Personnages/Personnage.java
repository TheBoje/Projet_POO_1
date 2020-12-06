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

    public Personnage(Tile t, List<Item> i, String n, int health, List<String> sp)
    {
        this.tile = t;
        this.items = i;
        this.name = n;
        this.hp = health;
        this.speeches = sp;
    }

    public Personnage(Tile t, List<Item> i, String n, List<String> sp)
    {
        this.tile = t;
        this.items = i;
        this.name = n;
        this.hp = DEFAULT_HP;
        this.speeches = sp;
    }

    /***********************************METHODS***********************************/



    public abstract void print();

    public boolean isAlive()
    {
        return (this.hp > 0);
    }



    public void attack(Weapon weapon, Personnage target) throws InvalidTarget
    {
        if (weapon == null) {
            Weapon bareHand = new MeleeWeapon("bareHands", 1);
            bareHand.use(target);
        } else {
            weapon.use(target);
        }
    }

    public void takeDamage(int amount)
    {
        this.hp -= amount;
        if(this.hp < 0)
            this.hp = 0;
    }


    public boolean addItem(Item object){
        return items.add(object);
    }

    public boolean removeItem(Item toRemove)
    {
        return items.remove(toRemove);
    }

    public String getRandomSpeech() throws NoSpeechAvailable
    {
        if (this.speeches == null || this.speeches.size() == 0)
        {
            throw new NoSpeechAvailable();
        }
        else
        {
            Random random = new Random();

            int r = random.nextInt(this.speeches.size());
            return this.speeches.get(r);
        }

    }

    /***********************************GETTERS***********************************/
    public Tile getTile()
    {
        return this.tile;
    }

    public List<Item> getItems()
    {
        return this.items;
    }

    public Item getItem(int index)
    {
        return this.items.get(index);
    }

    public String getName()
    {
        return this.name;
    }

    public int getHp()
    {
        return this.hp;
    }

    /***********************************SETTERS***********************************/
    public void setTile(Tile tile)
    {
        this.tile = tile;
    }

    public void setItems(List<Item> items)
    {
        this.items = new ArrayList<Item>();
    }
}