import java.util.Scanner;

public class UserAge {

    //function used to get the user's age
    static void getAge() {
        Scanner scanner = new Scanner(System.in);

        boolean trying = true;
        int tries= 1;
        int age = 0;
        String inputAge;
        while (trying) {
            if (tries <= 10) {

                /*

                //TEST: if we are on the 9th try, we will set a default age so it exists the loop;
                if (tries==9) {
                    inputAge="19";
                }

                */

                try{
                    System.out.println("Try #"+tries); //show what # try this is
                    System.out.println("Type in your age: ");
                    inputAge=scanner.nextLine(); //getting input
                    age=Integer.parseInt(inputAge);

                    trying=false; //this line will only run if above line does not fail
                }
                catch (NumberFormatException e){
                    System.out.println("Not a number");
                    System.out.println("Exception :" + e.getMessage());
                }
            }
            //if program goes past 10 tries then set a default age and stop doing the loop
            else{
                System.out.println("Failed to use correct input");
                age=18;
                System.out.println("Default age: " + age);
                trying = false;
            }
            tries++; //increase number of tries after looping
        }

        //Once we reach limit or get a correct input:
        System.out.println("The chosen age is: " + age);
    }

    //main method
    public static void main(String[] args) {
        getAge(); //run getAge function
    }
}