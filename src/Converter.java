package src;

import java.util.LinkedList;

public class Converter
{
    private LinkedList<Integer> elements;
    private int conversionFactor;
    public Converter(int conversionFactor)
    {
        this.conversionFactor = conversionFactor;
    }

    public double convertFixed(Ingredient ingredient)
    {
        return ingredient.getAmount() * conversionFactor;
    }

    //returns a linked list of the breakdown of the types to use (element 1 = cups)
    // element 2 = tablespoons and element 3 = teaspoons
    public LinkedList<Integer> convert(Ingredient ingredient)
    {
        elements = new LinkedList<>();
        double myml = ingredient.getMl() * conversionFactor;
        int numcups =(int) (myml / Type.CUP.getMl());
        myml %= Type.CUP.getMl();
        int numHalfCups = (int) (myml/Type.HALFCUP.getMl());
        myml%= Type.HALFCUP.getMl();
        int numThirdCups = (int) (myml/Type.THIRDCUP.getMl());
        myml%= Type.HALFCUP.getMl();
        int numFourCups = (int) (myml/Type.FOURTHCUP.getMl());
        myml%= Type.HALFCUP.getMl();
        int numtblsp = (int) (myml/Type.TABLESPOON.getMl());
        myml %= Type.TABLESPOON.getMl();
        int numtsp = (int) (myml/Type.TEASPOON.getMl());

        elements.add(numcups);
        elements.add(numHalfCups);
        elements.add(numThirdCups);
        elements.add(numFourCups);
        elements.add(numtblsp);
        elements.add(numtsp);
        return elements;

    }



}
