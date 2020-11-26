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

    // Affiche les caractéristiques visible par le joueur
    public void print()
    {
        System.out.println("----- " + this.getName() + " -----");
        System.out.println(this.isAlive() ? "Vivant" : "Mort");
    }

    // Ecrit une ligne de dialogue choisi aléatoirement dans la liste "speeches"
    public void pet()
    {
        System.out.println("- " + this.getRandomSpeech());
    }

}