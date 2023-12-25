import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class knight extends JFrame implements ActionListener, MouseListener
{
	private JPanel topPanel, bottomPanel;	// top and bottom panels in the main window
	private JLabel instructionLabel;		// a text label to tell the user what to do
	private JLabel infoLabel;            // a text label to show the coordinate of the selected square
    private JButton topButton;				// a 'reset' button to appear in the top panel
	private GridSquare [][] gridSquares;	// squares to appear in grid formation in the bottom panel
	private int rows,columns;				// the size of the grid
	boolean[][] vis;
	int lastx;
	int lasty;
	GridSquare square;
	GridSquare square1;
	int step=0;
	
	public knight(int rows, int columns)
	{
	vis=new boolean[rows][columns];
		this.rows = rows;
		this.columns = columns;
		this.setSize(400,400);
		
		// first create the panels
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setSize(500,500);
		GridLayout v=new GridLayout(rows, columns);
		v.setHgap(2);
		v.setVgap(2);
		bottomPanel.setLayout(v);

		//GridLa.setShowGrid(true);
		// then create the components for each panel and add them to it
		
		// for the top panel:
		instructionLabel = new JLabel("      ");
        infoLabel = new JLabel("Sir Lancelot, visit every square once!");
		topButton = new JButton("New Game");
		topButton.addActionListener(this);			// IMPORTANT! Without this, clicking the square does nothing.
		
		//topPanel.add(instructionLabel);
        topPanel.add(infoLabel);
		topPanel.add(instructionLabel);
		topPanel.add (topButton);
		
		// for the bottom panel:	
		// create the squares and add them to the grid
		gridSquares = new GridSquare[rows][columns];
		for ( int x = 0; x < columns; x ++)
		{
			for ( int y = 0; y < rows; y ++)
			{
				gridSquares[x][y] = new GridSquare(x, y);
				gridSquares[x][y].setSize(20, 20);
				gridSquares[x][y].setColor(x + y);
				
				gridSquares[x][y].addMouseListener(this);		
				
				bottomPanel.add(gridSquares[x][y]);
			}
		}
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.CENTER);		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent aevt)
	{
		Object selected = aevt.getSource();
				
		if ( selected.equals(topButton) )
		{
			step=0;
			square1=null;
			infoLabel.setText("Sir Lancelot, visit every square once!");
			for ( int x = 0; x < columns; x ++)
			{
				for ( int y = 0; y < rows; y ++)
				{
					vis[x][y]=false;
					
					gridSquares [x][y].setColor(x + y);
				}
			}
		}
	}


	// Mouse Listener events
	public void mouseClicked(MouseEvent mevt)
	{
		Object selected = mevt.getSource();
		
		
		if (selected instanceof GridSquare)
		{
			int x1=0;
			int y1=0;
		if(square1!=null)
		{
		x1=square1.getXcoord();
		y1=square1.getYcoord();
		}


             square = (GridSquare) selected;
            int x = square.getXcoord();
            int y = square.getYcoord();
			if(!vis[x][y])
			{
			x1=Math.abs(x1-x);
            y1=Math.abs(y-y1);
			if((x1==1 && y1==2)||(x1==2 && y1==1)|| step==0){
				if(vis[x][y]==false){
					vis[x][y]=true;
				}
				infoLabel.setText("Moves made: "+(step+1));
				square.switchColor();
				
	lastx=x;
	lasty=y;
	step++;
	if(square1!=null)
	square1.setBackground(Color.BLUE);
	 square1=square;
	 if(step==25){
		infoLabel.setText("You did it!");

	 }
			}
			else{
				infoLabel.setText("You can't go there!");

			}
	}else{
		infoLabel.setText("You can't go there!");
	}}	
	}
	
	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
