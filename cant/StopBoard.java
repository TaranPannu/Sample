import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;



public class StopBoard extends JFrame implements ActionListener, MouseListener{
    
    // Top Level Elements - Main UI Panels
    private JPanel board;
    private JPanel leftSidePanel, rightSidePanel;
    private JLabel infoLabel;
    
    // Elements for rightPanel - Where most of the action happens
    private JButton rollButton;
    private JPanel rollBox;
    private JLabel rollLabel;
    private JPanel dicePanel, leftDicePanel, rightDicePanel;
    private JLabel leftDiceLabel, rightDiceLabel;
    private JPanel runnerPanel;
    private JLabel turnStatusLabel, turnLabel;
    private BufferedImage die;
    
    
    // back end elements
    private Random diceroller;
    private ArrayList<StopColumn> boardColumns;
    private int[] arraySizes = {3,5,7,9,11,13,11,9,7,5,3};
    //private int numPlayers;
    public int turn;
    private int thisRoll;
    private ArrayList<Integer> rollValues;
    private int column1, column2;



    /* Constructor:
    * Initialize the Board data structure
    *  ArrayList of Column objects, 
    * which themselves are arraylists of tile objects
    */
    public StopBoard(){

        // Initialize the UI
        this.setSize(1200, 800);
        this.setLayout(new BorderLayout());
        
        // initialize the UI elements
        board = new JPanel();
        board.setLayout(new FlowLayout());
        board.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // add columns to the board
        boardColumns = new ArrayList<>();
        for(int i : arraySizes) {
            StopColumn newColumn = new StopColumn(i);
            board.add(newColumn);
            boardColumns.add(newColumn);
        }
        
        
        // initialize the right side panel
        rightSidePanel = new JPanel();
        rightSidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightSidePanel.setLayout(new BoxLayout(rightSidePanel, BoxLayout.PAGE_AXIS));
        rightSidePanel.setPreferredSize(new Dimension(500,800));
        
        
        // initialize the left side panel
        leftSidePanel = new JPanel();
        leftSidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //leftSidePanel.setPreferredSize(new Dimension(200,200));

        // initialize the sub-elements
        infoLabel = new JLabel("Can't Stop!");
        infoLabel.setSize(100, 100);
        infoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        turnLabel = new JLabel("It's Your Turn!");
        turnLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        rollBox = new JPanel();
        //rollBox.setPreferredSize(new Dimension(500, 100));
        rollLabel = new JLabel("");
        rollLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rollButton = new JButton("Roll!");
        rollValues = new ArrayList<>();

        rollButton.addActionListener(e -> {
            if(rollValues.size() == 0)
                generateRoll();
        });
        

        // set up dice selection panels
        leftDicePanel = new JPanel();
        leftDicePanel.setPreferredSize(new Dimension(225,300));
        leftDicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftDicePanel.setLayout(new BoxLayout(leftDicePanel, BoxLayout.Y_AXIS));


        rightDicePanel = new JPanel();
        rightDicePanel.setPreferredSize(new Dimension(225,300));
        rightDicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        leftDiceLabel = new JLabel("");
        rightDiceLabel = new JLabel("");

        leftDicePanel.add(leftDiceLabel);
        rightDicePanel.add(rightDiceLabel);
        
        // add dice selection panels to their container panel
        dicePanel = new JPanel(new FlowLayout());
        dicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dicePanel.add(leftDicePanel);
        dicePanel.add(rightDicePanel);
        
        runnerPanel = new JPanel();     
        turnStatusLabel = new JLabel("You have placed all possible runners. Do you want to roll again?");  
        

        // add ui element to top level panels
        leftSidePanel.add(infoLabel);

        rightSidePanel.add(turnLabel);
        rightSidePanel.add(rollButton);
        rightSidePanel.add(rollLabel);
        
        rightSidePanel.add(rollBox);
        rightSidePanel.add(rollLabel);
        
        rightSidePanel.add(dicePanel);
        
        rightSidePanel.add(runnerPanel);
        rightSidePanel.add(turnStatusLabel);
        
        


        // add the top level panels to the content pane
        getContentPane().add(leftSidePanel, BorderLayout.LINE_START);

        getContentPane().add(board, BorderLayout.CENTER);

        getContentPane().add(rightSidePanel, BorderLayout.LINE_END);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
		System.out.println("Implement me!");
    }
    public void printDetails(){
        System.out.println(board.getSize());

    }

    public int roll(){
        diceroller = new Random();
        return diceroller.nextInt(6) + 1;
    }

    public void generateRoll(){
        die = null;
        thisRoll = 0;
        ArrayList<JLabel> rolls = new ArrayList<>();
        
        for(int i = 0; i < 4; i++) {
            thisRoll = roll();
            rollValues.add(thisRoll);
            try{
                die = ImageIO.read(new File("assets/die" + Integer.toString(thisRoll) + ".png"));
            }   
            catch(Exception f){
                System.out.println("Error: File not found");
            }
            rolls.add(new JLabel(new ImageIcon(die)));
            rollBox.add(rolls.get(i)); 
             
        }
        String rollText = "You Rolled: ";
        for(int i = 0; i < 4; i++){
            rollText += (Integer.toString(rollValues.get(i)) + ",");
        }
        rollText = rollText.substring(0, rollText.length() - 1);
        rollLabel.setText(rollText);
        //this.revalidate();
        generatePairs(rollValues);
        this.repaint();
        
        rollBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //rollBox.setPreferredSize(new Dimension(50,50));
    }

    public void generatePairs(ArrayList<Integer> rollVals){
        
        for(int i : rollVals)
            System.out.println(i);
        String combo = "";
        JButton pair;
        for(int i = 0; i < 4; i++) {
            combo = "";
            for(int j = i+1; j < 4; j++) {
                combo = "(" + Integer.toString(rollVals.get(i)) + "," + Integer.toString(rollVals.get(j)) + ")";
                pair = new JButton(combo);
                pair.addActionListener(e -> {
                    System.out.println("Button pressed!");

                    JButton button = (JButton) e.getSource();
                    String text = button.getText();

                    text = text.replaceAll("[^\\d.]", "");
                    
                    column1 = Integer.valueOf(text.substring(1,2)) + Integer.valueOf(text.substring(0,1));

                    rollVals.remove(rollVals.indexOf(Integer.valueOf(text.substring(0,1))));
                    rollVals.remove(rollVals.indexOf(Integer.valueOf(text.substring(1,2))));

                    System.out.println(rollVals.get(0) + " " + rollVals.get(1));
                    column2 = rollVals.get(0) + rollVals.get(1);
                    
                    leftDicePanel.removeAll();
                    
                    leftDicePanel.add(leftDiceLabel);
                    leftDicePanel.add(button);
                    rightDicePanel.add(new JButton("(" + Integer.toString(rollVals.get(0)) + "," + Integer.toString(rollVals.get(1)) +  ")"));

                    

                    leftDiceLabel.setText("Advance a runner in column " + Integer.toString(column1));
                    rightDiceLabel.setText("Advance a runner in column " + Integer.toString(column2));
                    
                    
                    this.repaint();
                    this.revalidate();

                    
                    boardColumns.get(column1-2).activateColumn();
                    boardColumns.get(column2-2).activateColumn();

                    

                    });
                leftDicePanel.add(pair);
                combo = "";
            }
        }

    }

    public void placeRunners(){
        
    }
    public void mouseEntered(MouseEvent e){
            
        
    }
	public void mouseExited(MouseEvent e) {
        
    }
    
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}

}