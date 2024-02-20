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
    private int height, runnerIdx, pieceIdx;
    int counter;
    
    /* Constructor
     * Initialize an array of Tile Objects
     * Size is Variable
     */
    public StopColumn(int columnSize){
        this.height = columnSize;
        runnerIdx = height;
        pieceIdx = height;
        columnActive = false;
        hasRunner = false;
        columnWon = false;
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
    public int getPieceIdx(){
        return pieceIdx;
    }
    public int getRunnerIdx(){
        return runnerIdx;
    }
    public boolean isActive(){
        return columnActive;
    }
    public void resetColumn(){
        runnerIdx = height;
    }
    public void setRunnerIdx(int idx){
        this.runnerIdx = idx;
    }
    public void setPieceIdx(int idx){
        this.pieceIdx = idx;
    }
    public void setRunner(){
        hasRunner = true;
    }
    public void clean(){
        for(StopTile tile : column){
            tile.clear();
        }
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
    public boolean columnWon(){
        return columnWon;    
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

    public void lockColumnOnWin(){
        if(columnWon){
            for(StopTile t : column){
                t.clear();
                t.setBackground(Color.yellow);
            }
        }
    }

    public void placePieces(String color){
        pieceIdx = runnerIdx;
        for(StopTile tile : this.column){
            tile.claim(color);
        }
    }

    public void setPieces(String color){
        for (StopTile tile : this.column){
            if(column.indexOf(tile) == pieceIdx){
                tile.fill(color);
            }
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
