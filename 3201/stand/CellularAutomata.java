import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.io.*;
import java.util.*;


// Main Frame, including CA updating scheme and event detection mechanisms

public class CellularAutomata extends Frame implements ActionListener
{
	// define attributes
    public int width = 200;
    public int height = 200;
    public int [][] cell;
    public int [][] scan;
    public StateChangeStack stateChanges;
    public CellUpdateStack cellUpdates;
	
	// records of different species in the CA
    public SpeciesDatabase population;
    public BirthEventStack birthEvents;
    public DeathEventStack deathEvents;

    public static final int QUIESCENT = 0;
    public static final int NOT_OCCUPIED = -1;
    
    // the colours that represent each cell state (starting with black for 0)
    public static Color [] cellColor = {Color.black, Color.blue, Color.red,
				       					Color.magenta, Color.green, Color.cyan,
				       					Color.yellow, Color.white, Color.gray};
	
	// the colours that represent each species (colour of inner sheath)
    public static Color [] scanColor =  {
										new Color( 255, 255, 128),
										new Color( 255, 128, 255),
										new Color( 255, 128, 128),
										new Color( 128, 255, 255),
										new Color( 128, 255, 128),
										new Color( 128, 128, 255),
										new Color( 255, 255, 192),
										new Color( 255, 192, 255),
										new Color( 255, 192, 192),
										new Color( 255, 192, 128),
										new Color( 255, 128, 192),
										new Color( 192, 255, 255),
										new Color( 192, 255, 192),
										new Color( 192, 255, 128),
										new Color( 192, 192, 255),
										new Color( 192, 192, 192),
										new Color( 192, 192, 128),
										new Color( 192, 128, 255),
										new Color( 192, 128, 192),
										new Color( 192, 128, 128),
										new Color( 128, 255, 192),
										new Color( 128, 192, 255),
										new Color( 128, 192, 192),
										new Color( 128, 192, 128),
										new Color( 128, 128, 192)
	    								};

    public Panel simulator;
    public CellularAutomataCanvas cnv;
    public Panel console;
    public Panel variousSettings;
    public Label timeDisplay;
    public TextField intervalField;
    public TextField spaceSizeField;
    public TextField ancestorField;
    public Panel buttons;
    public Button startStop, step, reset;
    public Panel analysisResultPanel;
    public TextArea analysisResult;

    public boolean pause, oneStep, resetRequest;
    public long time;
    public int interval;
    
    private String ancestorFileName;
	
	/* constructor amended to take ancestor filename as a parameter as opposed
	 * to original hard coding
	 */
    public CellularAutomata( int cx, int cy, String ancestorFileIn)
    {
    	//title the frame
    	super( "Evoloops");
    	
        // JUST ADDED 2020
        pause = false;
        oneStep = false;
        resetRequest = false;
        
    	// determine the ancestor to use
    	ancestorFileName = ancestorFileIn;
		
		// ensure program terminates when window close clidked on
		addWindowListener( new WindowAdapter()
							{
								public void windowClosing( WindowEvent e)
								{
									System.exit( 0);
								}
							});
		
		// gui housekeeping
		setLayout( new BorderLayout( 10, 10));

		simulator = new Panel();
		simulator.setLayout( new BorderLayout( 10, 10));
		simulator.add( cnv = new CellularAutomataCanvas( this, cx, cy),
														BorderLayout.CENTER);

		console = new Panel();
		console.setLayout( new GridLayout( 2, 1));

		buttons = new Panel();
		buttons.setLayout(new GridLayout( 1, 5));
		buttons.add( new Label( "Time: ", Label.RIGHT));
		buttons.add( timeDisplay = new Label( "0", Label.LEFT));
		buttons.add( startStop = new Button( "Start"));
		startStop.addActionListener( this);
		buttons.add( step = new Button( "Step"));
		step.addActionListener( this);
		buttons.add( reset = new Button( "Reset"));
		reset.addActionListener( this);
		console.add(buttons);

		variousSettings = new Panel();
		variousSettings.setLayout( new GridLayout( 1, 6));
		variousSettings.add( new Label( "Interval: ", Label.RIGHT));
		variousSettings.add( intervalField = new TextField( "1"));
		intervalField.addActionListener( this);
		variousSettings.add( new Label( "Space Size: ", Label.RIGHT));
		variousSettings.add( spaceSizeField = new TextField( "200"));
		spaceSizeField.addActionListener( this);
		variousSettings.add( new Label( "Ancestor: ", Label.RIGHT));
		variousSettings.add( ancestorField = new TextField( ancestorFileName));
		ancestorField.addActionListener( this);
		console.add( variousSettings);
		
		simulator.add( console, BorderLayout.SOUTH);
		add( simulator, BorderLayout.WEST);
	
		analysisResultPanel = new Panel();
		analysisResultPanel.setLayout( new GridLayout( 1, 1));
		
		analysisResultPanel.add( analysisResult = new TextArea( "", 10, 10));
		analysisResult.setEditable( false);
		add( analysisResultPanel, BorderLayout.CENTER);
	
		stateChanges = new StateChangeStack();
	
		population = new SpeciesDatabase();
		birthEvents = new BirthEventStack();
		deathEvents = new DeathEventStack();
		outputPopulation();
	
		setSize( cx + 300, cy + 100);
	
		show();

		if ( cnv.buffer == null )
		{
			cnv.createBuffer();
		}
    }
	
	/**
	 * resets the cell array, canvas buffer and cell update stack, then
	 * clears the display
	 *
	 * @param int
	 * @param int
	 * @returns none
	 *
	 * @modifies: this
	 * @effects: creates a new cell array to the size of the dimensions passed
	 * and resets the simulation accordingly
	 */
    public void createNewCells(int x, int y)
    {
    	width = x;
		height = y;
		cnv.createBuffer();
		cell = new int [ width][ height];
		scan = new int [ width][ height];
		cellUpdates = new CellUpdateStack( width, height);
		clearCellularAutomata();
    }
	
	/**
	 * action listener contract method for gui buttons
	 */
    public void actionPerformed(ActionEvent e)
    {
    	if ( e.getSource() == startStop )
    	{
    		// if currently paused...
    		if ( pause )
    		{
    			// ... 'un-pause'
    			startStop.setLabel( "Stop");
    			pause = false;
    			oneStep = false;
    		}
    		else
    		{
    			// ...otherwise pause simulation
    			startStop.setLabel( "Start");
    			//oneStep = true; DEBUG REMOVED 2020
                pause = true; // DEBUG ADDED 2020
				// Consider this the same as "Step" button pressed
				// in order to execute reflectCellsToBuffer()
	    	}
		}
		
		else if ( e.getSource() == step )
		{
			oneStep = true;
            System.out.println("Step pressed, onestep true");
		}
		
		else if ( e.getSource() == intervalField )
		{
			setInterval();
		}
		
		else if ( (e.getSource() == reset) ||
		 			(e.getSource() == spaceSizeField) ||
		 				(e.getSource() == ancestorField) )
		{
			resetRequest = true;
		}
    }
	
	/**
	 * determine interval between display updates (really a worker method)
	 *
	 * @param none
	 * @returns none
	 *
	 * @modifies: this
	 * @effects:  following an update of the interval field in the gui this
	 * method determines the value input into the textfield and alters the
	 * display interval to suit
	 */
    public void setInterval()
    {
    	int tempInterval;
		
		try
		{
			tempInterval = Integer.parseInt(intervalField.getText());
		}
		catch ( Exception ex)
		{
			System.out.println( "Illegal interval specified.");
	    	intervalField.setText( "10");
	    	tempInterval = 10;
		}
		
		if ( tempInterval < 1 )
		{
	    	System.out.println( "Non-positive interval specified.");
	    	intervalField.setText( "1");
	    	tempInterval = 1;
		}

		interval = tempInterval;
    }
	
	/**
	 * worker method to reset simulation following selection in gui
	 */
    public void resetSimulation()
    {
    	int spaceSize;
		String imageFile;
		File tempFile;
		
		time = 0;
		timeDisplay.setText( "" + time);
		
		setInterval();
		
		startStop.setLabel( "Start");
		pause = true;
		oneStep = false;
		resetRequest = false;
		
		try
		{
			spaceSize = Integer.parseInt( spaceSizeField.getText());
		}
		catch ( Exception ex)
		{
			System.out.println( "Illegal space size specified.");
	    	spaceSizeField.setText("200");
	    	spaceSize = 200;
		}
		
		if ( spaceSize < 20 )
		{
			System.out.println( "Too small space size specified.");
	    	spaceSizeField.setText( "20");
	    	spaceSize = 20;
		}
		else if ( spaceSize > 2000 )
		{
	    	System.out.println( "Too large space size specified.");
	    	spaceSizeField.setText( "2000");
	    	spaceSize = 2000;
		}
		width = height = spaceSize;
		createNewCells( width, height);
		stateChanges.clear();
		population.clear();
		birthEvents.clear();
		deathEvents.clear();
		
		imageFile = ancestorField.getText();
		tempFile = new File( imageFile);
		
		if ( !tempFile.exists() )
		{
		    System.out.println( "File \"" + imageFile + "\" does not exist.");
	    	ancestorField.setText( ancestorFileName);
	    	imageFile = ancestorField.getText();
		}
		
		// place ancestor in the middle of the CA
		putAncestor( imageFile, width / 2, height / 2);
		reflectCellsToBuffer();
		outputPopulation();
    }
	
	/**
	 * place the ancestor in the grid
	 */
    public void putAncestor( String imageFile, int x, int y)
    {
    	String buf;
		int wd, ht, len, xx, yy, i;
		char data;
		StateChange sc;
	
		/* Measuring the size of image - width to max string length of file,
		 * height to number of strings in the ancestor file*/
	
		wd = ht = 0;
		try
		{
		    BufferedReader br = new BufferedReader(new FileReader(imageFile));
		    
		    while ( (buf = br.readLine()) != null )
		    {
				ht ++;
				len = buf.length();
				if ( len > wd )
				{
					wd = len;
				}
			}
			
	    	br.close();
		}
		catch ( Exception ex)
		{
		    System.out.println( "Error: " + ex);
		    System.exit( 1);
		}

		// Loading image
	
		x = x - wd / 2;
		y = y - ht / 2;
		
		// if too big to fit in the ca...
		if ( (x < 0) || (x + wd > width) || (y < 0) || (y + ht > height) )
		{
			System.out.println( "Image file \"" + imageFile +
												"\" sticks out of the space.");
	    	System.exit( 1);
		}
		
		xx = x;
		yy = y;
		
		try
		{
			BufferedReader br = new BufferedReader( new FileReader( imageFile));
	    
	    	while ( (buf = br.readLine()) != null )
	    	{
	    		for ( i = 0, xx = x; i < buf.length(); i ++, xx ++ )
	    		{
		    		data = buf.charAt( i);
		    		
		    		// only read valid characters from the ancestor file
		    		if ( (data >= '1') && (data <= '9') )
		    		{
		    			// push each character + coords onto the StateChangeStack
		    			stateChanges.push( xx, yy, (int) buf.charAt( i) - (int) '0');
		    		}
		    		else if ( (data != ' ') && (data != '0') )
		    		{
		    			System.out.println( "Illegal state appeared in \"" +
		    												imageFile + "\".");
						System.exit( 1);
			    	}
				}
			
				yy ++;
	    	}
	    	
	    	br.close();
		}
		catch ( Exception ex)
		{
	    	System.out.println( "Error: " + ex);
	    	System.exit( 1);
		}
		
		// while the StateChangeStack has contents...
		while ( !stateChanges.empty() )
		{
			// ...get top StateChange from the statechange stack...
	    	sc = stateChanges.pop();
			
			// ...retrieve coordinates from StateChange...
	    	x = sc.x;
	    	y = sc.y;
	    	
	    	// ...and set corresponding cell's state
	    	cell [x][y] = sc.nextState;
			
			// ternary operations to add each cell + neighbourhood to CellUpdateStack
			
			// push current coordinate to stack
		    cellUpdates.push( x, y);
		    // push west neighbour to stack (wrap around at edges for all these)
		    cellUpdates.push( x > 0 ? x - 1 : width - 1, y);
		    // push east neighbour to stack
		    cellUpdates.push( x < width - 1 ? x + 1 : 0, y);
		    // push north neighbour to stack
		    cellUpdates.push( x, y > 0 ? y - 1 : height - 1);
		    // push south neighbour to stack
		    cellUpdates.push( x, y < height - 1 ? y + 1 : 0);
		}
    }
	
	/**
	 * determine cell value at a given coordinate
	 *
	 * @param int
	 * @param int
	 * @returns int
	 *
	 * @effects: returns int value of cell in array at given (modular)
	 * coordinate
	 */
    public int getCellState( int x, int y)
    {
    	if (x < 0) 			{ x = width - 1; }
		if (x > width - 1) 	{ x = 0; }
		if (y < 0) 			{ y = height - 1; }
		if (y > height - 1)	{ y = 0; }
		
		return cell [ x][ y];
    }
	
	/**
	 * determine scan value at a given coordinate
	 * (scan is a working snapshot of the cell array at the current point
	 * in time to allow current neighbourhood to be determined whilst updating
	 * cells under the t-rule)
	 *
	 * @param int
	 * @param int
	 * @returns int
	 *
	 * @effects: returns int value of scan in array at given (modular)
	 * coordinate
	 */
    public int getScanState( int x, int y)
    {
    	if (x < 0) 			{ x = width - 1; }
		if (x > width - 1) 	{ x = 0; }
		if (y < 0) 			{ y = height - 1; }
		if (y > height - 1)	{ y = 0; }
		
		return scan [ x][ y];
    }
	
	/**
	 * 'clears' all cells in the ca
	 *
	 * @param none
	 * @returns none
	 *
	 * @modifies: this
	 * @effects:  clears all the cells in the cell and scan arrays, setting
	 * values to 0 and -1 respectively
	 */
    public void clearCellularAutomata()
    {
    	int x, y;
    	
		for ( x = 0; x < width; x ++ )
		{
			for (y = 0; y < height; y ++)
			{
				cell [ x][ y] = QUIESCENT;
				scan [ x][ y] = NOT_OCCUPIED;
	    	}
		}
	    
	    // clear stacks
		stateChanges.clear();
		cellUpdates.clear();
    }
	
	/**
	 * redraws the lattice to show current state
	 *
	 * @param none
	 * @returns none
	 *
	 * @modifies: this
	 * @effects:  redraws canvas and cells within cancas to show current state
	 * of the CA lattice
	 */
    public void reflectCellsToBuffer()
    {
    	int x, y;
    	
    	// set background of CA canvas
		cnv.buffer_g.setColor( cellColor[QUIESCENT]);
		cnv.buffer_g.fillRect( 0, 0, width, height);
		
		// for each cell in the CA
		for ( x = 0; x < width; x ++ )
		{
			for ( y = 0; y < height; y ++ )
			{
				// if scan array contains contents (i.e. cell part of a living structure)
				if ( scan [ x][ y] != NOT_OCCUPIED )
				{
					// plot the cell using one the apt scan colour
					cnv.buffer_g.setColor( scanColor[ scan [ x][ y] % scanColor.length]);
		    		cnv.buffer_g.fillRect( x, y, 1, 1);
				}
				// otherwise if cell is occupied 
				else if ( cell [ x][ y] != QUIESCENT )
				{
					// plot the cell using a standard cell colour
		    		cnv.buffer_g.setColor( cellColor[ cell [ x][ y]]);
		    		cnv.buffer_g.fillRect( x, y, 1, 1);
				}
	    	}
		}
	    
	    // finally show output in gui
		cnv.repaint();
    }
	
	/**
	 * display text information about current species in the CA
	 * 
	 * @param none
	 * @returns none
	 *
	 * @modifies: this
	 * @effects:  displays the current species state of the CA
	 */
    public void outputPopulation()
    {
    	SpeciesPop sppop;
    	
    	// set up headers for list
		analysisResult.setText( "ID : size : sequence : count\n");

		// for each species
		for ( int i = 0; i < population.length(); i ++ )
		{
			sppop = population.speciesPopOf( i);
			
			// if the species currently exists in the CA, show details
			if ( sppop.pop > 0 )
			{
				analysisResult.append( "" +
				      					i + " : " +
				      					sppop.sp.pheno.width + "x" +
				      					sppop.sp.pheno.height + " : " +
				      					sppop.sp.geno.sequence + " : " +
				      					sppop.pop + "\n");
	    	}
		}
    }
	
	/**
	 * prime method of program - updates the CA according to the t-rule
	 * intelligently updates only where needed
	 *
	 * @param int [][][][][]
	 * @returns none
	 *
	 * @modifies: this
	 * @effects:  updates those cells of the CA that are 'live' or neighbour of
	 * a live cell
	 */
    public void updateCellularAutomata( int [][][][][] rule)
    {
    	int x, y, c, t, r, b, l, ns, len;
		CellUpdate cu;
		StateChange sc;
		BirthEvent be;
		DeathEvent de;
		int deadID;
		
		// while the CellUpdateStack has contents (i.e. 'live' species in the CA)
		while ( !cellUpdates.empty() )
		{
			// retrieve the CellUpdate from the top of the stack
			cu = cellUpdates.pop();
			
			// determine the coordinates of the cell...
		    x = cu.x;
		    y = cu.y;
			
			/* ...hence get states of the cell and its neighbours
			 * (t = top (north), b = bottom (south), l = left (west) r = right (east))
			 */
		    c = cell [ x][ y];
		    t = getCellState( x, y - 1);
		    r = getCellState( x + 1, y);
		    b = getCellState( x, y + 1);
		    l = getCellState( x - 1, y);
		    
		    // retrieve the corresponding new state from the t-rule
		    ns = rule [ c][ t][ r][ b][ l];
		    
		    // if the cell's state needs to change
		    if ( c != ns )
		    {
		    	// push the cell's coords and new state onto the statechanges stack
		    	stateChanges.push( x, y, ns);
			}
			
			/* if state changing from 0 to 6 (i.e. a birth since umbilical cord
			 * is beginning to dissolve), then push the coordinate of the
			 * neighbouring state 5 onto the birth events stack, along with
			 * a couple of integers to show in which direction to start analysing
			 * the newly born structure */
		    if ( (c == 0) && (ns == 6) )
		    {
				if ((t==1) && (r==2) && (b==5) && (l==2))
				{
					birthEvents.push( x, y < height-1 ? y+1 : 0 , 0, 1);
				}
				else if ( (t==2) && (r==5) && (b==2) && (l==1) )
				{
					birthEvents.push( x < width-1 ? x+1 : 0, y, 1, 0);
				}
			    else if ( (t==5) && (r==2) && (b==1) && (l==2) )
			    {
			    	birthEvents.push( x, y > 0 ? y-1 : height-1, 0, -1);
			    }
			    else if ( (t==2) && (r==1) && (b==2) && (l==5) )
			    {
			    	birthEvents.push( x > 0 ? x-1 : width-1, y, -1, 0);
			    }
		    }

	    	if ( (c == 2) && (ns == 0) && (scan [ x][ y] != NOT_OCCUPIED) )
	    	{
	    		deathEvents.push( x, y);
	    	}
		}

		while ( !stateChanges.empty() )
		{
		    sc = stateChanges.pop();

		    x = sc.x;
		    y = sc.y;
		    cell[ x][ y] = sc.nextState;
			
		    cellUpdates.push( x, y);
	    	cellUpdates.push( x > 0 ? x - 1 : width - 1, y);
	    	cellUpdates.push( x < width - 1 ? x + 1 : 0, y);
	    	cellUpdates.push( x, y > 0 ? y - 1 : height - 1);
	    	cellUpdates.push( x, y < height - 1 ? y + 1 : 0);
		}
			
		
		while ( !birthEvents.empty() )
		{
			be = birthEvents.pop();
			
	    	int xx, yy, dx, dy, tempdx, w, h, wcheck, hcheck;
	    	String seq = "";
			
	    	xx = be.x;
	    	yy = be.y;
	    	dx = be.dx;
	    	dy = be.dy;
			
	    	w = 0;
	    	while ( true )
	    	{
	    		seq += ( "" + cell [ xx][ yy]);
				w ++;
				xx += dx; if ( xx < 0 ) xx = width - 1; if ( xx > width - 1 ) xx = 0;
				yy += dy; if ( yy < 0 ) yy = height - 1; if ( yy > height - 1 ) yy = 0;
				
				if ( getCellState( xx + dy, yy - dx) != 2 ) break;
	    	}
	    	w --;
			
	    	tempdx = dx;
	    	dx = dy;
	    	dy = -tempdx;
			
	    	h = 0;
	    	while ( true )
	    	{
	    		seq += ( "" + cell [ xx][ yy]);
				h ++;
				xx += dx; if ( xx < 0 ) xx = width - 1; if ( xx > width - 1 ) xx = 0;
				yy += dy; if ( yy < 0 ) yy = height - 1; if ( yy > height - 1 ) yy = 0;
				
				if ( getCellState( xx + dy, yy - dx) != 2 ) break;
	    	}
	    	h --;

	    	tempdx = dx;
	    	dx = dy;
	    	dy = -tempdx;
			
	    	wcheck = 0;
	    	while ( true )
	    	{
	    		seq += ( "" + cell [ xx][ yy]);
				wcheck ++;
				xx += dx; if ( xx < 0 ) xx = width - 1; if ( xx > width - 1 ) xx = 0;
				yy += dy; if ( yy < 0 ) yy = height - 1; if ( yy > height - 1 ) yy = 0;
				
				if ( getCellState( xx + dy, yy - dx) != 2 ) break;
	    	}
	    	wcheck --;
			
	    	tempdx = dx;
	    	dx = dy;
	    	dy = -tempdx;
			
	    	hcheck = 0;
	    	while ( true )
	    	{
	    		seq += ( "" + cell [ xx][ yy]);
				hcheck ++;
				xx += dx; if ( xx < 0 ) xx = width - 1; if ( xx > width - 1 ) xx = 0;
				yy += dy; if ( yy < 0 ) yy = height - 1; if ( yy > height - 1 ) yy = 0;
				
				if ( getCellState( xx + dy, yy - dx) != 2 ) break;
	    	}
	    	hcheck --;
			
	    	Species sp = new Species( w, h, seq);
	    	if ( (w == wcheck) && (h == hcheck) && (sp.geno.sequence != null) )
	    	{
				population.birth( sp);
				int bornID = population.indexOf( sp);
				
				xx -= dx; if ( xx < 0 ) xx = width - 1; if ( xx > width - 1 ) xx = 0;
				yy -= dy; if ( yy < 0 ) yy = height - 1; if ( yy > height - 1 ) yy = 0;
				
				tempdx = dx;
				dx = dy;
				dy = -tempdx;
				
				xx += dx; if ( xx < 0 ) xx = width - 1; if ( xx > width - 1 ) xx = 0;
				yy += dy; if ( yy < 0 ) yy = height - 1; if ( yy > height - 1 ) yy = 0;
					
				int corner = 0;
		
				while( corner < 4 ) 
				{
		    		scan [ xx][ yy] = bornID;
		    		if ( getCellState( xx + dx, yy + dy) != 2 )
		    		{
						tempdx = dx;
						dx = dy;
						dy = -tempdx;
						corner ++;
		    		}
		    		else
		    		{
						xx += dx; if ( xx < 0 ) xx = width - 1; if ( xx > width - 1 ) xx = 0;
						yy += dy; if ( yy < 0 ) yy = height - 1; if ( yy > height - 1 ) yy = 0;
		    		}
				}
	    	}
		}

		while ( !deathEvents.empty() )
		{
		    de = deathEvents.pop();
		    
		    if ( (deadID = scan [ de.x][ de.y] ) != NOT_OCCUPIED )
		    {
				population.death( deadID);
				Stack st = new Stack();
				st.push( de);
				
				while( !st.empty() )
				{
		    		de = ( DeathEvent) st.pop();
		    		scan [ de.x][ de.y] = NOT_OCCUPIED;
		    		
		    		if ( getScanState( de.x, de.y - 1) != NOT_OCCUPIED )
		    		{
		    			st.push( new DeathEvent( de.x, de.y > 0 ? de.y-1 : height-1));
		    		}
		    		
		    		if ( getScanState( de.x + 1, de.y) != NOT_OCCUPIED )
		    		{
						st.push( new DeathEvent( de.x < width-1 ? de.x+1 : 0, de.y));
		    		}
		    		
		    		if ( getScanState( de.x, de.y + 1) != NOT_OCCUPIED )
		    		{
						st.push( new DeathEvent( de.x, de.y < height-1 ? de.y+1 : 0));
		    		}
		    		if ( getScanState( de.x - 1, de.y) != NOT_OCCUPIED )
					{
						st.push( new DeathEvent( de.x > 0 ? de.x-1 : width-1, de.y));
					}
				}
	    	}
		}
    }
	
	
	/**
	 * method that forms the main program loop
	 * the CA iterates forever, or until exiting the gui causes program termination
	 *
	 * @param int array - the t-rule
	 * @returns none
	 *
	 * @modifies: this
	 * @requires: a fully defnied t-rule
	 * @effects:  causes that CA simulation to permanently loop, updating the CA
	 * using the t-rule each time; allows the simulation to be halted if stop or
	 * step is clicked on
	 */
    public void simulation( int [][][][][] rule)
    {
    	// forever loop
    	while ( true )
    	{
            // DEBUG 2020 - don't ask why this is needed!
            if (pause)
            {
                System.out.print("");
            }
            // END OF DEBUG 2020
            
    		// if reset is clicked on...
    		if ( resetRequest )
    		{
    			// ...then reset the CA to its initial state
    			resetSimulation();
    		}
	    	
	    	// if not-paused or step is clicked on
	    	if ( (!pause) || oneStep )
	    	{
	    		// update the CA according to the t-rule
	    		updateCellularAutomata( rule);
	    		
	    		// increment time and display new value
				time ++;
				timeDisplay.setText( "" + time);
				
				// if step clicked on or the display time interval has been reached
				if ( oneStep || (time % interval == 0) )
				{
					// make sure that the screen is updated (need to fully check this)
		    		reflectCellsToBuffer();
		    		outputPopulation();
				}
				
				// if step clicked on
				if ( oneStep )
				{
					// ensure that start/stop button shows start
		    		startStop.setLabel( "Start");
		    		
		    		// ensure that the simulation is paused
		    		pause = true;
		    		
		    		// reset step clicked on status back to zero
		    		oneStep = false;
				}
	    	}
		}
    }
}
