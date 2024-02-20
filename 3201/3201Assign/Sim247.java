import java.util.Random;
// Sim247 is the main class accepting arguments when program was excecuted from console
//and creating object of view class

public class Sim247{ 
    private  Random random = new Random();

    public static void main(String args[]){
        int i1=Integer.parseInt(args[0]);//accepting number of itertions
        String m=args[1];// accpeting R, M ,T
      int board[][]=new int[100][100];// creating initial config for turtle
      board  [1][2] =1;
      board   [1][3]=1;
      board   [1][4]=1;
      board   [1][12]=1;
      board   [2][2]=1;
      board   [2][3]=1;
      board   [2][6]=1;
      board   [2][8]=1;
      board    [2][9]=1;
      board  [2][11]=1;
      board   [2][12]=1;
      board  [3][4]=1;
      board   [3][5]=1;
      board   [3][6]=1;
      board   [3][11]=1;
      board   [4][2]=1;
      board   [4][5]=1;
      board   [4][7]=1;
      board   [4][11]=1;
      board   [5][1]=1;
      board  [5][6]=1;
      board [5][11]=1;
      board  [6][1]=1;
      board  [6][6]=1;
      board [6][11]   =1;
      board  [7][2]=1;
      board   [7][5]=1;
    board  [7][7]=1;
    board  [7][11]=1;
    board  [8][4]=1;
    board  [8][5]=1;
    board  [8][6] =1;
    board  [8][11]=1;
    board   [9][2]=1;
    board   [9][3]=1;
    board   [9][6]=1;
    board  [9][9]=1;
    board  [9][11]=1;
    board  [9][12]=1;
    board  [9][8]=1;

    board [10][2]=1;
    board [10][3]=1;
    board [10][4]=1;
    board [10][12]=1;
    Sim247 ob1=new Sim247();
 int b[][]=new int[100][100];
 for (int i = 0; i < b.length; i++) {// Random number generator
    for (int j = 0; j < b.length; j++) {
        b[i][j]= ob1.next();
    }
 }
 View ob;
 if(m.equals("T") || m.equals("t"))// for turtle
ob=new View(100, 100,i1,board);
else if(m.equals("R") || m.equals("r")) {//for random
    ob=new View(100, 100,i1,b);
}else if(m.equals("M") || m.equals("m")){// for maze
    int[][] xboard=new int[100][100];// creating initial config for maze
    xboard[1][5]=1;
    xboard[1][6]=1;
    xboard[2][3]=1;
    xboard[2][5]=1;
    xboard[3][2]=1;
    xboard[3][8]=1;
   xboard[4][3]=1;
   xboard[4][7]=1;
    xboard[4][8]=1;
    xboard[6][5]=1;
    xboard[6][7]=1;
    xboard[7][6]=1;

    ob=new View(100, 100,i1,xboard);}

}
     

int next() {
    if (random.nextBoolean()) {
      return 1;
    } else {
      return 0;
    }
  }  
}