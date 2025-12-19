//Dice class
import java.util.Random;

public class Dice{
    int sides;

    //default dice constructor
    public Dice(){
        this.sides=6;
    }

    //Dice constructor with custom number of sides
    public Dice(int numSides){
        this.sides=numSides;
    }

    //method used to return the number we got from the roll
    public int roll(){

        //rand.nextInt(n) gets number from 0(inclusive) to n(exclusive)
        //General formula for using .nextInt(n) when you want numbers between min/max:
        //minimum + random.nextInt(maximum - minimum + 1)
        //EX. want numbers between 5 and 10....then, do: r.nextInt(10[max]-5[min]+1)+5
        Random rand = new Random();
        int result = rand.nextInt(sides)+1; //get b/w 1 and num of sides(default 6)
        return result;
    }
}