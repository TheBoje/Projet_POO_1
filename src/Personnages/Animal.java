package Personnages;

import java.util.List;
import java.util.Random;

public abstract class Animal extends Personnage
{

    private List<String> dialogs;

    public void pet()
    {
        Random random = new Random();

        int r = random.nextInt(this.dialogs.size());
        System.out.println("- " + this.dialogs.get(r));
    }

}