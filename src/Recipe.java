package src;
import java.util.LinkedList;

public class Recipe
{
   private LinkedList<Ingredient> list = new LinkedList<>();
   private double conversionAmount;

    public Recipe(double conversionAmount)
    {
        this.conversionAmount = conversionAmount;
    }
    public void addToRecipe(Ingredient ingredient)
    {
        list.add(ingredient);
        System.out.println("added to recipe");
    }
    public void removeFromRecipe(Ingredient ingredient)
    {
        list.remove(ingredient);
        System.out.println("removed ingredient" + ingredient);
    }
    public void clear()
    {
        list.clear();
    }
      
    @Override
    public String toString()
    {
        Converter convert = new Converter(conversionAmount);
        String myString = "";
        for (Ingredient ing : list)
        {
            if (ing.isNull())
            {
                myString += ing.getMyName() + ":      amount: " + convert.convertFixed(ing) + "\n";
            }
            else
            {
                convert.convert(ing);
                myString += ing.getMyName() + ":      amount: " + convert;

            }
        }
        return myString;
    }
/*
    public static void main(String[] args)
    {
        Ingredient egg = new Ingredient("Egg", 2);
        Ingredient flour = new Ingredient("flour", 3, Type.CUP);
        Recipe recipe = new Recipe(4);
        recipe.addToRecipe(egg);
        recipe.addToRecipe(flour);
        System.out.println(recipe);
    }

 */
    
}
