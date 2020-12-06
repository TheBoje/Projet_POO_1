package Items;

import Personnages.Personnage;

public class Misc extends Item
{
    public Misc(String name, int value)
    {
        super(name, value);
    }

    public void use(Personnage personnage)
    {
        System.out.println("It must be usefull somewhere ...");
    }

    @Override
    public String getUsage()
    {
        return "It must be usefull somewhere";
    }
}
