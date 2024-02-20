import javax.swing.*;

import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.io.File;

import javax.imageio.*;

public class TitleScreen extends JFrame{
    JLabel title;
    private  JComponent popup;
    private Popup p;
    private  JButton popUpExit;
    JComboBox<Integer> mark ; 
     private Image backgroundImage;

    public TitleScreen(){
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
      
        



title=new JLabel("", SwingConstants.CENTER);
title.setPreferredSize(new Dimension(400, 200));
title.setBorder(BorderFactory.createLineBorder(Color.BLACK));


JPanel centre=new JPanel();
try{
    backgroundImage = ImageIO.read(new File("assets/" +"cant.png"));
    setContentPane(new JPanel(new BorderLayout()) {
        @Override public void paintComponent(Graphics g) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), centre);
        }
    }); 
          
}
catch(Exception f){
    System.out.println("Error: File not Found");
}
JPanel SavedGamePanel=new JPanel();
JPanel NewGamePanel=new JPanel();
NewGamePanel.setLayout(new BoxLayout(NewGamePanel, BoxLayout.Y_AXIS));
NewGamePanel.add(new JLabel("New Game"));
NewGamePanel.add(count_player());
SavedGamePanel.add(new JLabel("Load Saved Game"),BorderLayout.NORTH);
SavedGamePanel.setPreferredSize(new Dimension(350,300));
NewGamePanel.setPreferredSize(new Dimension(350,300));
centre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
NewGamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
SavedGamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
centre.add(NewGamePanel);
centre.add(SavedGamePanel);


      JPanel optionsPanel = new JPanel();
      JButton help=new JButton("HELP");
      JButton start=new JButton("START");
      JButton exit=new JButton("EXIT");

      optionsPanel.add(help);
      optionsPanel.add(start);
      optionsPanel.add(exit);
       help.addActionListener(i->{
        showPopup();
       });

       start.addActionListener(i->{
        SetupScreen setup = new SetupScreen(this, getPlayerCount());
        this.setVisible(false);
               });
exit.addActionListener(i->{
    System.exit(0);
});
    this.add(centre,BorderLayout.CENTER);
       this.add(title,BorderLayout.NORTH);
       this.add(optionsPanel,BorderLayout.SOUTH);
       setVisible(true);
    }

    public JComboBox count_player(){
        Integer[] count = {1,2,3,4};
     mark = new JComboBox<>(count);
        return mark;
    }

    public int getPlayerCount(){
        return (int)mark.getSelectedItem();
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

}
