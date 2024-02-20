public class lcs2 {
    public static void main(String args[]){
        String s1="abcab";
        String s="aecb";
        
        int dp[][]=new int[s.length()+1][s1.length()+1];
        for (int i = 1; i <=s.length(); i++) {
 for (int j = 1; j <= s1.length(); j++) {
   if((s.charAt(i-1)+"").equals(""+s1.charAt(j-1))){
    dp[i][j]=dp[i-1][j-1]+1;
   }
   else{
dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
   }
   System.out.print(dp[i][j]+" ");
 }
 System.out.println();   
}

    }
   
}
