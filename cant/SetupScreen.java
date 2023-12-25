import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.awt.image.*;

public class SetupScreen extends JFrame implements ActionListener, MouseListener{
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
    private  JComponent popup;
    private Popup p;
    private  JButton popUPexit;

    public SetupScreen(){
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
        
        playerPanel1.add(new PlayerCard("Player 1", "redman.png"));
        playerPanel1.add(new PlayerCard("Player 2", "blueman.png"));
        playerPanel2.add(new PlayerCard("Player 3", "yellowman.png"));
        playerPanel2.add(new PlayerCard("Player 4", "greenman.png"));
        
        titlePanel.add(title);
        
        optionsPanel.add(help);
        optionsPanel.add(start);
        optionsPanel.add(exit);

        player.add(playerPanel1,BorderLayout.NORTH);
        player.add(playerPanel2,BorderLayout.CENTER);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(player, BorderLayout.CENTER);
        this.add(optionsPanel, BorderLayout.SOUTH);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);

        


        start.addActionListener(i->{
       if(p!=null)
       p.hide();
     StopBoard game = new StopBoard();
        });

        exit.addActionListener(i->{
            if(p!=null)
            p.hide();
            System.exit(0);
        });

        help.addActionListener(i->{
showPopup();
        });


    }

    void showPopup() {
         popup = new JPanel();
        popup.setPreferredSize(new Dimension(300, 300));
        JLabel label=new JLabel();
        label.setText("HELP ME DADDY!");
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


    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
}