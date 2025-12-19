import java.util.Scanner;

public class GameOn {

    //method used to play the game
    public static void Play(){
        Board gameboard= new Board(); //create a gameboard

        Scanner scanner = new Scanner(System.in);//used to get input

        //The two dice that will be used to play:
        Dice firstDice=new Dice();
        Dice secondDice=new Dice();

        //Dice secondDice=new Dice(10); //test to see if constructor made a 10 sided dice

        /*TEST to see a dice roll
        System.out.println("First Dice roll: " + firstDice.roll());
        System.out.println("Num of sides for dice: " + firstDice.sides);

        System.out.println("Second Dice roll: " + secondDice.roll());
        System.out.println("Num of sides for dice: " + secondDice.sides);
        */

        //Ask num of players and their names, and add them to an array by
        //using method from Player class
        Player[] allPlayers=Player.createPlayerList();


        boolean gameOn= true; //boolean used to see if we are still playing
        String winner;


        int numOfPlayers= allPlayers.length; //find num of players
        int turnNum=1; //variable to hold current turn number

        //will run while 'gameOn' is True
        while(gameOn){
            System.out.println("TURN "+turnNum);

            //loop through players in the list and roll dice to move spaces in board
            //and see if they reach the end after moving(if they did, exit for loop)
            for(Player currPlayer : allPlayers){
                System.out.println(currPlayer.getPlayerName()+"'s turn");
                int curr_Roll=getRoll(firstDice,secondDice);
                currPlayer.setSpot(curr_Roll);
                System.out.println(currPlayer.getPlayerName()+" is now at spot "+currPlayer.getSpot());

                //check if player reached the end
                if(currPlayer.getSpot()>=gameboard.numSpaces()){
                    System.out.println(currPlayer.getPlayerName() +" wins!!!!!");
                    gameOn=false; //change value of gameOn to false
                    break; //exit for loop
                }
            }

            //if the current turn number is 10, end the loop/game by setting 'gameOn' as False
            if (turnNum==5){
                System.out.println("No one made it to the end :(  Better luck next time.");
                gameOn=false;
            }

            turnNum++;//increase turn number
            wait(3000); //Call method and wait 2000ms(2s)

        }


        //Once game ends show these messages:
        System.out.println("*****************************************");
        System.out.print("Number of turns for game to end: ");
        System.out.println(turnNum-1);

        System.out.println("*****************************************");
        System.out.println("Final Standings: ");
        System.out.println("*****************************************");
        printPlayerAndSpot(allPlayers);


    }

    //method used to loop through array of Players and show their name and spot
    public static void printPlayerAndSpot(Player[] playList){
        for (Player pl:playList){
            System.out.println(pl.getPlayerName()+" at spot "+pl.getSpot());
        }
    }

    //method used to roll the dice, print the number we got from each dice, and return total
    public static Integer getRoll(Dice d1, Dice d2){

        int rollD1=d1.roll();
        int rollD2=d2.roll();
        int totalRoll=rollD1+rollD2;

        System.out.println("Rolled a "+rollD1+" and a "+rollD2+", for a total of: "+totalRoll);
        return (totalRoll);
    }

    //method used to have the cpu wait X milliseconds before going to next command
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
