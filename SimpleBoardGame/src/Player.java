import java.util.Scanner;

public class Player {
    String playerName;

    int spot=0;

    //Player constructor that takes user input to set the name
    public Player(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name: ");
        this.playerName=scanner.nextLine();

    }
    //getter for player name
    public String getPlayerName() {
        return playerName;
    }

    //getter for player spot
    public int getSpot() {
        return spot;
    }

    //setter for player spot
    public void setSpot(int moveSpace) {
        this.spot = this.spot+moveSpace;
    }

    //method used to ask for number of players that will play,
    // create and return an array of Player objects
    public static Player[] createPlayerList(){
        Scanner scanner = new Scanner(System.in);

        //Get number of players and make an array of that length
        System.out.println("Please enter num of players: ");
        int numPlayers=scanner.nextInt();
        Player[] PlayerList = new Player[numPlayers];//create an empty Array to hold players

        //Create and add those Players to the list(Player constructor asks for the 'Player' name)
        for(int i=0;i<numPlayers;i++){
            System.out.println("Player " + (i+1));
            //String nameFromUser=scanner.nextLine(); //not really needed since Player constructor asks for a name
            PlayerList[i]=new Player();
        }

        //Print out the names of each 'Player' inside the Player array
        for (int i=0;i<numPlayers;i++){
            System.out.println("Player " + (i+1) + " is " + PlayerList[i].getPlayerName() + ".");
        }

        return PlayerList;
    }

    //this will only run if the file is run on its own
    public static void main(String[] args){
        System.out.println("Testtt");
    }
}
