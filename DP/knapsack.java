public class knapsack {
    public static void main(String args[]){
int w[]={4,2,3,5,2,1};
int v[]={5,4,4,4,1,3};
System.out.println(xx(w, v, 6, 0));    }
    static int xx(int w[],int v[],int C,int i){//it will return profit
        if(i==w.length || C==0){
         
            return 0;
        }
        if(w[i]>C)
        return xx(w, v, C,i+1);
     int x=v[i]+xx(w, v, C-w[i],i+1);
int x1=xx(w, v, C,i+1);

    return Math.max(x,x1);
    }
}
