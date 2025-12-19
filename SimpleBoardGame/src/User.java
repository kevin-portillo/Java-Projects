import java.util.Scanner;

public class User {

    public static void askGreetTest(){

        System.out.println("Hello World");
        System.out.print("This prints on ");
        System.out.print("the same line."); //System.out.print---will stay on same line
        System.out.println();


        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

        //create a Scanner object for console input
        Scanner scanner = new Scanner(System.in);

        //Reading different datatypes:

        //nextLine()=read an entire line(incl spaces) until a newline char is found
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        //next() = read single word (sequence without spaces) until a space/newline char is found
        System.out.println("Enter your first name: ");
        String firstName = scanner.next();

        //nextInt()= read an integer value
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();

        //nextDouble()= read a double
        System.out.println("Enter your height in meters: ");
        double height = scanner.nextDouble();

        //nextBoolean()= read boolean value
        System.out.println("Are you a student? True or False: ");
        boolean isStudent = scanner.nextBoolean();

        //it is good practice to close the Scanner object to release system resources
        scanner.close();

        System.out.println("Greetings " + name + ". It is nice to meet you " + firstName +
                ". You are " + age + " and are " + height + "m tall. ");

        if (isStudent) {
            System.out.println("You are a student.");
        } else {
            System.out.println("You are not a student.");
        }
    }
}