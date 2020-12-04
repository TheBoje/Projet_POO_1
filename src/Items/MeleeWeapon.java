package Items;

import Personnages.Personnage;

public class MeleeWeapon extends Weapon
{
    public MeleeWeapon(String name, int dmg)
    {
        super(name, dmg);
    }

    public MeleeWeapon(String name, int dmg, int value)
    {
        super(name, dmg, value);
    }

    public void use(Personnage personnage)
    {
        if(personnage != null)
        {
            personnage.takeDamage(this.getDamages());
        }
        else
        {
            System.out.println("You need a target to stab"); // TODO exception ?
        }
    }


}
