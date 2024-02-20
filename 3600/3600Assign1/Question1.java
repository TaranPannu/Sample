/////////////////////////////////////////////////////////////////
//  CS 3600 (Winter 2023), Assignment #1, Question #1          //
//  Program File Name: Question1.java                          //
//       Student Name: Taranpreet Singh                        //
//         Login Name: taranpreets                             //
//              MUN #: 202128856                               //
/////////////////////////////////////////////////////////////////


import java.util.*;

public class Question1{
    static boolean x=false;
    static int count=0;

    public static void main(String args[]){
        Map<Integer,Set<Integer>> v=new HashMap<>();

Scanner reader = new Scanner(System.in);  // Reading from System.in
String n1[]=reader.nextLine().split(" ");
int n = Integer.parseInt(n1[0]);
int m = Integer.parseInt(n1[1]);

for (int i = 0; i <m; i++) {
Set<Integer> v1=new HashSet<>();
String key[]= reader.nextLine().split(" ");
for (int j = 2; j < key.length; j++) {
    //System.out.print(key[j]+" ");
    v1.add(Integer.parseInt(key[j]));
}
v.put(Integer.parseInt(key[1].substring(0,key[1].indexOf(":"))),v1);


}
Set<Integer> U=new HashSet<Integer>();
for (int i = 1; i <=n; i++) {
    U.add(i);

}



Set<ArrayList<Integer>> v12=new HashSet<ArrayList<Integer>>() ;

       // System.out.println(v);
for (int i = 1; i <=v.size(); i++) {
    Set<Integer> U1=new HashSet<Integer>();
    if(!x)
  xx(0,i,v,U1,U,new ArrayList<Integer>(),v12);
}
if(count<=0)
System.out.println("No solutions");
else
System.out.println(count+" optimal solution(s)");
    }

    static  void xx(int p,int i, Map<Integer, Set<Integer>> v, Set<Integer> C, Set<Integer> U,ArrayList<Integer> sol, Set<ArrayList<Integer>> v12 ) 
     {
        
if(i==p){
if(C.equals(U))
{
    if(v12.size()==0)
    System.out.println("Optimal value = "+sol.size());

    Collections.sort(sol);
    if(v12.add(sol))
    {count++;System.out.println(count+": "+sol);
    }


x=true;
return;}
}
for (int j = 1+p; j <=v.size(); j++) {
Set<Integer> c1=new HashSet<>();
c1.addAll(C);

   C.addAll(v.get(j));
sol.add(j);

xx(p+1,i, v, C, U,sol,v12);
sol.remove(Integer.valueOf(j));
C.clear();// backtracking is happening here
C.addAll(c1);// backtracking is happening here

}
    }
}