import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;



/* StopTile class
 * represents a single tile on the can't stop board
 * maintains a list of pieces active on that tile
 */
public class StopTile extends JPanel implements MouseListener{

    /* Constructor
     * 
     */
    public StopTile(){

        this.setPreferredSize(new Dimension(40,40));
        Border border = BorderFactory.createLineBorder(Color.black);
        Border padding = BorderFactory.createEmptyBorder(2,2,2,2);
        this.setBorder(BorderFactory.createCompoundBorder(padding, border));
        this.setBackground(Color.white);

    }

    // add a runner to the tile
    public void occupy(){
        // implement
    }
    // on turn end: convert runners into player's coloured pieces
    public void claim(){
        //implement
    }
    

    // let a tile be interacted with
    public void activateTile(){
        addMouseListener(this);     
    }


    public void mouseEntered(MouseEvent e){
        Object tile = e.getSource();
        if(tile instanceof StopTile){
            
            StopTile hovertile = (StopTile)e.getSource();
            hovertile.setBackground(Color.red);
        }
    }
	public void mouseExited(MouseEvent e) {
        Object tile = e.getSource();
        if(tile instanceof StopTile){
            
            StopTile hovertile = (StopTile)e.getSource();
            hovertile.setBackground(Color.white);
        }
    }
    public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
}
