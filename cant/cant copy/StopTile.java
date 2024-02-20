import java.util.ArrayList;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;


 

/* StopTile class
 * represents a single tile on the can't stop board
 * maintains a list of pieces active on that tile
 */
public class StopTile extends JPanel implements MouseListener{

    /* Constructor
     * 
     */
    private Boolean tileActive;
    private BufferedImage runner;
    private BufferedImage piece;
    private Boolean isOccupied;
    
    
    
    public StopTile(){

        isOccupied = false;
        this.setPreferredSize(new Dimension(40,40));
        Border border = BorderFactory.createLineBorder(Color.black);
        Border padding = BorderFactory.createEmptyBorder(2,2,2,2);
        this.setBorder(BorderFactory.createCompoundBorder(padding, border));
        this.setBackground(Color.white);
        try{
            piece = ImageIO.read(new File("assets/" + "redpiece.png"));
        }   
        catch(Exception f){
            System.out.println("Error: File not found");
        } 
        try{
            runner = ImageIO.read(new File("assets/" + "runner.png"));
        }   
        catch(Exception f){
            System.out.println("Error: File not found");
        } 
    }

    // add a runner to the tile
    public void occupy(){
        this.removeAll();
        this.add(new JLabel(new ImageIcon(runner)));
        this.isOccupied = true;
        this.repaint();
        this.revalidate();

    }
    // on turn end: convert runners into player's coloured pieces
    public void claim(){
        //implement
        if(isOccupied){
            this.removeAll();
            this.add(new JLabel(new ImageIcon(piece)));
            this.repaint();
            this.revalidate();
        }
    }
    

    // let a tile be interacted with
    public void activateTile(){     }

    public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
}
