package src;

public enum Type
{
    TEASPOON(5), TABLESPOON(15), CUP(235);

    private double myMl;
    private Type(double ml)
    {
        this.myMl = ml;
    }

    public double getMl()
    {
        return myMl;
    }
}
