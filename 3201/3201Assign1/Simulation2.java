import java.net.SocketPermission;

import javax.swing.Timer;

public class Simulation2 {
    int width;
    int height;
    int[][] board;
    Grid[][] gridSquares;
    public Simulation2(int width, int height, int[][] board,Grid[][] gridSquares) {
        this.width = width;
        this.height = height;
        this.board = board;
        this.gridSquares=gridSquares;
       
    }
   
    void SimulateOneStep1(){// Simulating the process by one step
        int c=0;
        int board2[][]=new int [width][height];//creating 2 dimensional array
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board.length; j++) {
            c=count_Alive(i,j);
           /*  if ((board[i][j] == 1) && (c < 2))
            {board2[i][j] = 0;
                gridSquares[i][j].colr("0");

            }
 
                // Cell dies due to over population
                else if ((board[i][j] == 1) && (c > 3))
{                board2[i][j] = 0;
    gridSquares[i][j].colr("0");

}
                // A new cell is born
                else if ((board[i][j] == 0) && (c == 3))
               { board2[i][j] = 1;
                gridSquares[i][j].colr("1");
            }
 
                // Remains the same
                else
{
    board2[i][j] = board[i][j];
if(board2[i][j]==1)
{gridSquares[i][j].colr("1");
System.out.println(c+" . "+board2[i][j]);
}else
gridSquares[i][j].colr("0");


}    */         if(board[i][j]==0){//to check id neighbour is dead make himalive
                //c is the count of alive neighbour
                 if(board[i][j]==0 && c==3)
    {            board2[i][j]=1;
      // gridSquares[i][j].colr("1");//changing color in grid
    }
    //else
    //gridSquares[i][j].colr("0");

}

    if(board[i][j]==1)
    {    
       // c=count_Alive(i,j);
        if(c<2 || c>3)
{ 
       board2[i][j]=0;
 //  gridSquares[i][j].colr("0");
} 
else if(c==2 || c==3)
{   
     board2[i][j]=1;
  //  gridSquares[i][j].colr("1");
} 
    }  }
    }
    this.board=board2.clone(); //assigning new board to new boardv
    display();
     }

 
  public int Check(int x, int y) {//this function will basically wrap around the grid topmost row to bottom most rpw
    //left with right
  
    if(x<0)
    x= x+this.width;
    if(x>=width)
   x=(x-this.width);
  
    if(y<0)
    y= y+this.height;
    if(y>=height)
    y= (y-this.height);
 
    return this.board[x][y];
}
  public int count_Alive(int x, int y) {
    int C =0;

    C += Check(x - 1, y - 1);
//    System.out.println(count);
    C += Check(x, y - 1);
  //  System.out.println(count);

    C += Check(x + 1, y - 1);
    //System.out.println(count);

    C += Check(x - 1, y);
    //System.out.println(count);

    C += Check(x + 1, y);
    //System.out.println(count);

    C += Check(x - 1, y + 1);
    //System.out.println(count);

    C += Check(x, y + 1);
    //System.out.println(count);

    C += Check(x + 1, y + 1);
    //System.out.println(count);

    return C;
}
   
void display(){
    gridSquares[2][2].colr("1");
    gridSquares[2][3].colr("1");

    for (int i = 0; i < board.length; i++) {
     for (int j = 0; j < board.length; j++) {
        if(board[i][j]==1)
{       // gridSquares[i][j].colr("1");
System.out.print("1");
} 
       else
        {
             
    System.out.print(" ");
}    }
    System.out.println();  
    }
}
}
