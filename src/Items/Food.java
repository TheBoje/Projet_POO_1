package Items;

import Personnages.Personnage;

public class Food extends Item
{

    private int nutValue;

    public Food(String name, int nVal)
    {
        super(name);
        this.nutValue = nVal;
    }

    public void use(Personnage perso)
    {
    }
}
