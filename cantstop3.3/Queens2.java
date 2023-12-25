

import java.lang.Math;
import java.util.*;
import java.util.stream.Stream;

import javax.swing.plaf.synth.SynthPasswordFieldUI;

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
public class Queens2
{
    private static int boardSize = 10;
    
    // inverts the order of a series of genes in the genotype
    public static Integer[] inversionMutate(Integer[] genotype, double p)
    {
        // int temp[]=new int[genotype.length];
   Integer[] temp=new Integer[genotype.length];// copying genotype to temp
  
   if (Math.random() < p) {
       int x=(int)(Math.random()*10);
       int y=(int)(Math.random()*10);
       x=Math.min(x, y);
       y=Math.max(x, y);
       int i=0;
    for (i = 0; i < x; i++) {
       temp[i]=genotype[i];
    }
    for (int i1 = y; i1>=x; i1--) {
       temp[i]=genotype[i1];

       i++;
    }
    if((y+1)<10)
    for (int i2 = y+1; i2 < temp.length; i2++) {
       temp[i2]=genotype[i2];
    }
   }

   return temp;
    }
    
    /* performs fitness-proportional parent selection
     * also known as 'roulette wheel' selection
     * selects two parents that are different to each other
     */
    public static Integer[][] rouletteSelect(Integer[][] population)
    {
        Integer[][] parents = new Integer[2][boardSize];

        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        int TotalPopulation = population.length;
        int[] cumulative = new int[TotalPopulation];
        int sum = 0;
        int i1 = 0;
        while (i1 < population[0].length) {
            sum += population[0][i1];
            i1++;
        }
        cumulative[0] = sum;
        // Calculate the total fitness of the population
        //int sum1 = 0;
       // int total=0;
        int total = 0;
        for (int i = 0; i < TotalPopulation; i++) {
             //sum1 = 0;
            for (int j = 0; j < population[i].length; j++) {
              //  sum1 += population[i][j];
                total+=population[i][j];
            }
          //  total += sum1;
        }
        // Create an array to store the cumulative fitness
        
        
       /*  int sum = 0;
        for (int i = 0; i < population[0].length; i++) {
            sum += population[0][i];
        }
        cumulative[0] = sum;
*/
    /*     for (int i = 1; i < TotalPopulation; i++) {
            int sum2= 0;
            for (int j = 0; j < population[i].length; j++) {
                sum2 += population[i][j];
            }
            cumulative[i] = cumulative[i - 1] + sum2;
        }

        // Select the first parent
        int randomPoints
 = (int) (Math.random() * total);
        int index = 0;
        while (cumulative[index] < randomPoints
) {
            index++;
        }
        parents[0] = population[index];*/
     //   int[] cumulative = new int[TotalPopulation];
        int sum2 = 0;
        for (int i = 0; i < population[0].length; i++) {
            sum2 += population[0][i];
        }
        cumulative[0] = sum2;
        
        for (int i = 1; i < TotalPopulation; i++) {
            int sum3 = 0;
            for (int j = 0; j < population[i].length; j++) {
                sum3 += population[i][j];
            }
            cumulative[i] = cumulative[i - 1] + sum3;
        }
        
        // Select the first parent
        Random ran = new Random();
        int randomPoints
 = ran.nextInt(total);
                int index = -1;
        for (int i = 0; i < TotalPopulation; i++) {
            if (cumulative[i] >= randomPoints
) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            parents[0] = population[index];
        }
        
        // Select the second parent (ensuring that it is different from the first
        // parent)
       /*  do {
            randomPoints
 = (int) (Math.random() * total);
            index = 0;
            while (cumulative[index] < randomPoints
) {
                index++;
            }
            parents[1] = population[index];
        } while (Arrays.equals(parents[0], parents[1]));*/
        randomPoints
 = (int) (Math.random() * total);
        index = 0;
        while (cumulative[index] < randomPoints
) {
            index++;
        }
        parents[1] = population[index];
        
        while (Arrays.equals(parents[0], parents[1])) {
            randomPoints
 = (int) (Math.random() * total);
            index = 0;
            while (cumulative[index] < randomPoints
) {
                index++;
            }
            parents[1] = population[index];
        }
        
        // END OF YOUR CODE

        return parents;
    }
    
    /* creates a new population through λ + μ survivor selection
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
        Integer[][] TotalPopulation = new Integer[lambda][10];
        for (int i = 0; i < n; i++) {
    
        for (int j = 0; j < 10; j++) {
       TotalPopulation[i][j]=population[i][j];
            System.out.print(TotalPopulation[i][j]+" ");
        }
        System.out.println();
        }
        int x=0;
        for (int index = n; index < lambda; index++) {
          for (int i = 0; i < 10; i++) {
           TotalPopulation[index][i]=children[x][i];
          //  System.out.print(" index="+index+" m="+(m)+"k"+i+" ");
          System.out.print(TotalPopulation[index][i]+" ");

          }
          x++;
          System.out.println();
        }
        //System.arraycopy(population, 0, TotalPopulation, 0, n);
      //  System.arraycopy(children, 0, TotalPopulation, n, m);
       /* 
        Arrays.sort(TotalPopulation, new Comparator<Integer[]>() {
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
        System.arraycopy(TotalPopulation, 0, newPop, 0, n);
        // END OF YOUR CODE*/
        Arrays.sort(TotalPopulation, (individual1, individual2) -> {
            int fitness1 = 0;
            for (int i = 0; i < individual1.length; i++) {
                fitness1 += individual1[i];
            }
        
            int fitness2 = 0;
            for (int i = 0; i < individual2.length; i++) {
                fitness2 += individual2[i];
            }
        
            return Integer.compare(fitness2, fitness1);
        });
        
        System.arraycopy(TotalPopulation, 0, newPop, 0, n);
        
        return newPop;
    }
    
    // counts the number of unique genotypes in the population
    public static int genoDiversity(Integer[][] population)
    {

        int uniqueTypes = 0;
        Set<List> m1=new HashSet<>();
for (int i = 0; i < population.length; i++) {
m1.add(Arrays.asList(population[i]));
}

        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        uniqueTypes = m1.size();
        // END OF YOUR CODE
        
        return uniqueTypes;
    }
}

