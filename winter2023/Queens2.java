import java.lang.Math;
import java.util.*;

/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester2.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH DOC_20.23.03.06
 */
public class Queens2 {
    private static int boardSize = 10;

    // inverts the order of a series of genes in the genotype
    public static Integer[] inversionMutate(Integer[] genotype, double p) {
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE: (pretends to invert first test genotype)
        if (Math.random() < p) {
            int point1 = (int) (Math.random() * genotype.length);
            int point2 = (int) (Math.random() * genotype.length);

            if (point1 > point2) {
                int temp = point1;
                point1 = point2;
                point2 = temp;
            }

            for (int i = point1, j = point2; i < j; i++, j--) {
                int temp = genotype[i];
                genotype[i] = genotype[j];
                genotype[j] = temp;
            }
        }
        // END OF YOUR CODE

        return genotype;
    }

    /*
     * performs fitness-proportional parent selection
     * also known as 'roulette wheel' selection
     * selects two parents that are different to each other
     */
    public static Integer[][] rouletteSelect(Integer[][] population) {
        Integer[][] parents = new Integer[2][boardSize];

        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        int populationSize = population.length;

        // Calculate the total fitness of the population
        int totalFitness = 0;
        for (int i = 0; i < populationSize; i++) {
            int sum = 0;
            for (int j = 0; j < population[i].length; j++) {
                sum += population[i][j];
            }
            totalFitness += sum;
        }

        // Create an array to store the cumulative fitness
        int[] cumulativeFitness = new int[populationSize];

        int sum = 0;
        for (int i = 0; i < population[0].length; i++) {
            sum += population[0][i];
        }
        cumulativeFitness[0] = sum;

        for (int i = 1; i < populationSize; i++) {
            int sum1 = 0;
            for (int j = 0; j < population[i].length; j++) {
                sum1 += population[i][j];
            }
            cumulativeFitness[i] = cumulativeFitness[i - 1] + sum1;
        }

        // Select the first parent
        int randomFitness = (int) (Math.random() * totalFitness);
        int index = 0;
        while (cumulativeFitness[index] < randomFitness) {
            index++;
        }
        parents[0] = population[index];

        // Select the second parent (ensuring that it is different from the first
        // parent)
        do {
            randomFitness = (int) (Math.random() * totalFitness);
            index = 0;
            while (cumulativeFitness[index] < randomFitness) {
                index++;
            }
            parents[1] = population[index];
        } while (Arrays.equals(parents[0], parents[1]));

        // END OF YOUR CODE

        return parents;
    }

    /*
     * creates a new population through λ + μ survivor selection
     * given a population of size n, and a set of children of size m
     * this method will measure the fitness of all individual in the
     * combined population, and return the n fittest individuals
     * as the new population
     */
    public static Integer[][] survivorSelection(Integer[][] population, Integer[][] children) {
        Integer[][] newPop = new Integer[10][10];

        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        int n = population.length;
        int m = children.length;
        int lambda = n + m;
        Integer[][] combinedPopulation = new Integer[lambda][];
        for (int i = 0; i < n; i++) {
           combinedPopulation[i]= Arrays.copyOf(population[i]); 
           for (int integers : combinedPopulation[i]) {
            System.out.print(integers+" ");
           }
           System.out.println();
        }
        for (int index = n; index < lambda; index++) {
            combinedPopulation[index]= Arrays.copyOf(population[index]); 
            for (int integers : combinedPopulation[index]) {
                System.out.print(integers+" ");
               }
               System.out.println();
        }
        System.arraycopy(population, 0, combinedPopulation, 0, n);
        System.arraycopy(children, 0, combinedPopulation, n, m);
        Arrays.sort(combinedPopulation, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] individual1, Integer[] individual2) {
                int fitness = 0;
                for (int i = 0; i < individual1.length; i++) {
                    fitness += individual1[i];
                }
                int fitness1 = fitness;

                int fitness_ = 0;
                for (int i = 0; i < individual1.length; i++) {
                    fitness_ += individual1[i];
                }
                int fitness2 = fitness_;
                return Integer.compare(fitness2, fitness1);
            }
        });
        System.arraycopy(combinedPopulation, 0, newPop, 0, n);
        // END OF YOUR CODE

        return newPop;
    }

    // counts the number of unique genotypes in the population
    public static int genoDiversity(Integer[][] population) {
        int uniqueTypes = 0;

        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        Set<List<Integer>> genotypes = new HashSet<>();
        for (Integer[] individual : population) {
            genotypes.add(Arrays.asList(individual));
        }
        uniqueTypes = genotypes.size();
        // END OF YOUR CODE

        return uniqueTypes;
    }
}
