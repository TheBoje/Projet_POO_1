package Items;

import Personnages.Personnage;

public class ThrowWeapon extends Weapon
{
    private boolean hasBeenLaunched;

    public ThrowWeapon(String name, int dmg)
    {
        super(name, dmg);
        this.hasBeenLaunched = false;
    }

    public ThrowWeapon(String name, int dmg, int value)
    {
        super(name, dmg, value);
        this.hasBeenLaunched = false;
    }

    public void use(Personnage personnage)
    {
        if(personnage != null)
        {
            if(!this.hasBeenLaunched)
            {
                this.hasBeenLaunched = true;
                personnage.takeDamage(this.getDamages());
            }
            else
            {
                System.out.println("You already launched your weapon");
            }
        }
        else
        {
            System.out.println("You need a target");
        }
    }

    public boolean getHasBeenLaunched()
    {
        return this.hasBeenLaunched;
    }

    @Override
    public String getUsage()
    {
        return "ouais";// TODO This
    }
}
