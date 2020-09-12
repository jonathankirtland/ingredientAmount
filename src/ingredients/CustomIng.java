package src.ingredients;
import src.Type;
import src.Ingredient;
public class CustomIng implements Ingredient
{
    String myName;
    double amount;
    Type myType;

    //constructor for ingredients that have a fixed size
    public CustomIng(String name, int amount)
    {
        this.myName = name;
        this.amount = amount;
    }
    //constructor for ingredients with variable size
    public CustomIng(String name, double amount, Type type)
    {
        this.myName = name;
        this.amount = amount;
        this.myType = type;
    }
}
