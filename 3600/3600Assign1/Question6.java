public class Question6 {
    public static void main(String args[]){
       String X= " GAAGCCTA"; 
       String Y=" TATCGA";
       int m=X.length();
       int n=Y.length();
       int c[][]=new int [m+1][n+1];
       Character b[][]=new Character[m+1][n+1];
for (int i = 1; i <m; i++) {
  for (int j = 1; j <n; j++) {
    if((X.charAt(i)+"").equals(Y.charAt(j)+"")){
        c[i][j]=c[i-1][j-1]+1;
        b[i][j]='D';
    }
    else if(c[i-1][j]>=c[i][j-1]){
        c[i][j]=c[i-1][j];
        b[i][j]='U';
    }
    else{
        c[i][j]=c[i][j-1];
        b[i][j]='L';
    }
  }  
}

dis(c,b,m,n);
System.out.print("Longest Common Subs: ");
LCS(b,X,m-1,n-1);
    }
    static void dis(int c[][],Character b[][],int m,int n){
        for (int i = 1; i <m; i++) {
            for (int j = 1; j <n; j++) {
             System.out.print(b[i][j]);
             
            } 
            System.out.println();
         }
    }
 static  void LCS(Character b[][],String X,int i ,int j){
   // System.out.println("hh"+b[i][j]+" "+i+" "+j);
if(i==0 || j==0)
return ;
if((b[i][j]+"").equals("D")){
    LCS(b,X,i-1,j-1);
    System.out.print(X.charAt(i)+" ");
}
else if((b[i][j]+"").equals("U")){
    LCS(b,X,i-1,j);
}
else if((b[i][j]+"").equals("L")){
    LCS(b,X,i,j-1);
}
    }
}
