// For maintaining species database and birth/death detection

public class Phenotype
{
    public int width, height;

    Phenotype( int w, int h)
    {
    	width = w;
		height = h;
    }

	/**
	 * phenotypes are equal if they share width and height - actual gene
	 * sequences are irrelevant
	 */
    public boolean equals( Phenotype another)
    {
    	if ( (width == another.width) && (height == another.height) )
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
