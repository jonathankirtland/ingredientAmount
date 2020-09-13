package src;
public class Ingredient
{
    String myName;
    double amount;
    Type myType;

    //constructor for ingredients that have a fixed size
    public Ingredient(String name, double amount)
    {
        this.myName = name;
        this.amount = amount;
        myType = null;
    }
    //constructor for ingredients with variable size
    public Ingredient(String name, double amount, Type type)
    {
        this.myName = name;
        this.amount = amount;
        this.myType = type;
    }
    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public double getAmount()
    {
        return amount;
    }
    public void setType(Type type)
    {
        this.myType = type;
    }

    public String getMyName() {
        return myName;
    }

    public String getType()
    {
        return myType.name();
    }

    public Boolean isNull()
    {
        if (myType == null)
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return myName;
    }
    public double getMl()
    {
        return amount * myType.getMl();
    }


}
