public class Main {
    public static void main(String[] args) {
        Fighter redFighter=new Fighter();//creating the first fighter
        Fighter blueFighter=new Fighter();//creating the second fighter

        //run the Fight function
        Fighter.Fight(redFighter,blueFighter);
        System.out.println("End");
    }
}