package Personnages;

import Items.Item;
import Tiles.Tile;

import java.util.List;

public abstract class Human extends Personnage
{

    public Human(Tile tile, List<Item> items, String name, int health, List<String> speeches)
    {
        super(tile, items, name, health, speeches);
    }

    public Human(Tile tile, List<Item> items, String name, List<String> speeches)
    {
        super(tile, items, name, speeches);
    }

    public String talk(){
        return null;
    }

}