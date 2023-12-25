import java.util.Random;
// Sim247 is the main class accepting arguments when program was excecuted from console
//and creating object of view class
//as mentioned in the assignment description cells are 20*20..no matter frame size is different
public class Sim247{ 
    private  Random random = new Random();

    public static void main(String args[]){
        int i1=5;//Integer.parseInt(args[0]);//accepting number of itertions
        String m="t";//args[1];// accpeting R, M ,T
      int board[][]=new int[20][20];// creating initial config for turtle
      board  [2][2] =1;
      board   [2][3]=1;
      board   [2][4]=1;
      board   [2][12]=1;
      board   [3][2]=1;
      board   [3][3]=1;
      board   [3][6]=1;
      board   [3][8]=1;
      board    [3][9]=1;
      board  [3][11]=1;
      board   [3][12]=1;
      board  [4][4]=1;
      board   [4][5]=1;
      board   [4][6]=1;
      board   [4][11]=1;
      board   [5][2]=1;
      board   [5][5]=1;
      board   [5][7]=1;
      board   [5][11]=1;
      board   [6][1]=1;
      board  [6][6]=1;
      board [6][11]=1;
      board  [7][1]=1;
      board  [7][6]=1;
      board [7][11]   =1;
      board  [8][2]=1;
      board   [8][5]=1;
    board  [8][7]=1;
    board  [8][11]=1;
    board  [9][4]=1;
    board  [9][5]=1;
    board  [9][6] =1;
    board  [9][11]=1;
    board   [10][2]=1;
    board   [10][3]=1;
    board   [10][6]=1;
    board  [10][9]=1;
    board  [10][11]=1;
    board  [10][12]=1;
    board  [10][8]=1;

    board [11][2]=1;
    board [11][3]=1;
    board [11][4]=1;
    board [11][12]=1;
    Sim247 ob1=new Sim247();
 int b[][]=new int[20][20];
 for (int i = 0; i < b.length; i++) {// Random number generator
    for (int j = 0; j < b.length; j++) {
        b[i][j]= ob1.next();
    }
 }
 View ob;
 if(m.equals("T") ||m.equals("t")  )// for turtle
ob=new View(20, 20,i1,board);
else if(m.equals("R") || m.equals("r") ) {//for random
    ob=new View(20, 20,i1,b);
}else if(m.equals("M") || m.equals("m") ){// for maze
    int[][] xboard=new int[20][20];// creating initial config for maze
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

    ob=new View(20, 20,i1,xboard);}

}
     

int next() {
    if (random.nextBoolean()) {
      return 1;
    } else {
      return 0;
    }
  }  
}