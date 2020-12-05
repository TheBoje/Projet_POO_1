package Items;

import Personnages.Personnage;

public class RangeWeapon extends Weapon
{
    public static final int DEFAULT_AMMOS = 0;
    public static final int MAX_AMMOS = 30;

    private int ammunitions;

    public RangeWeapon(String name, int dmg)
    {
        super(name, dmg);
        this.ammunitions = DEFAULT_AMMOS;
    }

    public RangeWeapon(String name, int dmg, int value, int ammos)
    {
        super(name, dmg, value);
        this.ammunitions = ammos;
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

    public int getAmmunitions()
    {
        return this.ammunitions;
    }

    public void howManyAmmosLeft()
    {
        System.out.println("You have " + this.ammunitions + " ammunition left");
    }

    public String toString()
    {
        String str = super.toString();
        str += "\tmunitions - " + this.ammunitions + "\n";
        return str;
    }

}
