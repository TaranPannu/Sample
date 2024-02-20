//
//   "Evoloops.java"
//
//   Evoloop simulator with event-driven birth/death detection mechanisms
//   Java application version 0.2
//   (CA updating algorithm much improved 09/22/2004)
//
//   Note:
//     The detection algorithms used in this application were originally
//     developed by Chris Salzberg and Antony Antony at the Section
//     Computational Science, University of Amsterdam, the Netherlands.
//
//     This application does not include features of genealogy tracing
//     that were used to produce the results presented in our publications.
//
//     Evoloop Website:       http://complex.hc.uec.ac.jp/sayama/sdsr/
//     Artis Project Website: http://artis.phenome.org/
//
//
//   Any questions or comments should be addressed to:
//     Dr. Hiroki Sayama
//     Associate Professor
//     Department of Human Communication
//     University of Electro-Communications
//     1-5-1 Chofugaoka
//     Chofu Tokyo 182-8585 JAPAN
//     sayama@cx.hc.uec.ac.jp
//
//   Copyright (c) 2004 by Hiroki Sayama
//   All rights reserved
//


import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.io.*;
import java.util.*;

// Main application

public class Evoloops
{
	// size of background canvas
    private static int canvasWidth = 600;
    private static int canvasHeight = 600;
    
    // CA to use
    private static CellularAutomata ca;
    
    // transition rule - note 9 states on a Moore neighbourhood
    private static int [][][][][] rule = new int [9][9][9][9][9];
	
	/**
	 * starts up the system - CODE AMENDMENT/INSERTION
	 *
	 * @param String - filename for transition rule
	 * @param boolean - whether or not to introduce dissolver
	 *
	 * @modifies:  this
	 * @effects:  sets up the t-rule and ca
	 */
	public static void startUp( String ruleFileIn, boolean introDissolverIn,
								String ancestorFileIn)
	{
		defineRules( ruleFileIn, introDissolverIn);

		ca = new CellularAutomata( canvasWidth, canvasHeight, ancestorFileIn);
		ca.resetSimulation();
		ca.simulation( rule);
	}
	
	/**
	 * set up the transition rule - by reading it in from file
	 *
	 * @param String - filename to use
	 * @param boolean - whether or not to introduce dissolver
	 *
	 * @modifies: this
	 * @effects:  sets up the transition rule
	 */
    public static void defineRules( String ruleFile, boolean introduceDissolver)
    {
    	// string buffer for fileread
    	String buffer;
    	
    	/**
    	 * integers to hold the values for the t-r:
    	 * a = current state of cell in focus
    	 * b, c, d, e = neighbours top, bottom, left, right (any rotation)
    	 * f = new state of cell in focus
    	 */
		int a, b, c, d, e, f;
		
		// initialise all values in the t-r to -1
		for ( a = 0; a <= 8; a ++ )
		{
			for ( b = 0; b <= 8; b ++ )
			{
				for ( c = 0; c <= 8; c ++ )
				{
					for ( d = 0; d <= 8; d ++ )
					{
						for ( e = 0; e <= 8; e ++ )
						{
							rule [a][b][c][d][e] = -1;
						}
					}
				}
			}
		}
		
		// read the actual rule from file
		try
		{
	    	BufferedReader br = new BufferedReader( new FileReader( ruleFile));
	    	
	    	while ( (buffer = br.readLine()) != null )
	    	{
	    		// typecast values from ascii to integer
				a = (int) buffer.charAt(0) - (int) '0';
				b = (int) buffer.charAt(1) - (int) '0';
				c = (int) buffer.charAt(2) - (int) '0';
				d = (int) buffer.charAt(3) - (int) '0';
				e = (int) buffer.charAt(4) - (int) '0';
				f = (int) buffer.charAt(5) - (int) '0';
				
				// define the rules for all four rotations
				rule [a][b][c][d][e] = f;
				rule [a][c][d][e][b] = f;
				rule [a][d][e][b][c] = f;
				rule [a][e][b][c][d] = f;
	    	}
	    	
	    	br.close();
		}
		catch ( Exception ex)
		{
			System.out.println("Error: " + ex);
	    	System.exit(1);
		}
		
		// end here if there's no dissolver...
		if ( !introduceDissolver )
		{
			return;
		}

		// ...otherwise define rules related to state '8'
		
		// check every rule code
		for ( a = 0; a <= 8; a ++ )
		{
			for ( b = 0; b <= 8; b ++ )
			{
				for ( c = 0; c <= 8; c ++ )
				{
					for ( d = 0; d <= 8; d ++ )
					{
						for ( e = 0; e <= 8; e ++ )
						{
							//if the rule code is undefined (i.e. value -1)
							if ( rule [a][b][c][d][e] == -1 )
			    			{
			    				// if 8 is current state of cell in focus
								if ( a == 8 )
								{
									// ensure new state will be 0
									rule [a][b][c][d][e] = 0;
								}
								// else if there's an 8 in the neighbourhood
								else if ( (b == 8) || (c == 8) || (d == 8) ||
										(e == 8) )
								{
									// determine new state depending on a
								    switch( a)
								    {
								    	// for a = 0 and a = 1
									    case 0:
				    					case 1:	if ( ((b >= 2) && (b <= 7)) ||
				    							((c >= 2) && (c <= 7)) ||
							    				((d >= 2) && (d <= 7)) ||
							    				((e >= 2) && (e <= 7)) )
							    				{
							    					rule [a][b][c][d][e] = 8;
							    				}
							    				else
							    				{
							    					rule [a][b][c][d][e] = a;
							    				}
												break;
										
										// for a = 2, 3 or 5
									    case 2:
				    					case 3:
				    					case 5:	rule [a][b][c][d][e] = 0;
												break;
										
										// for a = 4, 6 or 7
				    					case 4:
				 						case 6:
				    					case 7:	rule [a][b][c][d][e] = 1;
												break;
								    }
								}
			    			}
						}
					}
				}
			}
		}
	
		// define all undefined rules
		
		// check each rule code
		for ( a = 0; a <= 8; a ++ )
		{
			for ( b = 0; b <= 8; b ++ )
			{
				for ( c = 0; c <= 8; c ++ )
				{
					for ( d = 0; d <= 8; d ++ )
					{
						for ( e = 0; e <= 8; e ++ )
						{
							// if the rule is undefined, set it accordingly
							if ( rule[a][b][c][d][e] == -1 )
							{
								if ( a == 0 )
								{
									rule [a][b][c][d][e] = 0;
								}
								else
								{
									rule [a][b][c][d][e] = 8;
								}
				    		}
						}
					}
				}
			}
		}	    
    }
}
