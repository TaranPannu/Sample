/*
 *  DO NOT SUBMIT THIS CLASS WITH YOUR ASSIGNMENT
 *
 *  This class is only provided so that you can test your code
 *  for Assignment 3.
 *
 *  ALL your code MUST be placed in the Queens2.java class.
 *
 *  MH March 2023
 */
public class Tester2
{
	public static void main(String[] args)
	{
        // let's use a small population for testing purposes
        Integer [][] population1 = new Integer [5][10];
        population1 [0] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        population1 [1] = new Integer[]{ 9, 5, 6, 10, 8, 7, 1, 3, 2, 4 };
        population1 [2] = new Integer[]{ 9, 4, 3, 1, 2, 5, 10, 7, 8, 6 };
        population1 [3] = new Integer[]{ 7, 8, 9, 1, 10, 2, 3, 4, 5, 6 };
        population1 [4] = new Integer[]{ 10, 6, 4, 2, 8, 5, 9, 1, 3, 7 };
        
        // the known fitnesses for this test population
        Integer [] fitnesses = new Integer [5];
        fitnesses [0] = 0; // worst possible, all aligned
        fitnesses [1] = 38;
        fitnesses [2] = 39;
        fitnesses [3] = 29;
        fitnesses [4] = 44; // very close to maximum (45)
        
        // expected frequencies after 1000 runs of roulette parent selection
        Integer [] expectedFrequency = new Integer [5];
        expectedFrequency [0] = 0;
        expectedFrequency [1] = 5060;
        expectedFrequency [2] = 5200;
        expectedFrequency [3] = 3866;
        expectedFrequency [4] = 5866;
        
        // test 1: perform inversion mutation on the population
        System.out.println("1. Testing Inversion mutation: (80% rate) ");
        for (int index = 0; index < 5; index ++)
        {
            System.out.print(index + ". Before: ");
            printGenotype( population1[index]);
            population1 [index] = Queens2.inversionMutate(population1[index], 0.8);
            System.out.print("   After:  ");
            printGenotype( population1[index]);
            System.out.println();
        }
        
        // test 2: perform roulette selection 10000 times on same population
        // (first reset the population after task 1)
        population1 [0] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        population1 [1] = new Integer[]{ 9, 5, 6, 10, 8, 7, 1, 3, 2, 4 };
        population1 [2] = new Integer[]{ 9, 4, 3, 1, 2, 5, 10, 7, 8, 6 };
        population1 [3] = new Integer[]{ 7, 8, 9, 1, 10, 2, 3, 4, 5, 6 };
        population1 [4] = new Integer[]{ 10, 6, 4, 2, 8, 5, 9, 1, 3, 7 };
        
        Integer [][] parents = new Integer [2][10];
        int [] tally = new int [population1.length];
        
        System.out.println("\n2. Testing Parent Selection (10000 runs):");
        System.out.println("Number of times each parent was selected:");
        for (int count = 0; count < 10000; count ++)
        {
            parents = Queens2.rouletteSelect(population1);
            int fitness0 = Queens.measureFitness(parents[0]);
            int fitness1 = Queens.measureFitness(parents[1]);
            for (int index = 0; index < population1.length; index ++)
            {
                if (fitness0 == fitnesses[index]) { tally[index] ++; }
                if (fitness1 == fitnesses[index]) { tally[index] ++; }
            }
        }
        // should see actual frequencies close to expected frequencies
        for (int index = 0; index < population1.length; index ++)
        {
            System.out.println(index + ". rough expectation: " + expectedFrequency[index] + ", actual: " + tally[index] );
        }
        
        // test 3: perform survivor selection
        System.out.println("\n3. Testing Survivor Selection:");
        System.out.println("Original Population:");
        Integer [][] population2 = createTestPopulation(10);
        System.out.println("\n Children:");
        Integer [][] children = createTestPopulation(20);
        
        population2 = Queens2.survivorSelection(population2, children);
        
        System.out.println("\nFittest 10 individuals form the new population:");
        for (int index = 0; index < 10; index ++)
        {
            System.out.print(index + ": Fitness: " + Queens.measureFitness(population2[index]) + ", Genotype: ");
            printGenotype(population2[index]);
        }
        
        // test 4: measure genotypic diversity
        // create a new population to test
        Integer [][] population3 = new Integer [10][10];
        population3 [0] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        population3 [1] = new Integer[]{ 9, 5, 6, 10, 8, 7, 1, 3, 2, 4 };
        population3 [2] = new Integer[]{ 9, 4, 3, 1, 2, 5, 10, 7, 8, 6 };
        population3 [3] = new Integer[]{ 7, 8, 9, 1, 10, 2, 3, 4, 5, 6 };
        population3 [4] = new Integer[]{ 10, 6, 4, 2, 8, 5, 9, 1, 3, 7 };
        population3 [5] = new Integer[]{ 3, 2, 7, 4, 10, 1, 8, 9, 6, 5 };
        population3 [6] = new Integer[]{ 10, 9, 8, 6, 7, 2, 3, 4, 1, 5 };
        population3 [7] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        population3 [8] = new Integer[]{ 9, 5, 6, 10, 8, 7, 1, 3, 2, 4 };
        population3 [9] = new Integer[]{ 9, 4, 3, 1, 2, 5, 10, 7, 8, 6 };
        
        int actual = 7;
        int returned = Queens2.genoDiversity(population3);
        
        System.out.println("\n4. Testing Genotypic Diversity:");
        System.out.println("Number of different genotypes present:");
        System.out.println("Actual: " + actual);
        System.out.println("Computed: " + returned);
        
        System.out.println("END OF TESTING\n\n");
	}
    
    public static void printGenotype(Integer[] genotype)
    {
        for (int index = 0; index < 10; index ++)
        {
            System.out.print(" " + genotype[index]);
        }
        System.out.println();
    }
    
    public static Integer[][] createTestPopulation(int popSize)
    {
        Integer[][] testPop = new Integer[popSize][10];
        
        for (int index = 0; index < popSize; index ++)
        {
            testPop [index] = Queens.randomGenotype();
            System.out.print(index + ": Fitness: " + Queens.measureFitness(testPop[index]) + ", Genotype: ");
            printGenotype(testPop[index]);
        }
        
        return testPop;
    }
}
