//imports
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class StopColumn extends JPanel{

    //private members
    //private Boolean columnWon;
    private ArrayList<StopTile> column;
    
    /* Constructor
     * Initialize an array of Tile Objects
     * Size is Variable
     */
    public StopColumn(int columnSize){

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
        this.setBackground(Color.yellow);
        for(StopTile tile : column) {
            tile.activateTile();
        }
    }

}
