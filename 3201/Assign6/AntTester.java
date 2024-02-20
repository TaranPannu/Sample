/*
*  DO NOT SUBMIT THIS CLASS WITH YOUR ASSIGNMENT
*
*  This class is only provided so that you can test your code
*  for Assignment 4.
*
*  ALL your code MUST be placed in the Ant.java class.
*
*  MH October 2022
*/
import java.util.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class AntTester
{
    public static void main( String args [])
    {
        // setup test conditions
        int numNodes = 20;
        int numGens = 100;
        int antsPerGen = 100;
        double evaporationRate = 0.15;
        double alpha = 1;
        double beta = 2;
        int currentNode = 19;
        int [][] edgeLengths = generateEdges(numNodes);
        AntSim sim = new AntSim(numNodes, numGens, antsPerGen, evaporationRate, alpha, beta, edgeLengths);
        Ant testAnt = new Ant(sim, numNodes);
        setPheromoneLevels(numNodes, sim);
        boolean visited [] = {true, true, false, true, false, false, false, true, false, false, true, true, true, true, false, false, true, false, false, true};
        DecimalFormat df = new DecimalFormat("0.00");
        
        /* show FULL list of edges in console - commented out, might be useful when debugging
        System.out.println("FULL list of edges:\nEdge\t\tlength\t\tpheromone");
        for (int i = 0; i < numNodes - 1; i++)
        {
            for (int j = i + 1; j < numNodes; j++)
            {
                System.out.println("("+i+","+j+")\t\t"+edgeLengths[i][j]+"\t\t"+sim.getPheromoneLevel(i,j));
            }
        }*/
        
        /*
         * test Ant.java methods (1) to (5)
         * these methods 'chain': the output from one method is used by the next
         * but this testing code uses the known correct answers as input parameters, to allow
         * each method's correctness to be tested independently
         */
        
        // known correct answers
        ArrayList<Integer> correctNotVisited = initIntArray(new int[]{2,4,5,6,8,9,14,15,17,18});
        ArrayList<Integer> correctLengths = initIntArray(new int[]{8,10,6,7,9,10,10,6,8,9});
        ArrayList<Double> correctLevels = initDoubleArray(new double[]{7,9,10,11,6,7,5,6,8,9});
        ArrayList<Double> correctProbs = initDoubleArray(new double[]{0.08,0.07,0.21,0.17,0.06,0.05,0.04,0.13,0.10,0.09});
        ArrayList<Double> correctCumulative = initDoubleArray(new double[]{0.08,0.15,0.36,0.53,0.59,0.64,0.68,0.81,0.91,1.00});
        
        // generate output from your code
        ArrayList<Integer> notVisited = testAnt.notVisited(visited);
        ArrayList<Integer> lengths = testAnt.getEdgeLengths(currentNode, correctNotVisited);
        ArrayList<Double> levels = testAnt.getPheromoneLevels(currentNode, correctNotVisited);
        ArrayList<Double> probabilities = testAnt.edgeProbabilities(correctLevels, correctLengths);
        ArrayList<Double> cumulative = testAnt.cumulativeProbabilities(correctProbs);
        
        // display results in console
        System.out.println("\n\nTesting methods (1) to (5):\n");
        System.out.println("List of nodes not yet visited:");
        System.out.println("'A' is what the values should be, 'B' is the values given by your code\n");
        System.out.println("node\t\tedge length\tpheromone\tprobability\tcumulative prob.");
        System.out.println("A\tB\tA\tB\tA\tB\tA\tB\tA\tB\n");
        for (int node = 0; node < notVisited.size(); node ++)
        {
            System.out.print(correctNotVisited.get(node)+"\t"+notVisited.get(node)+"\t");
            System.out.print(correctLengths.get(node)+"\t"+lengths.get(node)+"\t");
            System.out.print(correctLevels.get(node)+"\t"+levels.get(node)+"\t");
            System.out.print(correctProbs.get(node)+"\t"+df.format(probabilities.get(node))+"\t");
            System.out.println(correctCumulative.get(node)+"\t"+df.format(cumulative.get(node)));
        }
        System.out.println("\n(all probabilities are rounded to 2 decimal places)");
        
        // test Ant.java method (6)
        
        // call chooseNextNode() 1000 times from the same starting node
        int [] timesChosen = new int [numNodes];
        for (int count = 0; count < 10000; count ++)
        {
            int chosen = testAnt.chooseNextNode(correctCumulative);
            timesChosen[correctNotVisited.get(chosen)]++;
        }
        int [] expected = new int []{0,0,800,0,700,2100,1700,0,600,500,0,0,0,0,400,1300,0,900,900,0};
        
        // display results in console
        System.out.println("\n\nTesting method (6): choosing next node to visit\n");
        System.out.println("Run chooseNextNode 10000 times from starting node " + currentNode + ":\n");
        System.out.println("'expected' is what the values should roughly be, 'actual' is the values given by your code\n");
        System.out.println("node\ttimes chosen:\n\texpected\tactual\n");
        for (int index = 0; index < numNodes; index ++)
        {
            System.out.print(index+"\t");
            System.out.println(expected[index]+"\t\t"+timesChosen[index]);
        }
        System.out.println("\nEND OF TESTING\n\n    ");
    }
    
    // creates a set of edges for testing
    private static int [][] generateEdges(int numNodes)
    {
        int [][] edgeLengths = new int [numNodes][numNodes];
        
        for (int i = 0; i < numNodes - 1; i++)
        {
            for (int j = i + 1; j < numNodes; j++)
            {
                edgeLengths [i][j] = 1+(i%5)+(j%7);
                edgeLengths [j][i] = 1+(i%5)+(j%7);
            }
        }
        
        return edgeLengths;
    }
    
    // lays pheromone on each edge for testing
    private static void setPheromoneLevels(int numNodes, AntSim sim)
    {
        for (int i = 0; i < numNodes - 1; i++)
        {
            for (int j = i + 1; j < numNodes; j++)
            {
                double level = 0.9999+(i%7)+(j%5);
                sim.addPheromone(i, j, level);
            }
        }
    }
    
    // because java won't allow direct initialisation of arraylists
    public static ArrayList<Integer> initIntArray(int [] list)
    {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for( int index = 0; index < list.length; index ++)
        {
            arrayList.add(list[index]);
        }
        return arrayList;
    }
    public static ArrayList<Double> initDoubleArray(double [] list)
    {
        ArrayList<Double> arrayList = new ArrayList<Double>();
        for( int index = 0; index < list.length; index ++)
        {
            arrayList.add(list[index]);
        }
        return arrayList;
    }
}
