//imports
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class StopColumn extends JPanel implements MouseListener{
 
    //private members
    private Boolean columnWon;
    private Boolean columnActive;
    public ArrayList<StopTile> column;
    private int playerColumnHeight;
    private int height, tileIdx;
    
    /* Constructor
     * Initialize an array of Tile Objects
     * Size is Variable
     */
    public StopColumn(int columnSize){
        this.height = columnSize;
        columnActive = false;
        playerColumnHeight = 0;
        // initialize an empty column
        column = new ArrayList<>();
        
        for(int i = 0; i < columnSize; i++){
            StopTile newTile = new StopTile();
            column.add(newTile);
            this.add(newTile);
            //newTile.addMouseListener(this); // either make stopcolumn implement listener or create a listener within class
            // consider putting this functionality onto stopboard
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        
        //columnWon = false;



    }
    // let a column be interacted with
    public void activateColumn(){
        this.setBackground(Color.BLUE);
        for(StopTile tile : column) {
            tile.activateTile();
        }
        columnActive = true;
    }
    public boolean isActive(){
        return columnActive;
    }

    public void deactivateColumn(){
        columnActive = false;
    }

    public void advanceRunner(){
        tileIdx = height - (1 + playerColumnHeight);
        column.get(tileIdx).removeAll();
        column.get(tileIdx).occupy();
        if(tileIdx + 1 < height){
            column.get(tileIdx + 1).removeAll();
            this.repaint();
            this.revalidate();
        }
        playerColumnHeight += 1;
        this.deactivateColumn();
        

        
    }
    public void mouseEntered(MouseEvent e){
            
        
    }
	public void mouseExited(MouseEvent e) {
        
    }
    
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {
    
        if(columnActive){
            System.out.println("click event fired");
            this.advanceRunner();
        }

    }

}
