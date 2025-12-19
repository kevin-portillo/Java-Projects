public class Board {
    int spaces = 20;

    //Board constructor(default constructor)
    public Board(){
        System.out.println("Board created"); //Confirm that the board was created
        System.out.println("It will have " + spaces + " spaces.");
    }

    //getter for number of spaces in board
    public int numSpaces(){
        return spaces;
    }
}
