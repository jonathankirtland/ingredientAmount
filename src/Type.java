package src;

public enum Type
{
    TEASPOON(5), TABLESPOON(15), CUP(240), HALFCUP(120), THIRDCUP(80), FOURTHCUP(60);

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
