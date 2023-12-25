/**
 * @overview:  the representation of a species in the CA
 *
 * records:
 * phenotype (i.e. perimeter details)
 * genotype (i.e. genome as both raw integer and short sequence string)
 * 
 * allows comparison between species by phenotype and genotype
 */
public class Species
{
    public Phenotype pheno;
    public Genotype geno;
	
	/**
	 * constructor for the class (1)
	 *
	 * @param Phenotype
	 * @param Genotype
	 *
	 * @modifies: this
	 * @effects:  creates an instance of this using direct phenotype and genotype
	 */
	public Species ( Phenotype p, Genotype g)
    {
    	pheno = p;
		geno = g;
    }
	
	/**
	 * constructor for the class (2)
	 *
	 * @param int
	 * @param int
	 * @param String
	 *
	 * @modifies: this
	 * @effects:  creates an instance of this using loop width and height and
	 * raw genotype string (i.e. string of integer cell states in genome)
	 */
    public Species ( int w, int h, String raw)
    {
    	// deduce Phenotype and Genotype from raw data
    	pheno = new Phenotype( w, h);
		geno = new Genotype( raw);
    }
	
	/**
	 * consider species as equal if phenotype and genotype are identical to this
	 */
    public boolean equals( Species another)
    {
    	if ( (pheno.equals(another.pheno)) && (geno.equals(another.geno)) )
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
