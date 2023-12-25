import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.io.*;
import java.util.*;


// Canvas for graphical display of CA configuration

public class CellularAutomataCanvas extends Canvas
{
    public CellularAutomata caller;
    public Image buffer = null;
    public Graphics buffer_g = null;
    public int canvasWidth, canvasHeight;

    public CellularAutomataCanvas( CellularAutomata obj, int cx, int cy)
    {
		caller = obj;
		canvasWidth = cx;
		canvasHeight = cy;
		setSize( canvasWidth, canvasHeight);
		setBackground( Color.white);
    }
  
    public void createBuffer()
    {
		buffer = createImage( caller.width, caller.height);
		buffer_g = buffer.getGraphics();
		buffer_g.setColor( caller.cellColor [ caller.QUIESCENT]);
		buffer_g.fillRect( 0, 0, caller.width, caller.height);
    }

    public void paint( Graphics g)
    {
    	if (buffer == null)
    	{
    		createBuffer();
    	}
    	
		g.drawImage( buffer, 0, 0, canvasWidth, canvasHeight, this);
    }

    public void update( Graphics g)
    {
    	paint(g);
    }
}