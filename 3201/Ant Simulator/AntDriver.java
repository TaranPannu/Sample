/******************************************************************************
 *  ACO for TSP
 *
 *  @author M.Hatcher November 2021
 *
 *  to run: java AntDriver a b c d e f
 *
 *  a = number of nodes
 *  b = number of generations
 *  c = number of ants per generation
 *  d = pheromone evaporation rate between generations (between 0.0 and 1.0)
 *  e = alpha (weighting for effect of pheremone level in choice of edge to follow)
 *  f = beta (weighting for effect of length in choice of edge to follow)
 *
 *  typical run: java AntDriver 100 100 100 0.15 1 1
 *
 *  DO NOT MODIFY ANY CODE IN THIS CLASS!
 *
 ******************************************************************************/
import java.lang.Math;

public class AntDriver
{
    public static void main( String args [])
    {
        int numNodes = Integer.parseInt(args[0]);
        int numGens = Integer.parseInt(args[1]);
        int antsPerGen = Integer.parseInt(args[2]);
        double evaporationRate = Double.parseDouble(args[3]);
        double alpha = Double.parseDouble(args[4]);
        double beta = Double.parseDouble(args[5]);
        int [][] edgeLengths = generateEdges(numNodes, 1, 20);
        
        AntSim sim = new AntSim(numNodes, numGens, antsPerGen, evaporationRate, alpha, beta, edgeLengths);
        Ant bestAnt = sim.run();
        bestAnt.printTour();
        /*
        System.out.println("\nHow the others performed: ");
        for (int index = 0; index < numGens*antsPerGen; index ++)
        {
            System.out.print("Ant #" + index);
            sim.getAnt(index).printTour();
        }*/
        
        //sim.printLevels();
    }
    
    // creates a set of edges between each pair of nodes, each edge with a random length
    private static int [][] generateEdges(int numNodes, int min, int max)
    {
        int [][] edgeLengths = new int [numNodes][numNodes];
        
        // create random edge lengths between nodes
        for (int i = 0; i < numNodes - 1; i++)
        {
            for (int j = i + 1; j < numNodes; j++)
            {
                edgeLengths [i][j] = (int) (Math.random() * max) + min;
                edgeLengths [j][i] = edgeLengths[i][j];
            }
        }
        
        return edgeLengths;
    }
}
