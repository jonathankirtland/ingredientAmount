package src.ingredients;

import src.Ingredient;
import src.Type;

public class Flour implements Ingredient
{
    private double amount;

    public Flour(Type type)
    {
        amount = type.getMl();
    }

    @Override
    public double getMl()
    {
        return amount;
    }
}
