package Items;

import Personnages.Personnage;

public class Weapon extends Item
{
    private int damages;
    private int ammunitions;

    public Weapon(String name, int dam, int ammos)
    {
        super(name);
        this.damages = dam;
        this.ammunitions = ammos;
    }


    public void use(Personnage personnage) throws Exception
    {
        if(this.ammunitions > 0)
        {
            personnage.takeDamage(this.damages);
            this.ammunitions--;
        }
        else
        {
            this.ammunitions = 0;
            throw new Exception("You run out of bullets");
        }

    }
}
