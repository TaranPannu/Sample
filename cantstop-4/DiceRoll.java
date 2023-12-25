import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.image.*;
import java.io.File;

public class DiceRoll {
    
    //randomizer
    private Random diceroller;
    private int value;
    private BufferedImage die;


    public DiceRoll(){
        diceroller = new Random();
        value = diceroller.nextInt(6) + 1;
        try{
            die = ImageIO.read(new File("assets/die" + Integer.toString(this.value)+ ".png"));
        }
        catch(Exception f){
            System.out.println("Error: file not found");
    }
}

    public int value(){
        return this.value;
    }
    public JLabel generateDie(){
        
        JLabel dice = new JLabel(new ImageIcon(die));
        return dice;
    }

}
