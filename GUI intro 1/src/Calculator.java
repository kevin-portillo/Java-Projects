import java.awt.*;//for graphics library
import java.awt.event.*;//for event handling
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;//
import javax.swing.border.LineBorder;//modify border of buttons in calculator

public class Calculator {
    int boardWidth=360;
    int boardHeight=540;

    //make new colors using rgb values
    Color customLightGray=new Color(212,212,210);
    Color customDarkGrey=new Color(80,80,80);
    Color customBlack=new Color(28,28,28);
    Color customOrange=new Color(255,149,0);

    //constructor to make a frame with a title on top with a title "Calculator"
    JFrame frame=new JFrame("Calculator");

    JLabel displayLabel=new JLabel(); //label for text
    JPanel displayPanel=new JPanel(); //panel for text
    //essentially, text goes in label, label goes in panel, panel goes inside window

    JPanel buttonsPanel=new JPanel();//panel that will hold calculator buttons

    //String values to hold inputs( 2 numbers and operator)(EX: A+B,A-B,A*B,A/B)
    String A="0";
    String operator=null;
    String B=null;

    //BUTTON VALUES:
    String[] buttonValues = {
            "AC", "+/-","%","\u2190",
            "x\u00B2","\u2093\u02B8","1/x","÷",
            "7","8","9","*",
            "4","5","6","-",
            "1","2","3","+",
            "0",".","√","="
    };
    //unicode:
    //x²=x\u00B2
    //ₓʸ = \u2093\u02B8
    //← = \u2190



    String[] rightSymbols={"÷","*","-","+","="}; //used to show which buttons are orange
    String[] topSymbols={"AC","+/-","%","\u2190"};//used to show which buttons are gray
    String[] secRowXSymbols={"x\u00B2","\u2093\u02B8","1/x"};

    //calculator constructor
    Calculator(){
        frame.setVisible(true);//able to see window(if window doesnt load properly, move this line to the end of code)
        //See 31:27 from youtube vid. Line was copied already to end(as comment) just in case
        frame.setSize(boardWidth,boardHeight); //size frame based on given width and height
        frame.setLocationRelativeTo(null);//center the window
        frame.setResizable(false);//cant be resized by user
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when user clicks on X in window, program is terminated
        frame.setLayout(new BorderLayout());//place components within the window


        //styling for label
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white); //essentially color of font in this case
        displayLabel.setFont(new Font("Arial",Font.PLAIN,80)); //choosing font, style(?) and size
        // note: font size will affect size of label
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);//display text to the right
        displayLabel.setText("0");//default text in label
        displayLabel.setOpaque(true);//

        //styling the Panel for the display
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel); //put the label in the panel
        frame.add(displayPanel, BorderLayout.NORTH);//place panel in frame, on the top
        //NOTE: w/o BorderLayout.NORTH, the panel will take up entire window

        //styling the Panel for the buttons
        buttonsPanel.setLayout(new GridLayout(6,4));//5x4 grid for buttons
        buttonsPanel.setBackground(customBlack);//set background color of panel to the custom black
        frame.add(buttonsPanel); //add buttons panel in frame, automatically goes under the displayPanel

        //loop through buttonValues array to add values to buttons
        for (int i=0;i<buttonValues.length;i++){
            JButton button=new JButton(); //create a new button
            String buttonValue=buttonValues[i];
            button.setFont(new Font("Arial",Font.PLAIN,30));//set font, style, size of text
            button.setText(buttonValue); //set the text value of the button
            button.setFocusable(false); //makes it so text isnt highlighted when we hover over button
            button.setBorder(new LineBorder(customBlack));//change border of button(default is bluish color

            //Change button colors based on if they are on topSymbols,rightSymbols arrays
            //look at topSymbols array as a list, and if it contains the button value change color of button
            //ex. buttonValue="AC", and topSymbols does have "AC"..so color is changed
            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            }
            //look at right Symbols array as list, and if buttonValue is in list change colors
            else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            }
            //change colors for other buttons
            else{
                button.setBackground(customDarkGrey);
                button.setForeground(Color.white);
            }

            buttonsPanel.add(button); //add the button to the panel

            //MAIN FUNCTION BEHIND CALCULATOR(DOES ALL THE MATH)
            //SEE IF ACTION IS PERFORMED ON BUTTON
            //action in this case mouseclick
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //e=action event(mouse click) getSource=where the event comes from
                    JButton button =(JButton) e.getSource();
                    //identify which button was clicked on(get symbol)
                    String buttonValue=button.getText();

                    //quick check to see if display shows "Err" so we reset
                    //everything so we dont add values to end of "Err"
                    //like "Err3343090"
                    //RECHECK this if-statement
                    if(displayLabel.getText()=="Err"){
                        //clear everything and set display as 0
                        clearAll();
                        displayLabel.setText("0");
                    }

                    //RECHECK THIS:
                    //trying to change "AC" to "<-"
                    /*
                    //**********************************************
                    if (displayLabel.getText() != "0") {
                        JButton[] buttonArray=getButtonsFromPanel(buttonsPanel);
                        for (JButton currButton: buttonArray){
                            if(currButton.getText()=="AC"){
                                currButton.setText("<-");
                            }
                        }
                    }
                    else{
                        JButton[] buttonArray=getButtonsFromPanel(buttonsPanel);
                        for (JButton currButton: buttonArray){
                            if(currButton.getText()=="<-"){
                                currButton.setText("AC");
                            }
                        }
                    }
                    //***********************************************************
                     */

                    //depending on  what type of button is clicked(topButtons, rightButtons, others/else)
                    //do a certain action

                    //FOR SYMBOLS ON RIGHT
                    if (Arrays.asList(rightSymbols).contains(buttonValue)){

                        if (buttonValue == "="){
                            //if user presses "="
                            if(A != null){
                                B=displayLabel.getText();
                                double numA=Double.parseDouble(A);
                                double numB=Double.parseDouble(B);

                                //perform arithmetic operations
                                if(operator == "+"){
                                    displayLabel.setText(removeZeroDecimal(numA+numB));
                                }
                                else if(operator == "-"){
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                }
                                else if (operator == "÷") {
                                    displayLabel.setText(removeZeroDecimal(numA/numB));
                                }
                                else if (operator == "*"){
                                    displayLabel.setText(removeZeroDecimal(numA*numB));
                                }
                                else if (operator =="\u2093\u02B8"){
                                    displayLabel.setText(removeZeroDecimal(Math.pow(numA,numB)));
                                }
                                clearAll();
                            }
                        }
                        else if ("+-÷*".contains(buttonValue)) {
                            // this will only run if operator button hasnt been pressed before
                            //EX: '55' in display, user presses '+', display is reset to 0 and A=55..user
                            //presses '-', display is reset to 0 and A=0 now. Which will change desired results
                            if (operator==null){
                                A=displayLabel.getText();
                                displayLabel.setText("0");
                                B="0";
                            }
                            //even if user presses operator multiple times, operator value will change to
                            //most recent press
                            operator=buttonValue;
                        }
                    }

                    //FOR BUTTONS ON TOP
                    else if (Arrays.asList(topSymbols).contains(buttonValue)) {

                        //for AC
                        if (buttonValue == "AC"){
                            clearAll();
                            displayLabel.setText("0");
                        }
                        //for +/-
                        else if (buttonValue == "+/-"){
                            //Originally didnt have the if-else check for "0"
                            //only did it because pressing "+/-" when there was a zero
                            //then adding a number would cause something like "089"
                            //BRAINSTORM-MAYBE MODIFY removeZeroDecimal SO WE DONT DO A IF-ELSE HERE
                            if (displayLabel.getText()=="0"){
                                displayLabel.setText("0");
                            }
                            else {
                                //get num in display and convert to double
                                Double numDisplay = Double.parseDouble(displayLabel.getText());
                                numDisplay *= -1; //multiply by negative 1
                                //change display value with fixed string
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }
                        }
                        //for %(divide by 100)
                        else if (buttonValue == "%"){
                            //get num in display and convert to double
                            Double numDisplay=Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100; //divide by 100
                            //change display value with fixed string
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }

                        //for backspace (\u2190)(←)

                        else if(buttonValue == "\u2190" ){
                            //save display value as string
                            String oldDisplay=displayLabel.getText();
                            //if the display has more than one character remove the last one
                            if (oldDisplay.length()>1){
                                String removedLastChar=oldDisplay.substring(0,oldDisplay.length()-1);
                                displayLabel.setText(removedLastChar);
                            }
                            //else if the display has 1 or less, we reset value to 0
                            else {
                                displayLabel.setText("0");
                            }
                        }
                    }
                    //checking to see if user pressed buttons in 2nd row
                    else if (Arrays.asList(secRowXSymbols).contains(buttonValue)){
                        if (buttonValue == "x\u00B2"){
                            //similar to "+/-" issue where we might add numbers after "0"
                            if (displayLabel.getText() != "0") {
                                double numDisplay = Double.parseDouble(displayLabel.getText());
                                numDisplay = numDisplay * numDisplay;
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }
                        }
                        else if(buttonValue == "\u2093\u02B8"){
                            //For 'ₓʸ'
                            //for details look at [else if("+-÷*".contains(buttonValue))]
                            if (operator==null){
                                A=displayLabel.getText();
                                displayLabel.setText("0");
                                B="0";
                            }
                            operator=buttonValue;
                        }
                        else if (buttonValue == "1/x"){
                            //make sure display does not have a 0

                            double numDisplay=Double.parseDouble(displayLabel.getText());
                            if(numDisplay != 0){
                                numDisplay = (1/numDisplay);
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }

                            else {
                                displayLabel.setText("Err");
                            }
                        }
                    }

                    //FOR OTHERS(numbers and . and sqrt())
                    else {

                        if (buttonValue == "."){
                            //add check to make sure that there is only one decimal in display
                            //if display has a decimal, this will be !true=false, so it wont run
                            //will only run if there is no decimal
                            if(!displayLabel.getText().contains(buttonValue)){
                                //add decimal to value in display
                                displayLabel.setText(displayLabel.getText()+buttonValue);
                            }
                        }
                        //if buttonValue is one of these numbers
                        else if ("0123456789".contains(buttonValue)){
                            // see if the display has a '0' since we would want to replace it if it is
                            if (displayLabel.getText() == "0"){
                                displayLabel.setText(buttonValue);//change display label
                            }
                            else {
                                //if it is something else just add the number to the end
                                displayLabel.setText(displayLabel.getText()+buttonValue);
                            }
                        }

                        else if (buttonValue=="√"){
                            //THIS WILL BE FOR SQRT

                            //convert display string to double
                            Double numDisplay=Double.parseDouble(displayLabel.getText());
                            //check to see if num in display is positive
                            if (numDisplay>=0) {
                                numDisplay = Math.sqrt(numDisplay);
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }
                            else {

                                displayLabel.setText("Err");

                            }


                        }

                    }
                }
            }); //END OF addActionListener method

            //frame.setVisible(true); //set frame visible here if calculator is not loading properly
        }

    }

    //
    public  static JButton[] getButtonsFromPanel(JPanel panel) {
        List<JButton> buttonList =new ArrayList<>();
        for (Component component:panel.getComponents()) {
            if (component instanceof JButton) {
                buttonList.add((JButton) component);
            }
        }
        return buttonList.toArray(new JButton[0]);
    }
    //function used to reset values when pressing AC
    void clearAll(){
        A="0";
        operator=null;
        B=null;
    }

    //function used to change the number so it doesnt display ~.0 when it doesnt
    //have to like 134.0(it could just be 134)
    String removeZeroDecimal(double numDisplay){
        if (numDisplay % 1 ==0){
            //if num is divisible by 1 (modulus gives 0), then it is an int
            //(int) numDisplay...casts numDisplay as an int
            return  Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay); //line will only run if the 'if' statement is false
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

    public void fixErrorMessage(){
        wait(500);
        displayLabel.setText("0");
    }
}
