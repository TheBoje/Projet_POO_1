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

    public Clothes(String name, int value, int warmValue)
    {
        super(name, value);
        this.warmness = warmValue;
    }

    public void use(Personnage perso)
    {
        if(perso instanceof Player)
        {
            ((Player) perso).warm(this.warmness);
            System.out.println("You wear " + this.getName());
        }
        else
        {
            System.out.println("It's freezing outside, you'd better keep this " + this.getName() + " for you");
        }
    }

    @Override
    public String toString()
    {
        String str = super.toString();
        str += "\tvaleur de la chaleur - " + this.warmness + "\n";
        return str;
    }
}
