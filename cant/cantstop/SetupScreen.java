import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class SetupScreen extends JFrame implements ActionListener, MouseListener{
ArrayList<PlayerCard> m1=new ArrayList<>();
    // Top level elements
    private JLabel title;
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
    private  JButton popUPexit;
    private int PlayerCount;
    public SetupScreen(selectPlayer select){
    //    String[] difficulty = {"Plain Colored Marker", "Textured marker"};
     //JComboBox<String> mark = new JComboBox<>(difficulty);
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        title = new JLabel("CAN'T STOP");
        titlePanel = new JPanel();
        playerPanel1 = new JPanel();
        playerPanel2 = new JPanel();
        optionsPanel = new JPanel();
        player=new JPanel(new BorderLayout());
        
        help = new JButton("HELP");
        start = new JButton("START");
        exit = new JButton("EXIT");
        settings =new JButton("SETTINGS");
        back = new JButton("BACK");
        xx(select);
        titlePanel.add(title);
        optionsPanel.add(settings);
        optionsPanel.add(help);
        optionsPanel.add(start);
        optionsPanel.add(exit);
        optionsPanel.add(back);
        player.add(playerPanel1,BorderLayout.NORTH);
        player.add(playerPanel2,BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
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
       Turn turn=new Turn(this);
       this.setVisible(false);

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
        JLabel label=new JLabel();
        label.setText("SETTINGS!!");

        String[] difficulty = {"Plain Colored Marker", "Textured marker"};
        JComboBox<String> mark = new JComboBox<>(difficulty);

        popUPexit=new JButton("EXIT");
        popup.add(popUPexit);
        popup.add(label,BorderLayout.NORTH);
        popup.add(label,BorderLayout.NORTH);
        popup.add(mark,BorderLayout.SOUTH);

         p = PopupFactory.getSharedInstance().getPopup(this, popup, 250,150);
        p.show();
        
        popUPexit.addActionListener(i->{
            p.hide();
        });
    }
    void showPopup() {
         popup = new JPanel();
        popup.setPreferredSize(new Dimension(300, 300));
        JLabel label=new JLabel();
        
        label.setText("HELP!!");
         popUPexit=new JButton("EXIT");
      //  popup.setBackground(Color.lightGray);
        popup.add(popUPexit);
        popup.add(label);
         p = PopupFactory.getSharedInstance().getPopup(this, popup, 250,150);
        p.show();
        
        popUPexit.addActionListener(i->{
            p.hide();
        });
    }


    public void actionPerformed(ActionEvent e){
        System.out.println("Implement me!");
    }

    public void close(){
        this.removeAll();
    }
void xx(selectPlayer select){
    Set<String> AlreadySelectedcolor=new HashSet<>();
    PlayerCard ob;
for (int i = 1; i <=select.getPlayerCount(); i++) {
  if(i==1 || i==2){
     ob=new PlayerCard(AlreadySelectedcolor);
    m1.add(ob);
    playerPanel1.add(ob);
  }  else{
    ob=new PlayerCard(AlreadySelectedcolor);
    m1.add(ob);
    playerPanel2.add(ob);

  }
}
    /*if(select.getPlayerCount()==4)
        {
        playerPanel1.add(new PlayerCard("Player 1",AlreadySelectedcolor));
        playerPanel1.add(new PlayerCard("Player 2", AlreadySelectedcolor));
        playerPanel2.add(new PlayerCard("Player 3", AlreadySelectedcolor));
        playerPanel2.add(new PlayerCard("Player 4", AlreadySelectedcolor));
        }
        else if(select.getPlayerCount()==3)
        {
            playerPanel1.add(new PlayerCard("Player 1", AlreadySelectedcolor));
            playerPanel1.add(new PlayerCard("Player 2", AlreadySelectedcolor));
            playerPanel2.add(new PlayerCard("Player 3", AlreadySelectedcolor));
        }
        else if(select.getPlayerCount()==2)
        {
            playerPanel1.add(new PlayerCard("Player 1", AlreadySelectedcolor));
            playerPanel1.add(new PlayerCard("Player 2", AlreadySelectedcolor));
        }  
        else if(select.getPlayerCount()==1)
        {
            playerPanel1.add(new PlayerCard("Player 1", AlreadySelectedcolor));
        }*/
}
ArrayList getPlayer(){

return m1;
}

    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
}