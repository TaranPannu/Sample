import java.util.Arrays;

public class lcs {
   static int ans=0;

    public static void main(String args[]) {
String s="abc";
String s1="abdc";

int dp[][]=new int[s.length()+1][s1.length()+1];
for (int i = 1; i <=s.length(); i++) {
    for (int j = 1; j <=s1.length(); j++) {
        dp[i][j]=-1;
        System.out.print(dp[i][j]+" ");
    }
    System.out.println();
}
System.out.println(xx(s.length(), s1.length(), s,s1,dp));   

}
    static int xx(int n,int m,String s1,String s2,int dp[][]){
        if(n==0 || m==0)
{       //System.out.println(ans);
     
    return 0;
}   
if(dp[n][m]!=-1){
    return dp[n][m];
}     
        if((s1.charAt(s1.length()-1)+"").equals(s2.charAt(s2.length()-1)+""))
{      return  dp[n][m]=1+xx(n-1, m-1, s1.substring(0, s1.length()-1), s2.substring(0, s2.length()-1),dp);
//System.out.println(ans+"mk");
}      else{
    return dp[n][m]=Math.max( xx(n-1, m, s1.substring(0, s1.length()-1), s2,dp), xx(n, m-1, s1, s2.substring(0, s2.length()-1),dp));
     
      }

    }
}
