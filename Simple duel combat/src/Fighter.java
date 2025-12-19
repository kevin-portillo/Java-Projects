import java.util.Scanner;
import java.util.Random;

public class Fighter {
    String name;
    int attackPower=0;
    int defensePower=0;
    int health=150;

    public Fighter(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("What is the name of your fighter? :");
        this.name=scanner.nextLine();
    }

    //getter for Fighter name
    public String getName() {
        return name;
    }

    //getter for Fighter health
    public int getHealth() {
        return health;
    }

    //setter for Fighter Attack
    public void setAttackPower(int attackChange) {
        this.attackPower += attackChange;
    }

    //setter for Fighter Defense
    public void setDefensePower(int defenseChange){
        this.defensePower += defenseChange;
    }

    // lower health based on parameter given
    public void setHealth(int healthChange){
        this.health-=healthChange;
    }

    //method used to have the 2 fighters fight
    public static void Fight(Fighter f1, Fighter f2){
        while (true) {
            //roll and change stats for fighters
            f1.modifyAtkDef();
            f2.modifyAtkDef();

            //return the higher number between 0 and the difference
            int damageGivenByF1 = Math.max(0, (f1.attackPower - f2.defensePower));
            int damageGivenByF2 = Math.max(0, (f2.attackPower - f1.defensePower));

            //change health of fighter based on damage received
            f1.setHealth(damageGivenByF2);
            f2.setHealth(damageGivenByF1);

            //show how much damage each fighter did
            System.out.println(f1.getName() + " did " + damageGivenByF1 + " in damage.");
            System.out.println(f2.getName() + " did " + damageGivenByF2 + " in damage.");

            //see if there are any fighters knocked out and if yes, then exit loop(fight)
            boolean knockedOut = fighterDown(f1, f2);
            if(knockedOut){
                break;
            }
            //show how much health each fighter has
            System.out.println(f1.getName() + " has " + f1.getHealth() + " and " + f2.getName() + " has " + f2.getHealth() + " in health.");
        }
        showResults(f1,f2);
    }

    //return a boolean to show if at least one fighter is down
    public static boolean fighterDown(Fighter f1, Fighter f2){
        return (f1.getHealth() <= 0 || f2.getHealth() <= 0);
    }

    //show the final results
    public static void showResults(Fighter f1, Fighter f2){
        if((f1.getHealth()<=0) && (f2.getHealth()<=0)){
            System.out.println("It is a draw. DOUBLE KO!!!");
        }
        else if (f2.getHealth()<=0){
            System.out.println(f1.getName()+" wins!!!!!!");
        }
        else {
            System.out.println(f2.getName()+" wins!!!!!!");
        }
    }

    //return a random number
    public int numRolled(){
        //get a random int between 1 and 6
        Random rand=new Random();
        return (rand.nextInt(6)+1);
    }

    //function used to roll and change def or atk of fighter
    public void modifyAtkDef(){

        boolean noInput=true;
        int numModifier=numRolled();
        int tries=1;

        Scanner scanner=new Scanner(System.in);

        while(noInput){
            if (tries==3){
                System.out.println("This is your last try please do not make a mistake.");
            }
            System.out.println(this.name + " you received " +numModifier+ " skill points.");
            System.out.println("Where do you want to apply all of them: (A)ttack or (D)efense");
            char choice= Character.toUpperCase(scanner.next().charAt(0));

            if((choice=='A')){
                this.setAttackPower(numModifier);
                System.out.println(this.name+" your new attack power is: "+this.attackPower);
                noInput=false;
            }
            else if (choice=='D') {
                this.setDefensePower(numModifier);
                System.out.println(this.name+" your new defense power is: "+this.defensePower);
                noInput=false;
            }
            else {
                //if user puts in wrong option display this message, and if limit is reached then lose the points
                if (tries<3) {
                    System.out.println("Can you please select a correct option?");
                }
                else {
                    System.out.println("THIS WAS YOUR LAST CHANCE FOR A CORRECT INPUT");
                    System.out.println("YOUR POINTS WILL GO UNUSED.");
                    noInput=false;
                }
                tries++;
            }
        }

        System.out.println(this.name+" your attack is "+this.attackPower+" and your defense is "+ this.defensePower+".");



    }
}
