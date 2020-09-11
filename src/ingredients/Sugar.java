package src.ingredients;

import src.Ingredient;
import src.Type;

public class Sugar implements Ingredient
{
    private double amount;

    public Sugar(Type amount)
    {
        this.amount = amount.getMl();
    }

    @Override
    public double getMl()
    {
        return this.amount;
    }
}
