package Items;

import Personnages.Personnage;

public class RangeWeapons extends Weapon
{
    private int ammunitions;

    public RangeWeapons(String name, int dmg)
    {
        super(name, dmg);
    }

    public RangeWeapons(String name, int dmg, int value)
    {
        super(name, dmg, value);
    }

    public void use(Personnage personnage)
    {
        if(personnage != null)
        {
            if(this.ammunitions > 0)
            {
                personnage.takeDamage(this.getDamages());
                this.ammunitions--;
            }
            else
            {
                this.ammunitions = 0;
                System.out.println("You ran out of bullets");
            }
        }
        else
        {
            System.out.println("You need a target to fire at"); // TODO exception ?
        }
    }
}
