import java.io.*;
import java.util.*;

/**
 * @overview:  maintains a list of species in the CA with their population sizes
 *
 * allows species to be added to the list
 *
 * allows the whole list to be cleared
 *
 * allows births and deaths to be recorded against species ( population
 * increments and decrements)
 */
public class SpeciesDatabase
{
    private ArrayList database;
	
	/**
	 * constructor for the class
	 *
	 * creates an arraylist and ensures it is empty
	 *
	 * @param none
	 *
	 * @modifies: this
	 * @effects:  creates a new instance of this
	 */
    public SpeciesDatabase()
    {
    	database = new ArrayList();
		clear();
    }

	/**
	 * clear the contents of the array list
	 *
	 * @param none
	 * @returns none
	 *
	 * @modifies:  this
	 * @effects:  clears the contents of the arraylist
	 */
    public void clear()
    {
    	database.clear();
    }
	
	/**
	 * returns the length of the array list
	 *
	 * @param none
	 * @returns int
	 *
	 * @effects:  returns the size of the array list as an integer
	 */
    public int length()
    {
    	return database.size();
    }
	
	/**
	 * returns species with population total at given index in array list
	 *
	 * @param int
	 * @returns SpeciesPop
	 *
	 * @effects:  provides SpeciesPop at given integer index
	 */
    public SpeciesPop speciesPopOf( int i)
    {
    	if ( (i < 0) || (i >= database.size()) )
    	{
    		return null;
    	}
		else
		{
			return (SpeciesPop) database.get( i);
		}
    }
	
	/**
	 * provides index of a species if represented in the array list, otherwise
	 * adds it to the array list
	 *
	 * @param Species
	 * @returns int
	 *
	 * @modifies: this
	 * @effects:  searches arraylist for given species; if not currently present
	 * in list then adds to end of list, with a population of zero.  Returns the
	 * index of this species in the array list regardless
	 */
    public int indexOf( Species s)
    {
    	// variable declared early as required outside loop
    	int i;
    	
    	// search arraylist for the given species	
		for ( i = 0; i < database.size(); i ++ )
		{
			// if present return its index
			if ( s.equals( ((SpeciesPop) database.get( i)).sp) )
			{
				return i;
			}
		}
		
		// if not present add to end of list and return the index
		database.add( new SpeciesPop( s, 0));
		return i;
    }
	
	/**
	 * records the birth of a species member
	 *
	 * if the species is new it is added to the arraylist
	 * regardless its population is incremented to reflect the birth
	 *
	 * @param Species
	 * @returns none
	 *
	 * @modifies: this
	 * @effects:  add species to arraylist if need be and regardless increments
	 * population
	 */ 
    public void birth( Species s)
    {
    	SpeciesPop sppop = null;
		
		// if the species is in the array list, find its SpeciesPop entry
		for ( int i = 0; i < database.size(); i ++ )
		{
			sppop = ( SpeciesPop) database.get( i);
			
	    	if ( s.equals( sppop.sp) )
	    	{
	    		break;
	    	}
			
			sppop = null;
		}
		
		// if it's a new Species, add a corresponding SpeciesPop to the list
		if ( sppop == null )
		{
			database.add( sppop = new SpeciesPop( s, 0));
		}

		// increment the species count to reflect the birth
		sppop.pop ++;
    }

    /*
     * for some reason removed by Sayama - allows direct additions to population
     * count using the index of the SpeciesPop in the array list
     *
    public void birth(int i) {
	SpeciesPop sppop = (SpeciesPop) database.get(i);
	sppop.pop ++;
    }
    */

    /*
     * for some reason removed by Sayama - allows deductions to population count
     * by finding the Species within the SpeciesPop array list
     *
    public void death(Species s) {
	SpeciesPop sppop = null;

	for (int i = 0; i < database.size(); i ++) {
	    sppop = (SpeciesPop) database.get(i);
	    if (s.equals(sppop.sp)) break;
	    sppop = null;
	}

	if (sppop == null) database.add(sppop = new SpeciesPop(s, 0));

	sppop.pop --;

	if (sppop.pop < 0) {
	    System.out.println("Non-existent species died!?");
	    System.exit(1);
	}
    }
    */
	
	/**
	 * records the death (decrement of population) of a species member
	 *
	 * @param int
	 * @returns none
	 *
	 * @modifies: this
	 * @effects:  decrements population count of species at given index
	 */
    public void death( int i)
    {
    	SpeciesPop sppop = ( SpeciesPop) database.get( i);
		sppop.pop --;
		
		// exit program if death event has been assigned to an (already) dead species
		if ( sppop.pop < 0 )
		{
			System.out.println( "Non-existent species died!?" );
	    	System.exit(1);
		}
    }
}
