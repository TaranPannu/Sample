import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.*;

public class SetupScreen extends JFrame implements ActionListener, MouseListener{
    HashMap<Player, ArrayList<String>> playerDetailsMap;
    ArrayList<PlayerCard> playerCards;
    // Top level elements
    TitleScreen select;//iter 4 update
    private JPanel titlePanel;
    private JPanel playerPanel1;
    private JPanel playerPanel2;
    private JPanel optionsPanel;
    private JPanel player;
    private JButton help;
    private JButton start;
    private JButton exit;
    private JButton settings;
    private JButton back;
    private  JComponent popup;
    private Popup p;
    private  JButton popUpExit;
    private int PlayerCount;
    private ArrayList<String> colours;
    
    private Player[] players = {Player.player1, Player.player2, Player.player3, Player.player4};

    public SetupScreen(TitleScreen select, int num_players){
        super("CAN'T STOP");//iter 4 delete title
        this.select=select;//iter 4 update
        String[] cols = {"Red", "Blue", "Yellow", "Green"};
        colours = new ArrayList<>();
        this.PlayerCount = num_players;
        for (String col : cols){
            colours.add(col);
        }
        getContentPane().setBackground( new Color(25, 143, 195));//itera 4 update

        this.setSize(1000, 700);
        this.setLayout(new BorderLayout());
        HashMap<Player, ArrayList<String>> playerDetailsMap = new HashMap<>();
        playerCards = new ArrayList<>();
        titlePanel = new JPanel();
        playerPanel1 = new JPanel();
        playerPanel2 = new JPanel();
        optionsPanel = new JPanel();
        player=new JPanel(new BorderLayout());
        
playerPanel1.setBackground( new Color(25, 143, 195));//itera 4 update
playerPanel2.setBackground( new Color(25, 143, 195));//itera 4 update

        help = new JButton("HELP");
        start = new JButton("START");
        exit = new JButton("EXIT");
        settings =new JButton("SETTINGS");
        back = new JButton("BACK");
        addPlayers(select);
        optionsPanel.add(settings);
        optionsPanel.add(help);
        optionsPanel.add(start);
        optionsPanel.add(exit);
        optionsPanel.add(back);
        player.add(playerPanel1,BorderLayout.NORTH);
        player.add(playerPanel2,BorderLayout.CENTER);
        this.add(player, BorderLayout.CENTER);
        this.add(optionsPanel, BorderLayout.SOUTH);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);

        
        settings.addActionListener(i->{
            showPopupSettings();
        });

        start.addActionListener(i->{
            if(p!=null)
                p.hide();
            
            HashMap<Player, ArrayList<String>> playerMap = generatePlayerDetails();
            Boolean ready = true;
            for(Player p : playerMap.keySet()){
                if(playerMap.get(p).contains("Enter Your Name!")){
                    JOptionPane.showMessageDialog(this, p.name() + ", Enter a name!");
                    ready = false;
                }   
            }
            if(ready){
                playerMap = generatePlayerDetails();
                TurnOrder turn = new TurnOrder(this, PlayerCount, playerMap);
                this.setVisible(false);
            }
            
        });

        exit.addActionListener(i->{
            if(p!=null)
            p.hide();
            System.exit(0);
        });

        help.addActionListener(i->{
            showPopup();
        });

        back.addActionListener(i->{
            this.setVisible(false);
            select.setVisible(true);;
        });

    }

    void showPopupSettings() {
        popup = new JPanel();
        popup.setPreferredSize(new Dimension(300, 300));
        JLabel label = new JLabel("SETTINGS!!");
    
        String[] difficulty = {"Plain Colored Marker", "Textured marker"};
        
        JComboBox<String> mark = new JComboBox<>(difficulty);

        popUpExit = new JButton("EXIT");
        popup.add(popUpExit);

        popup.add(label,BorderLayout.NORTH);
        popup.add(label,BorderLayout.NORTH);
        popup.add(mark,BorderLayout.SOUTH);

        p = PopupFactory.getSharedInstance().getPopup(this, popup, 250,150);
        p.show();
        
        popUpExit.addActionListener(i->{
            p.hide();
        });
    }

    void showPopup() {
        popup = new JPanel();
        popup.setPreferredSize(new Dimension(300, 300));
        JLabel label=new JLabel();
        label.setText("HELP!!");
        popUpExit = new JButton("EXIT");

        popup.add(popUpExit);
        popup.add(label);
        
        p = PopupFactory.getSharedInstance().getPopup(this, popup, 250,150);
        p.show();
        
        popUpExit.addActionListener(i->{
            p.hide();
        });
    }


    public void actionPerformed(ActionEvent e){
        System.out.println("Implement me!");
    }

    public void close(){
        this.removeAll();
    }

    public void addPlayers(TitleScreen select){
        PlayerCard nextPlayer = new PlayerCard(null);
        Set<String> AlreadySelectedcolor = new HashSet<>();

        for (int i = 1; i <=select.getPlayerCount(); i++) {
            if(i==1 || i==2){
                nextPlayer = new PlayerCard(AlreadySelectedcolor);
                playerCards.add(nextPlayer);
                playerPanel1.add(nextPlayer);
            }
            else{
                nextPlayer = new PlayerCard(AlreadySelectedcolor);
                playerCards.add(nextPlayer);
                playerPanel2.add(nextPlayer);
            
            }
        }
        colours.remove(nextPlayer.getPlayercolor());
    }
    public HashMap<Player, ArrayList<String>> generatePlayerDetails(){
        // init map
        HashMap<Player, ArrayList<String>> playerDetails = new HashMap<>();
        //loop through players
        
        for(int i = 0; i < PlayerCount; i++){
            PlayerCard p = playerCards.get(i);
            ArrayList<String> details = new ArrayList<>();
            details.add(p.getPlayerName());
            details.add(p.getPlayercolor());
            details.add(Boolean.toString(p.isComputerPlayer()));
            details.add(p.getPlayerLevel());
            playerDetails.put(players[i], details);
        }
        return playerDetails;
    }

    public ArrayList<PlayerCard> getPlayer(){

        return null;
    }
public String getLoadGame(){ //iter 4 update
    return select.getLoadGame();
}
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
}