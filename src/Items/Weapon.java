package Items;

import Personnages.Personnage;

public class Weapon extends Item
{
    private int damages;

    public Weapon(String name, int dam)
    {
        super(name);
        this.damages = dam;
    }


    public void use(Personnage personnage)
    {
        personnage.takeDamage(this.damages);
    }
}
