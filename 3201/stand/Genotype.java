/**
 * @overview:  records a species genome in terms of:
 *
 * raw integer cell states.
 * sequence of characters representing genes: 'C'ore, 'G'rowth, 'T'urn.
 */
public class Genotype
{
	/**
	 * string holding raw integer cells values of the genotype
	 */
    public String rawData;
    
    /**
     * string holding gene characters (decrypted from raw data) of the genotype
     */
    public String sequence;
	
	/**
	 * constructor for the class
	 *
	 * @param String
	 * 
	 * @modifies: this
	 * @effects:  creates an instance of this
	 */
    public Genotype( String raw)
    {
    	rawData = raw;
		compressRawData();
    }

	/**
	 * checks for equality of genotypes (so multiple occurances in cell space
	 * can be noted and species uniquely identified)
	 *
	 * @param Genotype
	 * @returns boolean
	 *
	 * @effects:  checks for equality between this and another genotype by a String
	 * comparison of their cell state sequences - returns true if equal, false
	 * otherwise
	 */
    public boolean equals( Genotype another)
    {
    	// if either sequence is null return false
    	if ( (sequence == null) || (another.sequence == null) )
    	{
    		return false;
		}
		// if the sequences are equal return true...
		if ( sequence.equals( another.sequence) )
		{
			return true;
		}
		// ...otherwise return false
		else
		{
			return false;
		}
    }
	
	/**
	 * converts string of cell states into a string of genes
	 *
	 * @param none
	 * @returns none
	 *
	 * @modifies: this
	 * @effects:  examines rawData String and creates sequence string as a set
	 * of characters corresponding to certain state sequences
	 */
    private void compressRawData()
    {
    	/**
    	 * note that this sequencing will recognise the genotype of structures
    	 * that utilise left/right turns but will not at present recognise the
    	 * combinations of genes that lead to left v right turns.
    	 *
    	 * Such recognition can be done by further analysis of the sequence string:
    	 *
    	 * 'T' + [C....C] + 'T' -> left turn ( say 'L' )
    	 * 'T' + [C....C] + 'G' -> right turn ( say 'R' )
    	 *
    	 * where the first 'T' is the first occurance following some
    	 * [C....C] + [G....G] + [C....C] type sequence.
    	 *
    	 * ( [X....X] refers to zero or more occurances of gene X )
    	 *
    	 * A method can be added to create a new string/modify the sequence string
    	 * to explicitly show left and right turns however this will be at the
    	 * expense of loss of information about intermittent core cells
    	 *
    	 * MH
    	 */
    	sequence = "";
    	
		for ( int i = 0; i < rawData.length(); i ++ )
		{
			// code 'C' for core
			if ( rawData.regionMatches( i, "1", 0, 1) )
			{
				sequence += "C";
			}
			// code 'T' for turn
	    	else if ( rawData.regionMatches( i, "041", 0, 3) )
	    	{
	    		sequence += "T";
				i += 2;
	    	}
	    	// code 'G' for growth
	    	else if ( rawData.regionMatches( i, "071", 0, 3) )
	    	{
				sequence += "G";
				i += 2;
	    	}
	    	// if there's anything else in there it's not valid so break out
	    	else
	    	{
				sequence = null;
				break;
	    	}
		}
    }
}
