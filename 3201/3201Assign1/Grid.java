import java.awt.Color;
import javax.swing.*;

//Creating particular grid 
public class Grid extends JPanel
{
    private int xcoord, ycoord;  // location in the grid
	
	// constructor takes the x and y coordinates of this square
	public Grid(int xcoord, int ycoord)
	{
		super();
		this.setSize(2,2);
		this.xcoord = xcoord;
		this.ycoord = ycoord;
	}
	
	
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
