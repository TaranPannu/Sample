import java.awt.Color;
import javax.swing.*;

/*
 *  A GUI component
 *
 *  A simple extension of JPanel which records its
 *  coordinates in xcoord and ycoord, NOT in 'x' and 'y'.
 *  Why not? Because 'x' and 'y' are already attributes of
 *  the panel (super) class which say where to draw it in the window.
 *
 *  The game grid and allows the background colour to be set with ease.
 *
 *  @author mhatcher
 */
public class Grid extends JPanel
{
    private int xcoord, ycoord;  // location in the grid
	
	// constructor takes the x and y coordinates of this square
	public Grid(int xcoord, int ycoord)
	{
		super();
		this.setSize(-1,-1);
		this.xcoord = xcoord;
		this.ycoord = ycoord;
	}
	
	// if the decider is even, it chooses black, otherwise white (for 'column+row' will allow a chequerboard effect)
  
    
    // if the square is black it becomes white, and vice-versa
    public void colr(String st)
    {
      if (st.equals("0"))
        this.setBackground( Color.white);
        else
        this.setBackground( Color.BLACK);
    }
  public void setColor(){
    this.setBackground( Color.white);

  }
    // simple setters and getters
    public void setXcoord(int value)    { xcoord = value; }
    public void setYcoord(int value)    { ycoord = value; }
    public int getXcoord()              { return xcoord; }
    public int getYcoord()              { return ycoord; }
}
