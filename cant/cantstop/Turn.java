import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
public class Turn extends JFrame {
    JLabel title;
    public Turn(SetupScreen t)
    {
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        title=new JLabel(" ROLL DICE TO SELECT YOUR TURN!", SwingConstants.CENTER);

      JPanel optionsPanel = new JPanel();

      JButton back=new JButton("BACK");
      JButton start=new JButton("START");

      optionsPanel.add(back);
      optionsPanel.add(start);

       back.addActionListener(i->{
        t.setVisible(true);
       });

       start.addActionListener(i->{
        this.setVisible(false);
        StopBoard game = new StopBoard();
               });

    
       this.add(title,BorderLayout.NORTH);
       this.add(optionsPanel,BorderLayout.SOUTH);
       setVisible(true);
    }
}
