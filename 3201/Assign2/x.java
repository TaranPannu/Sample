public class x {
   public static void main(String args[]){
    System.out.println((int)Math.random()*10);
    System.out.println();
    System.out.println();
    System.out.println();
    int m[]={ 9, 5, 6, 10, 8, 7, 1, 3, 2, 4 };
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
System.out.println(count);
   } 
}
