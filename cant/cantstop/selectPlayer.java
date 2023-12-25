import javax.swing.*;

import java.awt.*;
public class selectPlayer extends JFrame{
    JLabel title;
    JComboBox<Integer> mark ;    
    public selectPlayer(){
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        title=new JLabel("CAN'T STOP", SwingConstants.CENTER);
title.setPreferredSize(new Dimension(400, 200));
title.setBorder(BorderFactory.createLineBorder(Color.BLACK));


JPanel centre=new JPanel();
JPanel SavedGamePanel=new JPanel();
JPanel NewGamePanel=new JPanel();
NewGamePanel.add(count_player(),BorderLayout.SOUTH);
NewGamePanel.add(new JLabel("New Game"),BorderLayout.NORTH);
SavedGamePanel.add(new JLabel("Load Saved Game"),BorderLayout.NORTH);
SavedGamePanel.setPreferredSize(new Dimension(350,300));
NewGamePanel.setPreferredSize(new Dimension(350,300));
centre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
NewGamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
SavedGamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
centre.add(NewGamePanel);
centre.add(SavedGamePanel);


      JPanel optionsPanel = new JPanel();
      JButton back=new JButton("HELP");
      JButton start=new JButton("START");

      optionsPanel.add(back);
      optionsPanel.add(start);

       back.addActionListener(i->{
       });

       start.addActionListener(i->{
        
        SetupScreen setup = new SetupScreen(this);
        this.setVisible(false);

               });

    this.add(centre,BorderLayout.CENTER);
       this.add(title,BorderLayout.NORTH);
       this.add(optionsPanel,BorderLayout.SOUTH);
       setVisible(true);
    }

    JComboBox count_player(){
        Integer[] count = {1,2,3,4};
     mark = new JComboBox<>(count);
        return mark;
    }

    int getPlayerCount(){
   

        return (int)mark.getSelectedItem();
    }
}
