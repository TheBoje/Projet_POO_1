package Items;

import Personnages.Personnage;
import Personnages.Player;

public class Clothes extends Item
{
    private int warmness;

    public Clothes(String name, int warmValue)
    {
        super(name);
        this.warmness = warmValue;
    }

    public void use(Personnage perso)
    {
        if(perso instanceof Player)
        {
            // TODO implémenter la méthode warm de player
            // perso.warm(this.warmness);
        }
    }
}
