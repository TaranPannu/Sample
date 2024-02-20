import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class PlayerCard extends JPanel{
    private String colorSelected;
    private JLabel playerLabel;
    private JLabel playerNum;
    private BufferedImage player;
    private JTextField PlayerName;
    private String[] diffi = {"Level","Easy","Hard"};
    private String[] Colour = {"Color", "Red", "Blue","Yellow","Green"};

    private JComboBox<String> diff = new JComboBox<>(diffi); 
    private JComboBox<String> color = new JComboBox<>(Colour);
    private JCheckBox confirm = new JCheckBox();

    public PlayerCard(Set<String> AlreadySelectedcolor){
    
        this.setPreferredSize(new Dimension(200,205));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.LIGHT_GRAY);

        confirm.setText("Computer Player");
        player = null;
        playerLabel = new JLabel();
        PlayerName=new JTextField();
        // when checkbox is selected.
        confirm.addActionListener(i->{
            this.setBackground(Color.LIGHT_GRAY);
            this.remove(diff);
            if(confirm.isSelected()){
                this.setBackground(Color.GRAY);
                difficulty();
            }
        });

        //When player will select color
        color.addActionListener(i->{
            this.colorSelected= (String)color.getSelectedItem();
            try{
                if(AlreadySelectedcolor.add(colorSelected)){
                    
                    player = ImageIO.read(new File("assets/" + colorSelected.toLowerCase() + "man.png"));
                }
                else{
                    player = ImageIO.read(new File("assets/" +"whiteman.png"));
                    new JPopupMenu ("A player has already selected that colour!");
                }
            }
            catch(Exception f){
                System.out.println("Error: File not Found");
            }
            playerLabel.setIcon(new ImageIcon(player)); 
            this.add(playerLabel);
            if(confirm.isSelected())
            {
                this.add(diff);
            }
            this.remove(PlayerName);
            this.add(PlayerName,BorderLayout.SOUTH);
            SwingUtilities.updateComponentTreeUI(this);
            this.invalidate();
            this.validate();
            this.repaint();
        });
        
        //default white color of user.
        try{
            player = ImageIO.read(new File("assets/" +"whiteman.png"));
        }
        catch(Exception f){
            System.out.println("Error: File not Found");
        }


        playerLabel.setIcon(new ImageIcon(player)); 
        playerNum = new JLabel(PlayerName.getText());
        this.add(playerNum);
    
        this.add(confirm);
        this.add(color);
        this.add(playerLabel);
       // PlayerName.setPreferredSize(new Dimension(100,5));
        this.add(PlayerName,BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void difficulty() {   

        //if(colorSelected.equals("Color"))
        {

            this.add(diff);
        }
        this.remove(PlayerName);
        this.add(PlayerName,BorderLayout.SOUTH);
        SwingUtilities.updateComponentTreeUI(this);
        this.invalidate();
        this.validate();
        this.repaint();
    }
   
    public String getPlayerName(){
        return PlayerName.getText(); 
    }
    
    public String getPlayercolor(){
        return colorSelected;
    }
    
    public String getPlayerLevel(){
        return (String)diff.getSelectedItem();
    }   

    public boolean isComputerPlayer(){
        return confirm.isSelected();
    }
}
