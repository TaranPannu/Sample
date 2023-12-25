/******************************************************************************
 *
 *  DO NOT SUBMIT THIS CLASS WITH YOUR ASSIGNMENT
 *
 *  DO NOT MODIFY ANY CODE IN THIS CLASS
 *
 *  This class is provided so that you can run your code
 *  for Assignment 6.
 *
 *  ALL your code MUST be placed in the Ant.java class.
 *
 *  @author M.Hatcher November 2022
 *
 ******************************************************************************/

public class AntSim
{
    // consider reading the values from a text file, each labelled
    private double [][] pheromoneLevel;  // levels on each edge
    private int totalPheromone = 10000;  // available to each ant
    private double initialPheromone = 0.0001; // initial level on each edge
    private double evaporationRate;      // between generations
                                         // 0.01 is too low, 0.1 - 0.3 is better, 0.15 might be a sweet spot
    private int [][] edgeLengths;        // length of each edge
    private int numGens;                 // number of generations to run
    private Ant [] ants;                  // ant population
    private int antsPerGen;              // ants to be used per generation
    private double alpha;                // weighting parameter for edge pheromone
    private double beta;                 // weighting parameter for edge length
    
    // set up the sim parameters during constructions
    public AntSim(int numNodes, int numGens, int antsPerGen, double evaporationRate, double alpha, double beta, int [][] edgeLengths)
    {
        this.edgeLengths = edgeLengths;
        this.numGens = numGens;
        this.antsPerGen = antsPerGen;
        this.evaporationRate = evaporationRate;
        this.alpha = alpha;
        this.beta = beta;
        int numAnts = numGens * antsPerGen;
        
        // create the ants
        ants = new Ant [numAnts];
        for (int index = 0; index < numAnts; index ++)
        {
            ants [index] = new Ant(this, numNodes);
        }
        
        // note that pheromoneLevel[a][b] = pheromoneLevel[b][a]
        pheromoneLevel = new double [numNodes][numNodes];
        
        // set initial pheromone levels on each edge to 1
        for (int i = 0; i < numNodes - 1; i++)
        {
            for (int j = i + 1; j < numNodes; j++)
            {
                pheromoneLevel [i][j] = 0.0001;
                pheromoneLevel [j][i] = 0.0001;
            }
        }
    }
    
    // run the sim and return the Ant with the best tour
    public Ant run()
    {
        int bestTour = -1; // not yet known
        int bestAnt = -1;  // not yet known
        
        int offset = 0;
        for (int gen = 0; gen < numGens; gen ++)
        {
            // let this generation of ants find their paths
            for (int index = offset; index < offset + antsPerGen; index ++)
            {
                int tourLength = ants [index].tour();
                if ((bestTour ==  -1) || (tourLength < bestTour))
                {
                    bestTour = tourLength;
                    bestAnt = index;
                }
            }
            // THEN evaporate current pheromone level on edges
            for (int i = 0; i < pheromoneLevel.length - 1; i++)
            {
                for (int j = i; j < pheromoneLevel.length; j++)
                {
                    pheromoneLevel[i][j] *= (1 - evaporationRate);
                    pheromoneLevel[j][i] *= (1 - evaporationRate);
                }
            }
            
            // THEN update the pheromone levels on edges
            int totalTours = 0; // for output to console
            for (int index = offset; index < offset + antsPerGen; index ++)
            {
                ants [index].layPheromone();
                totalTours += ants [index].getTourLength();
            }
            double meanTour = (double) (totalTours/antsPerGen); // for output to console
            System.out.println("G"+gen+": mean tour = " + meanTour); // for output to console
            
            offset += antsPerGen;
        }
        
        return ants [bestAnt];
    }
    
    // get length of edge between nodes i and j
    public int getEdgeLength(int i, int j)
    {
        return edgeLengths[i][j];
    }
    
    // get pheromone level of edge between nodes i and j
    public double getPheromoneLevel(int i, int j)
    {
        return pheromoneLevel [i][j];
    }
    
    // add pheromone to edge between nodes i and j
    // for ease of access and update, each edge has same representaiton in both directions: level[i,j] = level[j,i]
    public void addPheromone(int i, int j, double level)
    {
        pheromoneLevel [i][j] += level;
        pheromoneLevel [j][i] += level;
    }
    
    // simple get methods
    public Ant getAnt(int index)      { return ants [index]; }
    public double getTotalPheromone() { return totalPheromone; }
    public double getAlpha()          { return alpha; }
    public double getBeta()           { return beta; }
    
    // print out (current) pheromone levels on edges (useful for debug)
    public void printLevels()
    {
        for (int i = 0; i < pheromoneLevel.length - 1; i++)
        {
            for (int j = i + 1; j < pheromoneLevel.length; j++)
            {
                System.out.print(i+","+j+": ph:"+pheromoneLevel [i][j]);
                System.out.println(" el:"+edgeLengths [i][j]);
            }
        }
    }
}
