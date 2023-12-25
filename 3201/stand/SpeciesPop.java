/**
 * @overview: class records a species with its population total
 *
 */
public class SpeciesPop
{
	// Species
    public Species sp;
    
    // integer population total
    public int pop;

	/**
	 * constructor for the class
	 *
	 * @param Species
	 * @param int
	 *
	 * @modifies: this
	 * @effects:  creates an instance of this, taking species and integer population
	 * size
	 */
    public SpeciesPop(Species s, int p)
    {
    	sp = s;
		pop = p;
    }
}