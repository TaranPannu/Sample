import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class PlayerCard extends JPanel{
    
    // UI Elements for PlayerCard
    String[] difficulty = {"Easy", "Medium", "Hard"};
    final private JComboBox<String> diff = new JComboBox<>(difficulty);
    final private JCheckBox confirm = new JCheckBox();
    final private JCheckBox Computer_Player = new JCheckBox();
    private JLabel playerNum;
    private BufferedImage player;
    
    //constructor - Initialize the UI elements
    public PlayerCard(String title, String playerFile){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.LIGHT_GRAY);
        confirm.setText("Select Player");
        Computer_Player.setText("Computer Player");

        player = null;
        try{
            player = ImageIO.read(new File("assets/" + playerFile));
        }
        catch(Exception f){
            System.out.println("Error: File not Found");
        }
        JLabel playerLabel = new JLabel(new ImageIcon(player));

        // when checkbox is selected.
        confirm.addActionListener(i->{
            this.setBackground(Color.LIGHT_GRAY);
            if(confirm.isSelected())
{            this.setBackground(Color.GRAY);
}
        });
        
        

        playerNum = new JLabel(title);
        this.add(playerNum);
        this.add(diff);
        this.add(confirm);
        this.add(Computer_Player);
        this.add(playerLabel);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

 
}
