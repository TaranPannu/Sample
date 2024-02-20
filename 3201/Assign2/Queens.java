import java.lang.Math;
import java.util.*;

/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH 2021
 */
public class Queens
{
    private static int boardSize = 10;
    
    // creates a valid genotype with random values
    public static Integer [] randomGenotype()
    {
        Integer [] genotype = new Integer [boardSize];
        ArrayList<Integer> v=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int x=(int)(Math.random()*10)+1;
            while(v.contains(x)){
x=(int)(Math.random()*10)+1;
            }
           // System.out.print(x+" ");
            v.add(x);

//System.out.println((int)Math.random()*10);
        }
       // System.out.println(v);
        v.toArray(genotype);

      //  System.out.println(v);
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
      //  genotype = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        // END OF YOUR CODE
        
        return genotype;
    }
    
    // swaps 2 genes in the genotype1
    // the swap happens with probability p, so if p = 0.8
    // then 8 out of 10 times this method is called, a swap happens
    public static Integer[] swapMutate(Integer[] genotype, double p)
    {
        if (Math.random() < p) {
            int y=(int)(Math.random()*10);
            int x=(int)(Math.random()*10);
            while(x==y){//in case both position are at same index
            x=(int)(Math.random()*10);
                    }
                    int t=genotype[y];
                    genotype[y]=genotype[x];
genotype[x]=t;
        }

        
        return genotype;
    }
    
    // creates 2 child genotypes using the 'cut-and-crossfill' method
    public static Integer[][] cutAndCrossfill(Integer[] parent0, Integer[] parent1)
    {
        ArrayList<Integer> child1=new ArrayList<>();
        ArrayList<Integer> child2=new ArrayList<>();

        Integer [][] children = new Integer [2][boardSize];
        for (int i = 0; i < 5; i++) {
         //   children[0][i]=parent0[i];
            child1.add(parent0[i]);
           // children[1][i]=parent1[i];
            child2.add(parent1[i]);

        }
        for (int i = 5; i < 10; i++) {
            if(!child1.contains(parent1[i]))
child1.add(parent1[i]);
if(!child2.contains(parent0[i]))
child2.add(parent0[i]);
        }
        for (int i = 0; i <5; i++) {
            if(!child1.contains(parent1[i]))
child1.add(parent1[i]);
if(!child2.contains(parent0[i]))
child2.add(parent0[i]);
        }
        child1.toArray(children[0]);
        child2.toArray(children[1]);
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        //children[0] = new Integer[]{ 5, 4, 2, 6, 8, 1, 10, 9, 7, 3 };
      //  children[1] = new Integer[]{ 6, 2, 5, 7, 3, 9, 1, 10, 4, 8 };
        // END OF YOUR CODE
        
        return children;
    }
    
    // calculates the fitness of an individual
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
