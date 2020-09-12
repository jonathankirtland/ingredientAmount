package src;
import java.util.LinkedList;

public class Recipe
{
   private LinkedList<Ingredient> list = new LinkedList<>();

    public Recipe()
    {

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

}
