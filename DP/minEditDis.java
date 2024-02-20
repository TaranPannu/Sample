import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;

public class minEditDis {    
        static Map<String,Integer> dp=new HashMap<>();
             public static void main(String args[]) {
        System.out.println(xx("dogi","dig"));
    }
    static int xx(String s1,String s2){
        if(s1.length()==0)
{   
        return s2.length();
}        if(s2.length()==0)
{  
          return s1.length();
}
if(dp.containsKey(s1+" "+s2))
return dp.get(s1+" "+s2);       
 if(s1.charAt(s1.length()-1)==s2.charAt(s2.length()-1))
        return xx(s1.substring(0,s1.length()-1),s2.substring(0,s2.length()-1));
        int insert=1+xx(s1+s2.charAt(s2.length()-1),s2);
        int delete=1+xx(s1.substring(0,s1.length()-1),s2);
        int replace=1+xx(s1.substring(0,s1.length()-1)+s2.charAt(s2.length()-1),s2);
        int result= Math.min(Math.min(insert,delete),replace);
        dp.put(s1+" "+s2,result);
        return result;
    }
}

