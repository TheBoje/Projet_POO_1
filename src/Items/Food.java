package Items;

import Personnages.Personnage;
import Personnages.Player;


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
        if(perso instanceof Player)
        {
            // TODO appelle la modification de la jauge de bouffe du personnage
            ((Player) perso).fillHunger(this.nutValue);
        }
    }
}
