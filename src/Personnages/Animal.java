package Personnages;

import Items.Item;
import Tiles.Tile;

import java.util.List;
import java.util.Random;

public class Animal extends Personnage
{
    public Animal(Tile tile, List<Item> items, String name, int health, List<String> speeches)
    {
        super(tile, items, name, health, speeches);
    }

    public Animal(Tile tile, List<Item> items, String name, List<String> speeches)
    {
        super(tile, items, name, speeches);
    }


    public void pet()
    {
        // TODO
    }

}