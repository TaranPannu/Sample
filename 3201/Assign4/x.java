import java.util.ArrayList;
import java.util.*;

public class x {
    public static void main(String args[]){
   ArrayList<String> v=new ArrayList<>();
   ArrayList<String> v1=new ArrayList<>();
   Set<Integer[][]> m1=new HashSet<>();
v.add("null");
v1.add("null");
System.out.println(v.equals(v1));

    }
    public static Integer[] inversionMutate(int[] genotype, double p)
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
            System.out.print(temp[i]+" ");
         }
         for (int i1 = y; i1>=x; i1--) {
            temp[i]=genotype[i1];
            System.out.print(temp[i]+" ");

            i++;

         }
         if((y+1)<10)
         for (int i2 = y+1; i2 < temp.length; i2++) {
            temp[i2]=genotype[i2];
            System.out.print(temp[i2]+" ");

         }
        }
System.out.println();
     
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE: (pretends to invert first test genotype)
    //    genotype = new Integer[]{ 1, 2, 3, 8, 7, 6, 5, 4, 9, 10 };
        // END OF YOUR CODE
        
        return temp;
    }
}
