//https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
//https://docs.oracle.com/javase/tutorial/uiswing/components/splitpane.html


import java.awt.*;//for graphics library
import javax.swing.*;
import java.awt.event.*;//for event handling


public class DojoGui implements ActionListener {
    //essentially, text goes in label, label goes in panel, panel goes inside window
    boolean started = false;
    boolean ended=false;

    Duel match;
    //Variables and components that are important

    //dimensions for parent window
    int boardWidth = 720;
    int boardHeight = 650;

    /*
    //make new colors using rgb values
    Color customDarkGrey=new Color(80,80,80);
    Color customBlack=new Color(28,28,28);
    Color customOrange=new Color(255,149,0);
    */

    //fonts to be used for messages and buttons
    Font customInfoFont = new Font("Arial", Font.PLAIN, 18);
    Font customButtonFont = new Font("Times New Roman", Font.BOLD, 20);
    Font customTitleFont = new Font("Ink Free", Font.BOLD, 22);

    //constructor to make a frame(window) with a title on top with a title "THE DOJO"
    JFrame frame = new JFrame("THE DOJO");


    //JLabel fighterLabel= new JLabel("Messaging"); //descriptive string wasnt used
    JSplitPane splitPaneFrame;


    //top panel to hold images of fighters
    JPanel topPanel = new JPanel();

    //(parent) bottom panel that will hold info and buttons
    JPanel bottomInfoPanel = new JPanel();

    //(child) panels that will be place on the (parent) bottom panel
    JPanel bottomLeftInfoPanel = new JPanel();
    JPanel bottomCenterInfoPanel = new JPanel();
    JPanel bottomRightInfoPanel = new JPanel();

    //labels that will hold fighter info and fight info
    JLabel redInfoLabel = new JLabel();
    JLabel blueInfoLabel = new JLabel();
    JLabel fightStatus = new JLabel();
    JLabel gameTitle = new JLabel();

    ImageIcon redFighterImg;
    ImageIcon blueFighterImg;

    JLabel redFighterImgLabel;
    JLabel blueFighterImgLabel;




    //button(optional)
    JButton startButton = new JButton("Start");


    //text that will be in buttons
    String[] buttonText = {"Attack Up", "Defense Up"};
    int numButtons = buttonText.length;

    //button ids
    String[] redButtonIDs = {"redAB", "redDB"};
    String[] blueButtonIDs = {"blueAB", "blueDB"};

    //DojoGui constructor
    DojoGui(Duel duel) {
        match = duel; //setting the Duel object input to the Duel var in DojoGui

        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//end program when user clicks on "X"
        frame.setLocationRelativeTo(null); //place frame at center of screen
        frame.setLayout(new BorderLayout()); //place components

        //MAKING TOP PANEL
        //adjusting gameTitle properties
        setTopLabelProperties(gameTitle);
        gameTitle.setText("SPARTAN DUEL");

        //setting values for image icons and labels
        //this was originally done outside this constructor
        //ImageIcon redFighterImg = new ImageIcon("redMasterChief.jpg"); //original, when fighter class didnt have an imageicon var
        redFighterImg = this.match.redFighter.getFighterImage();
        redFighterImgLabel = new JLabel(redFighterImg);

        //ImageIcon blueFighterImg = new ImageIcon("blueMasterChief.png"); //original, when fighter class didnt have an imageicon var
        blueFighterImg =  this.match.blueFighter.getFighterImage();
        blueFighterImgLabel = new JLabel(blueFighterImg);


        //Setting layout for topPanel and adding components
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        //topPanel.add(fighterLabel);
        topPanel.setBackground(Color.BLACK);
        topPanel.add(redFighterImgLabel);
        topPanel.add(Box.createRigidArea(new Dimension(40, 30)));
        topPanel.add(gameTitle);
        topPanel.add(Box.createRigidArea(new Dimension(40, 30)));
        //topPanel.add(Box.createRigidArea(new Dimension(250,30))); //only if gameTitle was not displayed
        topPanel.add(blueFighterImgLabel);


        //bottomInfoPanel.setBackground(Color.CYAN);

        //SETTING BACKGROUND AND LAYOUTS FOR BOTTOM PANEL COMPONENTS
        //colors for bottom panels
        bottomLeftInfoPanel.setBackground(Color.RED);
        bottomCenterInfoPanel.setBackground(Color.BLACK);
        bottomRightInfoPanel.setBackground(Color.BLUE);

        //setting layouts for panels
        bottomLeftInfoPanel.setLayout(new BoxLayout(bottomLeftInfoPanel, BoxLayout.Y_AXIS));
        bottomRightInfoPanel.setLayout(new BoxLayout(bottomRightInfoPanel, BoxLayout.Y_AXIS));
        bottomCenterInfoPanel.setLayout(new BorderLayout());

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //CREATING AND ADDING RED FIGHTER INFO AND BUTTONS
        //trying to add fighter info and buttons to bottom left panel(RED FIGHTER)

        String redInfoString = match.redFighter.getFighterInfoHTML();
        redInfoLabel.setText(redInfoString);
        setInfoLabelProperties(redInfoLabel);


        //create a panel that holds red buttons using a function
        JPanel redButtonPanel = createAddButtonsToPanel(redButtonIDs, buttonText, numButtons, customInfoFont);//panel to hold buttons and place in SW panel
        redButtonPanel.setBackground(Color.RED);//change bg color of button panel

        //add info label and button panel to a side panel
        createBottomSidePanelsForBoxLayout(bottomLeftInfoPanel, redInfoLabel, redButtonPanel);


        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //CREATING AND ADDING BLUE FIGHTER INFO AND BUTTONS
        //add fighter info and buttons to bottom right panel(blue side)


        String blueInfoString = match.blueFighter.getFighterInfoHTML();
        blueInfoLabel.setText(blueInfoString);
        setInfoLabelProperties(blueInfoLabel);


        //create a panel to hold the buttons
        JPanel blueButtonPanel = createAddButtonsToPanel(blueButtonIDs, buttonText, numButtons, customInfoFont);//panel to hold buttons and place in SW panel
        blueButtonPanel.setBackground(Color.BLUE);//change bg color of button panel

        //add info label and button panel to side panel
        createBottomSidePanelsForBoxLayout(bottomRightInfoPanel, blueInfoLabel, blueButtonPanel);


        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //BOTTOM CENTER INFO PANEL

        //labels for bottom center info panel
        //Possible layout values during game: "Start", roll info, or match results




        String currentMessage = "Start";

        fightStatus.setText(currentMessage);
        fightStatus.setHorizontalAlignment(SwingConstants.CENTER);
        fightStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        fightStatus.setFont(customInfoFont);
        fightStatus.setForeground(Color.ORANGE);

        //testing something
        startButton.setName("Start");
        startButton.addActionListener(this);

        bottomCenterInfoPanel.add(fightStatus, BorderLayout.CENTER);
        bottomCenterInfoPanel.add(startButton, BorderLayout.SOUTH);
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        //creating and customizing split pane for bottom section

        //part 1: get a split pane for the two panels on the right
        JSplitPane bottomRightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bottomCenterInfoPanel, bottomRightInfoPanel);
        bottomRightSplitPane.setDividerSize(0);
        bottomRightSplitPane.setDividerLocation(225);
        bottomRightSplitPane.setEnabled(false);//user cant resize divider

        //part 2: get a split pane for the panel on the left and splitpane from part1 above
        JSplitPane bottomSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bottomLeftInfoPanel, bottomRightSplitPane);
        bottomSplitPane.setDividerSize(0);
        bottomSplitPane.setDividerLocation(250);
        bottomSplitPane.setEnabled(false);


        //pane that is divided horizontally(vertical_split). fighterPanel on top, infoPanel on bottom)
        splitPaneFrame = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomSplitPane);

        //split pane properties for main one(dividing screen into top and bottom)
        splitPaneFrame.setDividerLocation(350); //trying to center divider(higher num, bigger top part)
        splitPaneFrame.setDividerSize(0); //divider is not visible since its size is 0 lol

        //MAYBE ADD THIS AS A FUNCTION

        /*
        //CHANGING TOP PANEL IN splitpane
        //create replacement panel
        JPanel replacement=new JPanel();
        JLabel repl=new JLabel("SOMETHING");
        replacement.add(repl);

        //remove the top panel from its parent container(splitPaneFrame in this case)
        splitPaneFrame.remove(topPanel);
        //add the replacement panel
        splitPaneFrame.add(replacement);

        //refresh to ensure layout is updated and displayed correctly
        splitPaneFrame.revalidate();
        splitPaneFrame.repaint();
        */
        //add big split pane to the frame
        frame.add(splitPaneFrame, BorderLayout.CENTER);


        frame.setResizable(false);//window cant be resized
        frame.setVisible(true);



    }

    //function that does actions if button is pressed(MAIN GAME LOGIC)
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();//get the button that was pressed
        String buttonValue = button.getName();//see id/name of the button
        String display = "";
        boolean validSelection = false;
        boolean endTurn = false;



        if (!started) {
            if (buttonValue == "Start") {

                //if user presses start, change text displayed in bottom center panel
                //and remove the "Start" button from the bottom center panel
                this.fightStatus.setText("STARTED");
                this.bottomCenterInfoPanel.remove(startButton);
                //should be called when removing an item:
                this.bottomCenterInfoPanel.revalidate();
                this.bottomCenterInfoPanel.repaint();

                //set values for started and currFighterColor
                started = true;
                System.out.println("Started status: " + started);
                match.currFighterColor = this.match.redFighter.getColor();
                System.out.println("Current fighter: " + match.getCurrFighterColor());

                /*
                int currRoll = match.numRolled();
                String display = match.getCurrentRollInfo(match.currFighterColor, currRoll);
                this.fightStatus.setText(display);
                 */

                //get the first roll and display in bottom center panel
                match.currRoll = match.numRolled();
                display = match.getCurrentRollInfo(match.currFighterColor, match.currRoll);
                this.fightStatus.setText(display);
            }
        } else {

            //if statements to make sure the current fighter has the same color as the button pressed
            if (buttonValue == "redAB" && match.sameColor(match.getCurrFighterColor(), match.redFighter)) {
                System.out.println("before increase: " + this.match.redFighter.getAttackPower());
                this.match.redFighter.setAttackPower(match.getCurrRoll());
                validSelection = true;

            } else if (buttonValue == "redDB" && match.sameColor(match.getCurrFighterColor(), match.redFighter)) {
                this.match.redFighter.setDefensePower(match.currRoll);
                validSelection = true;

            } else if (buttonValue == "blueAB" && match.sameColor(match.getCurrFighterColor(), match.blueFighter)) {
                this.match.blueFighter.setAttackPower(match.getCurrRoll());
                validSelection = true;
                endTurn = true;//since blue fighter goes second, it technically means end of turn

            } else if (buttonValue == "blueDB" && match.sameColor(match.getCurrFighterColor(), match.blueFighter)) {
                this.match.blueFighter.setDefensePower(match.getCurrRoll());
                validSelection = true;
                endTurn = true;//since blue fighter goes second, it technically means end of turn

            }

            //if user makes a valid color selection
            if (validSelection ) {
                //change the color of curr fighter
                match.changeCurrFighterColor();
                //show new roll for the new currFighter
                match.currRoll = match.numRolled();
                display = match.getCurrentRollInfo(match.currFighterColor, match.currRoll);
                this.fightStatus.setText(display);

                //reset valid selection to false(SEEMS IT IS NOT REALLY USED)
                //REASON IT ISN'T USED IS BECAUSE EVERY TIME A BUTTON IS PRESSED
                //(AKA THIS FUNCTION IS CALLED) validSelection is initialized as
                //false.
                validSelection = false;

                //if we reach the end of the turn(AKA blue fighter chose where to use pts)
                if (endTurn) {
                    //make fighters clash and adjust their hp accordingly
                    match.clash();

                    endTurn = false;//reset end of turn
                    //might want to add a slight pause to show how much damage each fighter took

                    //check to see if there is a fighter down
                    boolean koExists = match.fighterDown();
                    //if someone is KOd
                    if (koExists) {
                        //show status of fighters and of fight and change top Panel
                        endFight();
                        ended=true;

                        //this is to prevent user from continuing game after someone
                        //is KOd. Accomplished by making currFighter = ""; this way
                        //user will never have a valid selection
                        match.removeCurrFighterColor();


                    }
                }

            }
            showCurrentStatusAll();
        }

    }


    //function that adds buttons to a panel and returns the panel(FOR SIDE PANELS)
    //NOTE: BOTH ARRAYS SHOULD BE THE SAME SIZE
    public JPanel createAddButtonsToPanel(String[] buttonID, String[] buttonText, int amountButtons, Font fontUsed) {
        JPanel buttonHolder = new JPanel(); //panel that will hold items

        //loop through the arrays to make/edit buttons and add them to the panel
        for (int i = 0; i < amountButtons; i++) {
            //create a button with
            JButton currButton = new JButton(buttonText[i]);
            //currButton.setText(buttonText[i]); //not really needed since we added text already
            currButton.setName(buttonID[i]); //set the name/id of the button
            currButton.setFont(fontUsed); //set font used
            currButton.addActionListener(this); //add an action listener
            buttonHolder.add(currButton); //add button to panel
        }
        return buttonHolder;
    }

    //function that adds items to panels that will be used in the bottom on the sides
    public void createBottomSidePanelsForBoxLayout(JPanel sidePanel, JLabel infoToAdd, JPanel buttonPanel) {
        // add a rigid area, label, rigid area, and button panel
        sidePanel.add(Box.createRigidArea(new Dimension(20, 30)));
        sidePanel.add(infoToAdd);
        sidePanel.add(Box.createRigidArea(new Dimension(20, 40)));
        sidePanel.add(buttonPanel);
    }


    //function called when the fight ends...
    // used to show final stats, change bottom center panel and change topPanel
    public void endFight() {
        System.out.println("TESTING 1 2 3");
        showCurrentStatusAll();
        //set text to show in the bottom center panel after match ends
        //will also set values for Duel.winner and Duel.winnerColor
        this.fightStatus.setText(match.showDuelResults());

        //adjust the new top panel
        this.finalTopPanel(splitPaneFrame,topPanel,match.winnerColor);
    }

    //function used to show status of all fighters
    public void showCurrentStatusAll() {
        this.blueInfoLabel.setText(match.blueFighter.getFighterInfoHTML());
        this.redInfoLabel.setText(match.redFighter.getFighterInfoHTML());
    }

    //sets properties for given infoLabel
    //maybe add another parameter to adjust the text/foreground color
    public void setInfoLabelProperties(JLabel infLbl){

        infLbl.setFont(customInfoFont);//change font, style, and size
        infLbl.setHorizontalAlignment(SwingConstants.CENTER); //center text
        infLbl.setAlignmentX(Component.CENTER_ALIGNMENT); //center label in panel(I GUESS)
        infLbl.setForeground(Color.WHITE); //change font color
    }

    //function that sets the top label properties
    public void setTopLabelProperties(JLabel tpLbl){
        tpLbl.setFont(customTitleFont); //change font of gametitle label
        tpLbl.setPreferredSize(new Dimension(250, 400)); //set dimensions of jlabel
        tpLbl.setForeground(Color.lightGray); //text color for jlabel
        tpLbl.setHorizontalAlignment(SwingConstants.CENTER); //text is centered

    }


    //Possible improvement, pass the winner Fighter as an input instead of Color
    //change top panel in splitpane
    public void finalTopPanel(JSplitPane spltPane, JPanel tpPanel, Color colorW){
        //CHANGING TOP PANEL IN splitpane

        //remove the top panel from its parent container(splitPaneFrame in this case)
        spltPane.remove(this.topPanel);

        //get dimensions of tpPanel input
        Dimension tpPanelSize=tpPanel.getSize();
        int tpWidth=tpPanelSize.width; //get width
        int tpHeight=tpPanelSize.height;//get height

        //create replacement panel and adjust properties
        JPanel replacement=new JPanel();
        replacement.setLayout(new BorderLayout());
        replacement.setPreferredSize(tpPanelSize);
        replacement.setBackground(colorW);

        //Label to hold image of winner
        JLabel winnerIcon;


        JLabel repl=new JLabel("DUEL OVER");
        //center text in the middle horizontally
        //(vertical alignment not really needed since it is the only label and swingconstant.center does it)
        repl.setHorizontalAlignment(SwingConstants.CENTER);
        //repl.setVerticalAlignment(SwingConstants.CENTER);
        repl.setForeground(Color.WHITE);//change color of text
        replacement.add(repl, BorderLayout.CENTER);//put label in center of replacement panel

        //if the color of the winner is not black, but depending on if it is red or blue
        //the image placement and image used will change
        if (colorW!=Color.BLACK){

            if (colorW==Color.RED){
            winnerIcon=new JLabel(redFighterImg);
            replacement.add(winnerIcon, BorderLayout.WEST);
            }
            else if (colorW==Color.BLUE) {
                winnerIcon=new JLabel(blueFighterImg);
                replacement.add(winnerIcon, BorderLayout.EAST);
            }
        }


        //add the replacement panel
        spltPane.add(replacement);


        //refresh to ensure layout is updated and displayed correctly
        spltPane.revalidate();
        spltPane.repaint();

        //change value of new topPanel
        this.topPanel=replacement;
        System.out.println("WORK IN PROGRESS");
    }


}