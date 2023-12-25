import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class TitleScreen extends JFrame{
    JLabel title;
    String st="";
    JComboBox<Integer> mark ;    
    private Image backgroundImage;//iter 4
    public TitleScreen(){
        this.setSize(1000, 700);// iter 4
        this.setLayout(new BorderLayout());
        title=new JLabel("", SwingConstants.CENTER);
title.setPreferredSize(new Dimension(400, 200));
title.setBorder(BorderFactory.createLineBorder(Color.BLACK));


JPanel centre=new JPanel();
try{// iter 4
    backgroundImage = ImageIO.read(new File("assets/" +"cant.png"));

    setContentPane(new JPanel(new BorderLayout()) {
        @Override public void paintComponent(Graphics g) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), title);

        }
    }); 
          
}
catch(Exception f){
    System.out.println("Error: File not Found");
}// iter 4

JPanel SavedGamePanel=new JPanel();
JPanel NewGamePanel=new JPanel();

NewGamePanel.setLayout(new BorderLayout());
JComboBox comp=count_player();
NewGamePanel.add(comp,BorderLayout.CENTER);

JLabel label_new_game=new JLabel("New Game");// iter 4
label_new_game.setHorizontalAlignment(SwingConstants.CENTER);// iter 4
label_new_game.setFont(new Font("Serif", Font.BOLD, 50));// iter 4
NewGamePanel.add(label_new_game,BorderLayout.NORTH);

JLabel label_load_game;// iter 4
label_load_game=new JLabel("Load Game");// iter 4
label_load_game.setFont(new Font("Serif", Font.BOLD, 50));// iter 4
label_load_game.setHorizontalAlignment(SwingConstants.CENTER);// iter 4

SavedGamePanel.setPreferredSize(new Dimension(350,300));
NewGamePanel.setPreferredSize(new Dimension(350,300));

centre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
NewGamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
SavedGamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

centre.setBackground( new Color(25, 143, 195));//itera 4 update
NewGamePanel.setBackground(new Color(181, 238, 246));//itera 4 update
SavedGamePanel.setBackground(new Color(181, 238, 246));//itera 4 update

JButton loadsavedGame = new JButton("Click Here");//itera 4 update
loadsavedGame.setForeground(Color.BLUE);//itera 4 update
loadsavedGame.setBackground(new Color(52, 152, 219));//itera 4 update
loadsavedGame.setFont(new Font("Arial", Font.BOLD, 16));//itera 4 update
loadsavedGame.setBorder(BorderFactory.createLineBorder(Color.black ,0));//itera 4 update

SavedGamePanel.setLayout(new BorderLayout());//iter 4
SavedGamePanel.add(label_load_game,BorderLayout.NORTH);//itera 4 update
SavedGamePanel.add(loadsavedGame,BorderLayout.CENTER);//itera 4 update

centre.add(NewGamePanel);
centre.add(SavedGamePanel);



      JPanel optionsPanel = new JPanel();
      JButton back=new JButton("HELP");
      JButton start=new JButton("START");

      optionsPanel.add(back);
      optionsPanel.add(start);

     
       loadsavedGame.addActionListener(i->{ //itera 4 update
        savedGame();
       });

       start.addActionListener(i->{
        
        SetupScreen setup = new SetupScreen(this, getPlayerCount());
        this.setVisible(false);

               });

    this.add(centre,BorderLayout.CENTER);
       this.add(title,BorderLayout.NORTH);
       this.add(optionsPanel,BorderLayout.SOUTH);
       setVisible(true);
    }

    private void savedGame() {      //itera 4 update
       
        JFileChooser fileChooser = new JFileChooser();
int result = fileChooser.showOpenDialog(null);
if (result == JFileChooser.APPROVE_OPTION) {
   File selectedFile = fileChooser.getSelectedFile();

   try {
    FileReader reader = new FileReader(selectedFile);
    BufferedReader bufferedReader = new BufferedReader(reader);
    String line;
    while ((line = bufferedReader.readLine()) != null) {
st+="\n"+line;
    }
    bufferedReader.close();
 } catch (IOException e) {
    e.printStackTrace();
 }
}

    }

    public JComboBox count_player(){
        Integer[] count = {1,2,3,4};
     mark = new JComboBox<>(count);

        return mark;
    }

    public int getPlayerCount(){
        return (int)mark.getSelectedItem();
    }
    public String getLoadGame(){//iter 4 update
        return st;
    }
}
