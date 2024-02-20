//imports
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class StopColumn extends JPanel implements MouseListener{
 
    //private members
    private Boolean columnWon;
    private Boolean hasRunner;
    private Boolean columnActive;
    public ArrayList<StopTile> column;
    private int playerColumnHeight;
    private int height, runnerIdx;
    int counter;
    
    /* Constructor
     * Initialize an array of Tile Objects
     * Size is Variable
     */
    public StopColumn(int columnSize){
        this.height = columnSize;
        runnerIdx = height;
        columnActive = false;
        hasRunner = false;
        columnWon = false;
        playerColumnHeight = 0;
        // initialize an empty column
        column = new ArrayList<>();
        
        for(int i = 0; i < columnSize; i++){
            StopTile newTile = new StopTile();
            column.add(newTile);
            this.add(newTile);
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        



    }
    // let a column be interacted with
    public void activateColumn(){
        counter = 1;
        if(this.isActive()){
            counter += 1;
        }
        for(StopTile tile : column) {
            tile.activateTile();
        }
        columnActive = true;    
    }
    
    public boolean isActive(){
        return columnActive;
    }
    public void resetColumn(){
        runnerIdx = height;
    }
    public void setRunner(){
        hasRunner = true;
    }
    public void unSetRunner(){
        hasRunner = false;
    }

    public void deactivateColumn(){
        counter -= 1;
        if(counter == 0){
            columnActive = false;
            for(StopTile tile : column)
                tile.deactivateTile();
            hasRunner = false;
        }
        if (counter < 0)
            counter = 0;
        
    }


    public Boolean columnHasRunner(){
        return hasRunner;
    }

    public void advanceRunner(){
        hasRunner = true;
        if(runnerIdx < height){
            column.get(runnerIdx).removeAll();
            column.get(runnerIdx).unOccupy();
        }
        runnerIdx -= 1;
        if(runnerIdx == 0){
            columnWon = true;
            for(StopTile tile : column)
                tile.setBackground(Color.yellow);
        }
        column.get(runnerIdx).occupy();
        
    }

    public void placePieces(){
        for(StopTile tile : this.column){
            tile.claim();
        }
    }
    public void mouseEntered(MouseEvent e){
            
        
    }
	public void mouseExited(MouseEvent e) {
        
    }
    
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
}
