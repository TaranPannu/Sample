public class mcp{
    public static void main(String args[]) {
        int m[]={2 ,40 ,2 ,40 ,5};
       System.out.println( matrixMultiplication(5,m));

    }
    static int matrixMultiplication(int N,int arr[])
    {
int dp[][]=new int[N][N];
int row ,col;
for(int len=2;len<N;len++){
    for(row=0,col=len;row<(N-len);row++,col++){
        int left=0,down=0;
        dp[row][col]=Integer.MAX_VALUE;
        for(int k=row+1;k<col;k++){
            dp[row][col]=Math.min(dp[row][col],dp[row][k]+dp[k][col]+arr[row]*arr[k]*arr[col]);
        }
    }
}
return dp[0][N-1];
}
}