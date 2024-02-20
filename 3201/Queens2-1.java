
//taran
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
    
    public static Integer[] inversionMutate(Integer[] genotype, double p)
    {
   Integer[] temp=new Integer[genotype.length];
  
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
  
    public static Integer[][] rouletteSelect(Integer[][] population)
    {
        Integer[][] parents = new Integer[2][boardSize];

      
        int TotalPopulation = population.length;
        int[] cumulative = new int[TotalPopulation];
        int sum = 0;
        int i1 = 0;
        while (i1 < population[0].length) {
            sum += population[0][i1];
            i1++;
        }
        cumulative[0] = sum;
        
        int total = 0;
        for (int i = 0; i < TotalPopulation; i++) {
            for (int j = 0; j < population[i].length; j++) {
                total+=population[i][j];
            }
        }
       
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
        }
        System.out.println();
        }
        int x=0;
        for (int index = n; index < lambda; index++) {
          for (int i = 0; i < 10; i++) {
           TotalPopulation[index][i]=children[x][i];

          }
          x++;
        }
        Map<Integer,ArrayList<List<Integer>>> m1=new HashMap<>();
        ArrayList<Integer> vv=new ArrayList<>();
       for (int i = 0; i < TotalPopulation.length; i++) {
        int fit=measureFitness(TotalPopulation[i]);
        if(m1.containsKey(fit)){
            ArrayList<List<Integer>> v1=m1.get(fit);
            List al = Arrays.asList(TotalPopulation[i]);
            v1.add(al);
        }else{
            vv.add(fit);
            ArrayList<List<Integer>> v1=new ArrayList<>();
            List al = Arrays.asList(TotalPopulation[i]);
            v1.add(al);   
        }
       }
       Collections.sort(vv, Collections.reverseOrder());  
int xx=0;
     for (Integer integer : vv) {
        if(xx>=10)break;
       ArrayList<List<Integer>> v2=m1.get(integer);
for (List<Integer> integer2 : v2) {
    Integer[]arr = new Integer [10];

    //Converting List to Array
   newPop[xx]=v2.toArray(arr);
   xx++;
}
     }
      /*   Arrays.sort(TotalPopulation, (individual1, individual2) -> {
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
        
        System.arraycopy(TotalPopulation, 0, newPop, 0, n);*/
        
        return newPop;
    }
    
    public static int genoDiversity(Integer[][] population)
    {

        int uniqueTypes = 0;
        Set<List> m1=new HashSet<>();
for (int i = 0; i < population.length; i++) {
m1.add(Arrays.asList(population[i]));
}

        
        uniqueTypes = m1.size();
        
        return uniqueTypes;
    }

    public static int measureFitness(Integer [] m)
    {
        /* The initial fitness is the maximum pairs of queens
         * that can be in check (all possible pairs in check).
         * So we are using it as the maximum fitness value.
         * We deduct 1 from this value for every pair of queens
         * found to be in check.
         * So, the lower the score, the lower the fitness.
         * For a 10x10 board the maximum fitness is 45 (no checks),
         * and the minimum fitness is 0 (all queens in a line).
         */
        int fitness = (int) (0.5 * boardSize * (boardSize - 1));
        
//int m[]={ 9, 5, 6, 10, 8, 7, 1, 3, 2, 4 };
   int m1[][]=new int [m.length][m.length];
   //first loop is goona places these queens
   for (int i = 0; i <m.length; i++) {
m1[i][m[i]-1]=1;
   }
   int count=0;

for (int i = 0; i < m.length; i++) {
    int x=i;
    int y=m[i]-1;
   // System.out.println(x+" . "+y);
    while(1>0){
        y+=1;

        if(x<0 || y<0  || x>=m.length || y>=m.length)
        break;
if(m1[x][y]==1)
{count++;
//System.out.println(x+" "+y);
}

    }
    x=i;
    y=m[i]-1;
    while(1>0){
        y-=1;

        if(x<0 || y<0  || x>=m.length || y>=m.length)
        break;
        if(m1[x][y]==1)
        {count++;
      //  System.out.println(x+" "+y);
    }

    }    x=i;
    y=m[i]-1;
    while(1>0){
        x+=1;

        if(x<0 || y<0  || x>=m.length || y>=m.length)
        break;
        if(m1[x][y]==1)
        {count++;
       // System.out.println(x+" "+y);
    }

    }    x=i;
    y=m[i]-1;
    while(1>0){
        x-=1;

        if(x<0 || y<0  || x>=m.length || y>=m.length)
        break;
        if(m1[x][y]==1)
        {count++;
        //System.out.println(x+" "+y);
    }

    }   x=i;
    y=m[i]-1;
    while(1>0){
        y+=1;
x+=1;
        if(x<0 || y<0  || x>=m.length || y>=m.length)
        break;

        if(m1[x][y]==1)
        {count++;
      //  System.out.println(x+" "+y);
    }


    }   x=i;
    y=m[i]-1;
    while(1>0){
        y-=1;
x-=1;
        if(x<0 || y<0  || x>=m.length || y>=m.length)
        break;
        if(m1[x][y]==1)
        {count++;
      //  System.out.println(x+" "+y);
    }

    }   x=i;
    y=m[i]-1;
    while(1>0){
        x-=1;
y+=1;
        if(x<0 || y<0  || x>=m.length || y>=m.length)
        break;

        if(m1[x][y]==1)
        {count++;
    //    System.out.println(x+" "+y);
}

    }   x=i;
    y=m[i]-1;
     while(1>0){
        y-=1;
x+=1;
        if(x<0 || y<0  || x>=m.length || y>=m.length)
        break;

        if(m1[x][y]==1)
        {count++;
      //  System.out.println(x+" "+y);
    }
    }
}
        return fitness-(count/2);
    }
}

