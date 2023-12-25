import java.io.*;
import java.util.*;

public class CellUpdateStack
{
    private Stack updateStack;
    private boolean [][] inStack;
    private int spacex, spacey;

    public CellUpdateStack(int x, int y)
    {
    	updateStack = new Stack();
		inStack = new boolean [x][y];
		spacex = x;
		spacey = y;
		clear();
    }

    public void clear()
    {
    	updateStack.clear();
		for (int i = 0; i < spacex; i ++)
		{
			for (int j = 0; j < spacey; j ++)
			{
				inStack[i][j] = false;
			}
		}		
    }

    public boolean empty()
    {
    	return updateStack.empty();
    }

    public void push( int x, int y)
    {
    	if (!inStack [x][y])
    	{
    		inStack [x][y] = true;
	    	updateStack.push( new CellUpdate( x, y));
		}
    }

    public CellUpdate pop()
    {
    	
		CellUpdate cu;
		
		if ( updateStack.empty() )
		{
			return null;
		}
		else
		{
	    	cu = ( CellUpdate) updateStack.pop();
	    	inStack [cu.x][cu.y] = false;
	    	return cu;
		}
    }
}