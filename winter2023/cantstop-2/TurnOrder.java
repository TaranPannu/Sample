import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class TurnOrder extends JFrame {
    private JLabel title;
    JLabel lb;
    int dicevalue=0;
    private JButton rollButton;
    private JButton back;
    private JButton start;
    private JPanel rollBox;
    JPanel WestPanel;
   // private JLabel rollLabel;
    //private Player currentPlayer;
 //   private PlayerSelector selector;
    //private ArrayList<Integer> rollVals;
    //private JButton nextButton;
    HashMap<String,ArrayList<String>> PLAYER=new HashMap<>();
    int rollcount=0;
    JPanel panel;
    int playerCount;
    JPanel EastPanel ;
    SetupScreen setupScreen;
    public TurnOrder(SetupScreen setupScreen, int numPlayers)
    {
        this.setupScreen=setupScreen;
        playerCount=numPlayers;
        this.setSize(1400, 800);
        this.setLayout(new BorderLayout());
    //    selector = new PlayerSelector();
        this.playerCount = numPlayers;
     //   rollVals = new ArrayList<>();
       // currentPlayer = Player.player1;
        title = new JLabel("Roll to Determine Turn Order!");
        JPanel optionsPanel = new JPanel();
         WestPanel = new JPanel();
         EastPanel= new JPanel();
         rollBox = new JPanel();

        EastPanel.setLayout(new BoxLayout(EastPanel, BoxLayout.Y_AXIS));
        WestPanel.setPreferredSize(new Dimension(500, 800));
        EastPanel.setPreferredSize(new Dimension(900, 800));

        back = new JButton("BACK");
        start = new JButton("START");
        rollButton=new JButton("Roll");

     //   nextButton = new JButton("Okay");
        //rollBox.setLayout(new BoxLayout(rollBox, BoxLayout.Y_AXIS));
       // rollLabel = new JLabel(currentPlayer.name() + ", Roll!");
     panel=AddPlayer_To_East_Panel(setupScreen,numPlayers);
     EastPanel.add(panel);
     EastPanel.add(rollButton,BorderLayout.CENTER);

        optionsPanel.add(back);
        optionsPanel.add(start);
              
        rollButton.addActionListener(e -> {
            if(rollcount>=playerCount)
            return;
            TextDisplay("Player "+rollcount+" Turn",EastPanel);
            rollBox.removeAll();
         rollBox=(generatePair(new JPanel()));
         WestPanel.add(rollBox, BorderLayout.CENTER);
         this.remove(WestPanel);
         this.add(WestPanel, BorderLayout.WEST);        
         //to display value of score in each player panel
         DisplayDiceValue();
//to repaint west panel after every roll
         SwingUtilities.updateComponentTreeUI(this);
         this.invalidate();
         this.validate();
         this.repaint();
         rollcount+=1;
        });


        back.addActionListener(i->{
          setupScreen.setVisible(true);
       });

        start.addActionListener(i->{
            System.out.println(PLAYER);
            this.setVisible(false);
            StopBoard game = new StopBoard(numPlayers,PLAYER);
        });

        WestPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        EastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

       this.add(optionsPanel, BorderLayout.SOUTH);
       this.add(EastPanel, BorderLayout.EAST);
       this.add(WestPanel, BorderLayout.WEST);
       setVisible(true);
       getHashMap();
    }

    HashMap getHashMap(){
ArrayList<PlayerCard> ob=setupScreen.getPlayer();
System.out.println(ob.size());
for (PlayerCard playerCard : ob) {
    ArrayList<String> obj=new ArrayList<>();
obj.add(playerCard.getPlayerName());
obj.add(playerCard.getPlayercolor());
obj.add(playerCard.getPlayerLevel());
if(playerCard.isComputerPlayer()){
    obj.add("true");
}else{
    obj.add("false");
}
    PLAYER.put(playerCard.getPlayerName(), obj);
}
        return PLAYER;
    }


      void DisplayDiceValue() {
        if(rollcount>=playerCount)
        return;
        Component x[]=panel.getComponents();
        JPanel c;
        if(rollcount==0 || rollcount==1)
         c=(JPanel)x[0];
        else
         c=(JPanel)x[1];
     Component a[]=c.getComponents();
       if(rollcount<2)
       c.remove(a[rollcount]);
       else
       c.remove(a[rollcount-2]);
      
  JPanel JP=createPanel(dicevalue);
       if(rollcount==0 || rollcount==1)
         {   c.add(JP,rollcount);
          panel.add(c,0);}else{
           c.add(JP,rollcount-2);
          panel.add(c,1);
          }
    }


   void TextDisplay(String text,JPanel East){
 lb=new JLabel(text+"");
 WestPanel.add(lb,BorderLayout.CENTER);
    }
JPanel createPanel(int value){
    JPanel JP=new JPanel();
    JLabel text=new JLabel(""+value);
   
    JP.setLayout(new BorderLayout());
    JP.add(text,BorderLayout.CENTER);
    JP.setPreferredSize(new Dimension(200,200));

    JP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return JP;
}


    JPanel AddPlayer_To_East_Panel(SetupScreen ob,int numPlayers) {
JPanel obj=new JPanel();
JPanel upper=new JPanel();
JPanel lower=new JPanel();
System.out.println("no. of player:-"+numPlayers);
for (int i = 1; i <=numPlayers; i++) {
    if(i==1 || i==2){
        upper.add(createPanel(0));
    }
    else{    
        lower.add(createPanel(0));  
    }
}
obj.add(upper,BorderLayout.NORTH);
obj.add(lower,BorderLayout.SOUTH);
return obj;
    }

public void AddPriority_MAP(int diceValue){
    ArrayList<String> v=new ArrayList<>();
    v=PLAYER.get(setupScreen.getPlayer().get(rollcount).getPlayerName());
    v.add(Integer.toString(diceValue)+"");

}

    public JPanel generatePair(JPanel west){
        DiceRoll die1 = new DiceRoll();
        DiceRoll die2 = new DiceRoll();
     west.removeAll();
     west.add(die1.generateDie(),BorderLayout.NORTH);
     west.add(die2.generateDie(),BorderLayout.CENTER);
System.out.println(die1.value()+die2.value());
dicevalue=die1.value()+die2.value();
      AddPriority_MAP(dicevalue);
        return west;
    }


    /*public void getNext(){
        clearDisplay();
        if(rollVals.size() == playerCount){
            displayTotal();
        }
        else{
            currentPlayer = selector.nextPlayer(currentPlayer, playerCount);
            rollLabel.setText(currentPlayer.name() + ", Roll!");
        }
        
    }
    public void clearDisplay(){
        rollBox.removeAll();
        rollLabel.setText("");
        rollBox.add(rollLabel);
        rollBox.add(rollButton);
        this.repaint();
        this.revalidate();
    }

    public void displayTotal(){
        clearDisplay();
        rollLabel.setText(getHighestRoller(new ArrayList<>()).name() + ",   You rolled the highest!");
    }

    public Player getHighestRoller(ArrayList<Player> highest){
        int max = 0;
        for(int i = 0; i < playerCount;i++){
            if(rollVals.get(i) > max){
                highest.clear();
                highest.add(Player.valueOf("player" + Integer.toString(i + 1)));
            }
            else if(i == max){
                highest.add(Player.valueOf("player" + Integer.toString(i + 1)));
            }
        }
        return highest.get(0);
    }*/

}

