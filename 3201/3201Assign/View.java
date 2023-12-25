import javax.swing.*;
import java.awt.*;
import java.sql.Time;
// view class is to show frame and for creating grid
public class View {

Simulation2 ob;
    public View(int rows,int cols,int i,int[][] board) {
        Grid[][] gridSquares = new Grid[rows][cols];
      
        	// first create the panels
            JFrame frame=new JFrame("");
            JPanel contentPane = (JPanel) frame.getContentPane();
contentPane.setLayout(new FlowLayout());
            frame.setSize(600,400);
		
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setSize(600,400);
		GridLayout v=new GridLayout(rows,cols);
		//v.setHgap(2);
		//v.setVgap(2);
       
		bottomPanel.setLayout(v);
		for ( int x = 0; x < cols; x ++)
		{
			for ( int y = 0; y < rows; y ++)
			{
				gridSquares[x][y] = new Grid(x, y);
				gridSquares[x][y].setSize(1, 1);
                gridSquares[x][y].setColor();
				bottomPanel.add(gridSquares[x][y]);
			}
		}
        ob=new Simulation2(rows, cols, board,gridSquares);
   

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(null);
     contentPane.add(bottomPanel,BorderLayout.CENTER);
        for (int j = 0; j <i; j++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            System.out.println(e);
            }
            ob.SimulateOneStep1();

            frame.setVisible(true);
        }
    }
   
   
}
