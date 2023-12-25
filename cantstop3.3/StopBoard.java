import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
public class StopBoard extends JFrame implements MouseListener{
    
    // UI Elements
    private HashMap<Player, ArrayList<String>> playerDetailMap;
    // panels
    private JPanel board;
    private JPanel leftSidePanel, rightSidePanel;
    private JPanel dicePanel, leftDicePanel, rightDicePanel;
    private JPanel rollBox;
    private JPanel runnerPanel;
    Boolean flag = false;


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
    
    private BufferedImage runner;
    private BufferedImage piece;
    
    //Back-End Elements
    

    
    // current player
    private Player currentPlayer;
    private Player loadPlayer;
    private PlayerSelector selecter;

    // arraylists
    private ArrayList<StopColumn> boardColumns;
    private ArrayList<Integer> rollValues;
    private ArrayList<DiceCombo> combinations;

    // HashMaps
    private HashMap<Player, ArrayList<Integer>> playerPieces;
    private HashMap<Player, String> colourMap;
    private HashMap<Player, String> nameMap;
    private String currentPlayerColour;

    private OptionsMenu options;

    // member variables    
    public int movesToMake;
    private boolean runnersPlaced;
    private int runnerCount;
    private int col1, col2;
    private int[] arraySizes = {3,5,7,9,11,13,11,9,7,5,3};
    private static int NUM_PLAYERS;
    
    
    
    
    //constructor: initialize the UI and setup game stats for first time
    public StopBoard(int numPlayers, HashMap<Player, ArrayList<String>> playerDetail){
        NUM_PLAYERS = numPlayers;
        colourMap = new HashMap<>();
        nameMap = new HashMap<>();
        runnersPlaced = false;
        playerDetailMap = playerDetail;
        // get the first player
        setStartingPlayer();
        System.out.println(currentPlayer);
        // set the players colours
        setPlayerColours();
        System.out.println(colourMap);
        //get the players names
        setPlayerNames();
        System.out.println(nameMap);


        currentPlayerColour = colourMap.get(currentPlayer).toLowerCase();
        // dice holders
        rollValues = new ArrayList<>();
        combinations = new ArrayList<>();
        
        //initialize the board-state storage
        initPlayerMap();
        
        // initialize the turn passer
        selecter = new PlayerSelector();
            
        // turn counters
        runnerCount = 0;
        movesToMake = 0;

        // Initialize the UI
        this.setSize(1400, 800);
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
        
        //set turn label
        turnLabel = new JLabel(nameMap.get(currentPlayer) +  ", It's Your Turn!");
        turnLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // panel to contains dice images
        rollBox = new JPanel();
        rollBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // label containing dice info
        rollLabel = new JLabel("");
        rollLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // roll button: set action listener to progress turn
        rollButton = new JButton("Roll!");
        rollButton.addActionListener(e -> {
            rightSidePanel.remove(endButton);
            if(rollValues.isEmpty()){
                
                movesToMake = 2;

                rightSidePanel.remove(endButton);
                turnStatusLabel.setText("Group your dice!");
                
                leftDiceLabel.setText("");
                rightDiceLabel.setText("");
                
                generateDice();
                generateCombinations();
                generatePairs();
                
                combinations = new ArrayList<>();
            }
            
        });
        
        // initialize the options button
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
        
        // init turn status label, end button
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
        // to be fixed
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

    public void setStartingPlayer(){
        ArrayList<Integer> turnRolls = new ArrayList<>();
        for(Player p : playerDetailMap.keySet()){
            turnRolls.add(Integer.valueOf(playerDetailMap.get(p).get(4)));
        }
        currentPlayer = Player.valueOf("player" + Integer.toString(turnRolls.indexOf(Collections.max(turnRolls)) + 1));
    }
    public void setPlayerColours(){
        for(Player p: playerDetailMap.keySet()){
            colourMap.put(p, playerDetailMap.get(p).get(1));
        }
    }
    public void setPlayerNames(){
        for(Player p : playerDetailMap.keySet()){
            nameMap.put(p, playerDetailMap.get(p).get(0));
        }
    }
    /** Generate Dice
     */
    public void generateDice(){
        
        // generate the rolls, add to the panel
        DiceRoll roll;
        for(int i = 0; i < 4; i+=1){
            roll = new DiceRoll();
            rollValues.add(roll.value());
            rollBox.add(roll.generateDie()); 
        }
    }
    // fix the algorithm to use Roll object. Update logic. 
    public void generateCombinations(){
        DiceCombo dice;
        combinations.clear();
        for(int x = 0; x < 4; x+=1){
            for(int y = x+1; y < 4; y+=1){
                dice = new DiceCombo(rollValues.get(x), rollValues.get(y));
                combinations.add(dice);
            }
        }
    }
    // generate buttons so the player can select their columns
    public void generatePairs(){
        JButton pair;
        String text = "";
        int sum = 0;
        for (int val: rollValues){
            sum += val;
        }
        leftDiceLabel.setText("Select columns to advance!");
        for(int i = 0; i < combinations.size(); i+=1){
            DiceCombo combo = combinations.get(i);
            text = Integer.toString(combo.sum()) + " , " + Integer.toString(sum - combo.sum());
            pair = new JButton(text);
            pair.addActionListener(e -> {
                selectColumns(combo);
            });

            leftDicePanel.add(pair);
        }
    }
    // on column select: activate the selected columns, if valid.
    public void selectColumns(DiceCombo combo){
        // clear the label, determine column values.
        leftDiceLabel.setText("");
        col1 = 0;
        col2 = 0;
        col1 = combo.getX() + combo.getY();
        rollValues.remove(rollValues.indexOf(combo.getX()));
        rollValues.remove(rollValues.indexOf(combo.getY()));
        col2 = rollValues.get(0) + rollValues.get(1);

        // clear the panel and add runner advancement labels
        leftDicePanel.removeAll();
        this.repaint();
        leftDicePanel.add(leftDiceLabel);
        leftDiceLabel.setText("Place a Runner in Column " + Integer.toString(col1));
        rightDiceLabel.setText("Place a Runner in Column " + Integer.toString(col2));
        turnStatusLabel.setText("Advance a runner in available columns!");
        
        // reset the dice stats
        rollBox.removeAll();
        rollValues.clear();


        //runner placement logic
        // set runner placement height above current pieces
        for(int i = 0; i < boardColumns.size(); i++){
            if(playerPieces.get(currentPlayer).get(i) < boardColumns.get(i).column.size()){
                boardColumns.get(i).setRunnerIdx(playerPieces.get(currentPlayer).get(i) - 1);
            }
        }   
        // if 3 runners have been placed, do not activate non-occupied columns
        if(runnerCount >= 3){
            if(boardColumns.get(col1 - 2).getRunnerIdx() < boardColumns.get(col1 - 2).column.size()){
                boardColumns.get(col1 - 2).activateColumn();
            }
            if(boardColumns.get(col2 - 2).getRunnerIdx() < boardColumns.get(col2 - 2).column.size()){
                boardColumns.get(col2 - 2).activateColumn();
            }
        }
        else {
            boardColumns.get(col1 - 2).activateColumn();
            boardColumns.get(col2 - 2).activateColumn();

        }

        // check for bust
        if(noColumnActive()){
            bust();
        }
    }
    public void columnSelected(StopColumn selected){
        if(selected.isActive()){
            selected.deactivateColumn();
            selected.advanceRunner();
            if(!selected.columnHasRunner())
                runnerCount += 1;
            if(runnerCount == 3){
                runnersPlaced = true;
                for(StopColumn col : boardColumns){
                    col.deactivateColumn();
                }
            }
            if(runnerCount == 3 & rollValues.isEmpty()){
                rightSidePanel.remove(endButton);
                endButton = new JButton("End");
                endButton.addActionListener(ev -> {
                    passTurn(false);
                });
                rightSidePanel.add(endButton);
                turnStatusLabel.setText("End your turn or roll again!");    
                this.repaint();
                this.revalidate();
            }
            else{
                turnStatusLabel.setText("Roll Again!");
            }
        }

    }

    // check for bust condition
    public boolean noColumnActive(){
        for(StopColumn col : boardColumns){
            if (col.isActive())
                return false;
        }
        return true;
    }
    
    //update UI on reaching bust state
    public void bust(){
        turnStatusLabel.setText("You Are Bust! End your Turn Now.");
        leftDiceLabel.setText("BUST!");;
        rightDiceLabel.setText("BUST!");
        endButton = new JButton("End Turn");
        endButton.addActionListener(e -> {
         passTurn(true);
        });
        rightSidePanel.add(endButton);
        
        this.repaint();
        this.revalidate();
    }

    // pass the turn to the next player
    // clean the game UI
    // reset the game board state
    public void passTurn(boolean Bust){
        // non-bust: replace runners with pieces
        
        for(StopColumn col : boardColumns){
            // replace runners with pieces
            if (!Bust) {
                col.placePieces(currentPlayerColour);
            }
            
            //reset columns
            col.unSetRunner();
            col.resetColumn();    
        
        }
        String text = "You have successfully advanced your pieces!";
        if (Bust) text = "You have lost all your runners!";
        JOptionPane.showMessageDialog(this, text);
        storePieces(currentPlayer);
        // pass control to the next player
        currentPlayer = selecter.nextPlayer(currentPlayer, NUM_PLAYERS);
        currentPlayerColour = getCurrentColour().toLowerCase();

        //refresh ui and game stats
        resetBoard();
    }

    public void resetBoard(){
        // reset the ui
        leftDiceLabel.setText("");
        rightDiceLabel.setText("");
        rollBox.removeAll();
        rightSidePanel.remove(endButton);
        turnStatusLabel.setText("Roll Your Dice!");

        setColumns();

        // Set turn label
        turnLabel.setText(nameMap.get(currentPlayer) + ", It's Your Turn!");

        //put the column numbers on the board
        for(StopColumn col : boardColumns){
            JLabel colLabel = new JLabel(Integer.toString(boardColumns.indexOf(col) + 2));
            col.column.get(0).add(colLabel);
        }

        // reset game stats
        runnersPlaced = false;
        runnerCount = 0;
        movesToMake = 0;
        this.repaint();
        this.revalidate();

    }

    public String getCurrentColour(){
        return colourMap.get(currentPlayer);
    }
    /** On the passing of a turn
    *   clear the columns
        set the runner Idx and Piece idx
        draw it onto the column
    */
    public void setColumns(){
        // set the column stats from playerPieces map
        for(int i = 0; i < boardColumns.size(); i++){
            StopColumn col = boardColumns.get(i);
            col.setPieceIdx(playerPieces.get(currentPlayer).get(i));
            col.setRunnerIdx(col.getPieceIdx());
        }
        //clean the board
        for (StopColumn col: boardColumns){
            col.clean();
            col.setPieces(currentPlayerColour);
        }
        
    }

    // store the player's current board state to the hashmap
    public void storePieces(Player currentPlayer){
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < 11; i++){
            list.add(boardColumns.get(i).getPieceIdx());
        }
        playerPieces.put(currentPlayer, list);
    }
    
    // opens the options menu
    public void openOptionsMenu(){
        options = new OptionsMenu(this);
    }
    
    // generate and return text for save file

    /** save game data encoding
     *    playerx
     *    playerx pieces
     *    
     *    playery
     *    playery pieces
     *    ...
     * 
     *    currentPlayer
     *    currentPlayer runners
     *    movesToMake
     * 
     */
    public String passData(){
        // init data string

        
        String data = "";
        for(int i = 0; i < NUM_PLAYERS; i++){
            // add the current player
            
            Player player = Player.values()[i];
            data += player.name() + "\n";
            
            //add the currently player's pieces
            ArrayList<Integer> pieces = playerPieces.get(player);
            for(int j : pieces){
                data += Integer.toString(j) + ",";
            }
            data += "\n";
        }
        data += "flag\n";
        data += currentPlayer.name() + "\n";
        //add the current player's runner counts
        
        for(StopColumn col : boardColumns){
            if(col.columnHasRunner()){
                data += "1,";
            }
            else data += "0,";
        }
        data += "\n";
        for(StopColumn col : boardColumns){
            data += Integer.toString(col.getRunnerIdx()) + ",";
        }
        data += "\n";
        data += Integer.toString(movesToMake) + "\n";
        
        return data;
    }

    public void loadGameData(String data){
        String loadData = data;
        String[] loadLines = loadData.split("\n");
        this.NUM_PLAYERS = (loadLines.length - 6) / 2;
        for(String line : loadLines){
            processLine(line);
        }
        System.out.println(playerPieces.get(Player.player1));
        System.out.println(playerPieces.get(Player.player2));
        System.out.println(playerPieces.get(Player.player3));
        System.out.println(playerPieces.get(Player.player4));
    }

    public void processLine(String line){
        
        String[] vals;        
        //set flag

        if(line.contains("flag")){
            flag = true;
        }
        // every other line
        else{
            // line to set player
            if(line.contains("player")){
                //to update hashmap
                if(flag == false){
                    loadPlayer = Player.valueOf(line);
                //to update board
                }
                else{
                    currentPlayer = Player.valueOf(line);
                }
            }   
            // line does is not set to player
            else if (line.length() > 5){
                ArrayList<Integer> pieces = new ArrayList<>();
                vals = line.split(",");
                for(String val : vals){
                    pieces.add(Integer.valueOf(val));
                }
                //update the piece map
                if(flag == false){
                    System.out.println("Player:" + loadPlayer);
                    setPlayerPieces(pieces, loadPlayer);
                    System.out.println("Player Pieces:" + pieces);
                }
                else{
                    if(pieces.contains(0)){
                        loadRunners(pieces);
                    }
                    else{
                        loadPieces(pieces);
                    }
            }   
            }
        }
    }
    public void loadRunners(ArrayList<Integer> pieces){
        for(int i = 0; i < boardColumns.size(); i++){
            StopColumn col = boardColumns.get(i);
            if((col.column.size() - pieces.get(i)) != col.column.size())
                col.column.get(col.column.size() - pieces.get(i)).occupy();
        }
        this.repaint();
        this.revalidate();
    }

    public void loadPieces(ArrayList<Integer> pieces){
        for(int i = 0; i < boardColumns.size(); i++){
            StopColumn col = boardColumns.get(i);
            if((pieces.get(i)) != col.column.size())
                col.column.get(pieces.get(i)).fill(currentPlayerColour);
                col.setRunnerIdx(pieces.get(i));
        }
        this.repaint();
        this.revalidate();
    }
    // update pieces map
    public void setPlayerPieces(ArrayList<Integer> pieces, Player player){
        playerPieces.put(player, pieces);
    }

    public void loadBoard(){

    }
    // initialize the hashmap which stores player's progress
    public void initPlayerMap(){
        playerPieces = new HashMap<>();
        ArrayList<Integer> STARTING_POSITION = new ArrayList<>();
        
        for(int i: arraySizes){
            STARTING_POSITION.add(i);
        }

        Player[] players = {Player.player1, Player.player2, Player.player3, Player.player4};
        
        for(int i = 0; i < NUM_PLAYERS; i++){
            playerPieces.put(players[i], STARTING_POSITION);
        }
    }



    // refactor this logic into it's own method, 
    //call it from inside mouse clicked
    public void mouseClicked(MouseEvent e){
        Object col = e.getSource();
        if(col instanceof StopColumn){
            StopColumn selected = (StopColumn) col;
            columnSelected(selected);
        }
    }
    
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
}