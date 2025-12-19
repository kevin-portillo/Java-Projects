import java.awt.*;
import java.util.Random;


public class Duel {
    String[] fighterColors; //array to hold
    boolean dueling; //boolean to show if the fighters are dueling
    String currFighterColor; //string to show who is currently choosing to increase atk/def
    Fighter redFighter; //first player
    Fighter blueFighter; //second player
    int currRoll;
    String winner="";
    Color winnerColor;
    Fighter winnerFighter;

    boolean endOfTurn=false; //boolean to see if both fighters rolled


    Duel(){
        //create and assign fighters
        redFighter=new Fighter("Red", Color.RED, "redMasterChief.jpg");
        blueFighter=new Fighter("Blue", Color.BLUE,"blueMasterChief.png");

        //add values to premade empty array
        fighterColors= new String[]{redFighter.getColor(), blueFighter.getColor()};
        dueling=true;
        currFighterColor = "None";
        currRoll=0;



    }
    //getter for end of turn
    public boolean getEndOfTurn(){return endOfTurn;}

    //getter for fighting boolean
    public boolean isDueling() {
        return dueling;
    }

    //getter for current fighter
    public String getCurrFighterColor(){return currFighterColor;}

    //getter for current roll
    public int getCurrRoll(){return currRoll;}

    //change dueling status
    public void switchDuelingStatus(){
        dueling=!dueling;
    }

    //
    public void checkDuelingStatus(){
        dueling=this.fighterDown();
    }

    //reset end of turn
    public void switchEndOfTurn(){
        endOfTurn=!endOfTurn;
    }

    //change value of boolean 'dueling'
    public void switchDueling(){ dueling=!dueling;}

    //get a random number between 1 and 6
    public int numRolled(){
        //get a random int between 1 and 6
        Random rand=new Random();
        return (rand.nextInt(6)+1);
    }

    //get a string showing color and their roll
    public String getCurrentRollInfo(String colorFighter, int roll){
        String currentRollInfo="<html>"+colorFighter+" rolled a "+roll+".<br> " +
                "Choose where to add.</html>";
        return currentRollInfo;
    }

    //function to get a string showing the results of the fight and set values for winner and winnerColor
    public String showDuelResults(){
        int rFHealth=this.redFighter.getHealth();
        int bFHealth=this.blueFighter.getHealth();

        if((rFHealth<=0)&&(bFHealth<=0)){
            this.winner="None";
            this.winnerColor=Color.BLACK;
            return "It's a draw. DOUBLE KO";
        }
        else if(rFHealth<=0){
            this.winner=this.blueFighter.getColor();
            this.winnerColor=blueFighter.getBaseColor();
            return this.winner + " wins!!!!";
        }
        else if(bFHealth<=0){
            this.winner=this.redFighter.getColor();
            this.winnerColor=redFighter.getBaseColor();
            return this.winner+" wins!!!!";
        }
        else {
            return "Still fighting";
        }
    }

    //function the returns a boolean if any fighter is down(aka 0 HP)
    public boolean fighterDown(){
        int f1 =this.redFighter.getHealth();
        int f2=this.blueFighter.getHealth();

        return (f1<=0 || f2<=0);
    }

    //calculate damage given between both fighters and lower their HP accordingly
    public void clash(){
        Fighter p1=this.redFighter;
        Fighter p2=this.blueFighter;

        //return the higher number between 0 and the difference
        int damageGivenByP1 = Math.max(0, (p1.getAttackPower() - p2.getDefensePower()));
        int damageGivenByP2 = Math.max(0, (p2.getAttackPower() - p1.getDefensePower()));

        //lower health of both fighters
        p1.lowerHealth(damageGivenByP2);
        p2.lowerHealth(damageGivenByP1);
    }

    public void changeCurrFighterColor(){
        if (currFighterColor == redFighter.getColor()){
            currFighterColor = blueFighter.getColor();
        }
        else {
            currFighterColor = redFighter.getColor();
        }
    }

    public void removeCurrFighterColor(){
        currFighterColor = "";
    }

    public boolean sameColor(String currFighterColor, Fighter comparer){
        return currFighterColor == comparer.getColor();
    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

}
