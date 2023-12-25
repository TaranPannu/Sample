import java.util.ArrayList;
import java.util.*;

public class DP{
    public static void main(String args[]) {
        int m[]={7,5,1};
        ArrayList v=new ArrayList<>();
        Map<Integer,Integer> v1=new HashMap<>();
        System.out.println(xx(10,m,v1));
        System.out.println(v1);
    }

    static int xx(int sum,int m[],Map<Integer,Integer> m1){
        if(sum==0){
             return 0;
        };
        int ans=Integer.MAX_VALUE;
for (int i = 0; i < m.length; i++) {
    int subans=0;
    if(sum-m[i]<0)continue;
if(m1.containsKey(sum-m[i])){
subans=m1.get(sum-m[i]);
}else{
  subans=xx(sum-m[i],m,m1);

   }
   if(subans!=Integer.MAX_VALUE && ans>subans+1)
   ans=subans+1;

}
m1.put(sum, ans);

return ans;    }
}