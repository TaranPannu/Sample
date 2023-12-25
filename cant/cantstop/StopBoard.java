import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
public class StopBoard extends JFrame implements MouseListener{
    
    // UI Elements

    // panels
    private JPanel board;
    private JPanel leftSidePanel, rightSidePanel;
    private JPanel dicePanel, leftDicePanel, rightDicePanel;
    private JPanel rollBox;
    private JPanel runnerPanel;

    // named buttons
    private JButton endButton;
    private JButton rollButton;
    private JButton optionsButton;

    //labels
    private JLabel infoLabel;
    private JLabel rollLabel;
    private JLabel leftDiceLabel, rightDiceLabel;
    private JLabel turnStatusLabel, turnLabel;
    private JLabel colLabel1, colLabel2;
    
    //image placeholders
    private BufferedImage die;
    private BufferedImage runner;
    private BufferedImage piece;
    
    //Back-End Elements
    
    //randomizer
    private Random diceroller;
    
    // arraylists
    private ArrayList<StopColumn> boardColumns;
    private ArrayList<Integer> rollValues;
    private ArrayList<JLabel> diceImages;
    private ArrayList<DiceCombo> combinations;

    private OptionsMenu options;
    //private ArrayList<JLabel> runnerImages;
    //private ArrayList<Integer> runnerColumns;

    // member variables    
    public int movesToMake;
    private int runnerCount;
    private int col1, col2;
    private int[] arraySizes = {3,5,7,9,11,13,11,9,7,5,3};
    public StopBoard(){

        diceroller = new Random();
        rollValues = new ArrayList<>();
        diceImages = new ArrayList<>();
        combinations = new ArrayList<>();
        runnerCount = 2;
        movesToMake = 0;
        // Initialize the UI
        this.setSize(1200, 800);
        this.setLayout(new BorderLayout());
        board = new JPanel();
        board.setLayout(new FlowLayout());
        board.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // add columns to the board
        boardColumns = new ArrayList<>();
        int j = 0;
        for(int i : arraySizes) {
            StopColumn newColumn = new StopColumn(i);
            JLabel columnLabel = new JLabel(Integer.toString(j + 2));
            newColumn.column.get(0).add(columnLabel);
            board.add(newColumn);
            boardColumns.add(newColumn);
            newColumn.addMouseListener(this);
            j += 1;
        }
        
        
        // initialize the right side panel
        rightSidePanel = new JPanel();
        rightSidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightSidePanel.setLayout(new BoxLayout(rightSidePanel, BoxLayout.PAGE_AXIS));
        rightSidePanel.setPreferredSize(new Dimension(500,800));
        
        
        // initialize the left side panel
        leftSidePanel = new JPanel();
        leftSidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        

        // initialize the sub-elements
        infoLabel = new JLabel("Can't Stop!");
        infoLabel.setSize(100, 100);
        infoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        turnLabel = new JLabel("It's Your Turn!");
        turnLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // panel to contains dice images
        rollBox = new JPanel();
        rollBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // label containing dice info
        rollLabel = new JLabel("");
        rollLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // roll button, arraylist to hold dice values
        rollButton = new JButton("Roll!");
        rollValues = new ArrayList<>();

        rollButton.addActionListener(e -> {
            if(rollValues.isEmpty()){
                rightSidePanel.remove(endButton);
                turnStatusLabel.setText("Group your dice!");
                movesToMake = 2;
                leftDiceLabel.setText("");
                rightDiceLabel.setText("");
                generateDice();
                generateCombinations();
                generatePairs();
                combinations = new ArrayList<>();
            }
            
        });
        

        optionsButton = new JButton("Options");
        optionsButton.addActionListener(e -> {
            openOptionsMenu();
    });
        leftSidePanel.add(optionsButton);

        // set up dice selection panels
        leftDicePanel = new JPanel();
        leftDicePanel.setPreferredSize(new Dimension(225,300));
        leftDicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftDicePanel.setLayout(new BoxLayout(leftDicePanel, BoxLayout.Y_AXIS));

        leftDiceLabel = new JLabel("");
        leftDicePanel.add(leftDiceLabel);



        rightDicePanel = new JPanel();
        rightDicePanel.setPreferredSize(new Dimension(225,300));
        rightDicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightDicePanel.setLayout(new BoxLayout(rightDicePanel, BoxLayout.Y_AXIS));

        rightDiceLabel = new JLabel("");
        rightDicePanel.add(rightDiceLabel);
        
        // add dice selection panels to their container panel
        dicePanel = new JPanel(new FlowLayout());
        dicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dicePanel.add(leftDicePanel);
        dicePanel.add(rightDicePanel);
        
        //initialize runnerPanel
        runnerPanel = new JPanel();     
        runnerPanel.setLayout(new FlowLayout());
        
        turnStatusLabel = new JLabel("Roll the Dice!");  
        endButton = new JButton("End Turn");

        

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
        
        

        
        // load piece and runner assets and add to runnerPanel
        try{
            piece = ImageIO.read(new File("assets/" + "redpiece.png"));
        }catch(Exception f){
            System.out.println("Error: File not found");
        } 

        try{
            runner = ImageIO.read(new File("assets/" + "runner.png"));
        }catch(Exception f){
            System.out.println("Error: File not found");
        } 

        for(int i = 0; i < 3; i++)
            runnerPanel.add(new JLabel(new ImageIcon(piece)));
        for(int i = 0; i < 3; i++){
            runnerPanel.add(new JLabel(new ImageIcon(runner)));
        }
        
        


        // add the top level panels to the content pane
        getContentPane().add(leftSidePanel, BorderLayout.LINE_START);
        getContentPane().add(board, BorderLayout.CENTER);
        getContentPane().add(rightSidePanel, BorderLayout.LINE_END);
        
        // set Frame details
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
    }
    /** Generate Dice
     *  
     * 
     * 
     */
    public void generateDice(){
        
        // generate the rolls, add to the panel
        rollValues.clear();
        for(int i = 0; i < 4; i+=1){
            rollValues.add(diceroller.nextInt(6) + 1);
        
            try{
                die = ImageIO.read(new File("assets/die" + Integer.toString(rollValues.get(i)) + ".png"));
            }       
            catch(Exception f){
                System.out.println("Error: File not found");
            }
            diceImages.add(new JLabel(new ImageIcon(die)));
            rollBox.add(diceImages.get(i)); 
        }
        diceImages.clear();
    }
    public int generateCombinations(){
        DiceCombo dice;
        combinations.clear();
        for(int x = 0; x < 4; x+=1){
            for(int y = x+1; y < 4; y+=1){
                dice = new DiceCombo(rollValues.get(x), rollValues.get(y));
                combinations.add(dice);
        }
    }

    return 0;
        }

    public void generatePairs(){
        JButton pair;
        String text = "";
        for(int i = 0; i < combinations.size(); i+=1){
            DiceCombo combo = combinations.get(i);
            text = "(" + combo.getX() + " + " + combo.getY() + ")" + " = " + combo.sum();
            pair = new JButton(text);
            pair.addActionListener(e -> {
                selectColumns(combo);
            });
            leftDicePanel.add(pair);
        }
    }
    public void selectColumns(DiceCombo combo){
        System.out.println("Place Runners");
        col1 = 0;
        col2 = 0;

        col1 = combo.getX() + combo.getY();
        rollValues.remove(rollValues.indexOf(combo.getX()));
        rollValues.remove(rollValues.indexOf(combo.getY()));
        col2 = rollValues.get(0) + rollValues.get(1);
        leftDicePanel.removeAll();
        this.repaint();
        leftDicePanel.add(leftDiceLabel);
        leftDiceLabel.setText("Place a Runner in Column " + Integer.toString(col1));
        rightDiceLabel.setText("Place a Runner in Column " + Integer.toString(col2));
        turnStatusLabel.setText("Advance a runner in available columns!");
        rollBox.removeAll();
        rollValues.clear();

        
        
        if((col1 == col2) & (runnerCount >= 0)) runnerCount += 1;
        if(boardColumns.get(col1 - 2).columnHasRunner()){
            if(runnerCount < 0){
                boardColumns.get(col1 - 2).activateColumn();
            }
        }
        else if(runnerCount >= 0){
            boardColumns.get(col1 - 2).activateColumn();
        }

        if(boardColumns.get(col2 - 2).columnHasRunner()){
            if(runnerCount < 0){
                boardColumns.get(col2 - 2).activateColumn();
                
        }
        }
        else if(runnerCount >= 0){
            boardColumns.get(col2 - 2).activateColumn();
           }
    
        if(noColumnActive()){
            bust();
        }
        
    }


    public boolean noColumnActive(){
        for(StopColumn col : boardColumns){
            if (col.isActive())
                return false;
        }
        return true;
    }
    public void bust(){
        turnStatusLabel.setText("You Are Bust! End your Turn Now.");
        leftDiceLabel.setText("BUST!");;
        rightDiceLabel.setText("BUST!");
        endButton = new JButton("End Turn");
        endButton.addActionListener(e -> {
            endTurn(true);
        });
        rightSidePanel.add(endButton);
        this.repaint();
        this.revalidate();
    }
    public void endTurn(boolean Bust){
        if(!Bust){
            for(StopColumn col : boardColumns){
                col.unSetRunner();
                col.resetColumn();
                col.placePieces();
            }
        }
        else{
            turnStatusLabel.setText("You lost all your runners!");
            for(StopColumn col : boardColumns){
                col.unSetRunner();
                col.resetColumn();
                for(StopTile tile : col.column){
                    if(col.column.indexOf(tile) != 0)
                        tile.clear();
                }
            }
        }
        leftDiceLabel.setText("");
        rightDiceLabel.setText("");
        rollBox.removeAll();
        rightSidePanel.remove(endButton);
        turnStatusLabel.setText("Roll Your Dice!");
        Bust = false;
        runnerCount = 2;
        this.repaint();
        this.revalidate();
    }
   
    
    public void openOptionsMenu(){
        options = new OptionsMenu(this);
    }
    public String passData(){
        String data = "";
        
        return data;

    }
    public void mouseClicked(MouseEvent e){
        Object col = e.getSource();
        if(col instanceof StopColumn){
            StopColumn selected = (StopColumn) col;
            if(selected.isActive()){
                selected.deactivateColumn();
                if(runnerCount >= 0){
                    selected.advanceRunner();
                    runnerCount -= 1;
                }
                else if (boardColumns.indexOf(selected) == col1 - 2 | boardColumns.indexOf(selected) == col2 - 2){
                    selected.advanceRunner();
                    runnerCount -= 1;
                }
                System.out.println("Runner count: " +  runnerCount);
                if(runnerCount == -1){
                    for(StopColumn column : boardColumns)
                        column.deactivateColumn();
                }
                if((runnerCount < 0) & rollValues.isEmpty()){
                    endButton = new JButton("End");
                    endButton.addActionListener(ev -> {
                        endTurn(false);
                    });
                    rightSidePanel.add(endButton);
                    turnStatusLabel.setText("End your turn or roll again!");    
                }
                else{
                    turnStatusLabel.setText("Roll Again!");
                }
                                   }

            }
        
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
}