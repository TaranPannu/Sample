import java.io.*;
import java.util.*;

public class StateChangeStack
{
    private Stack changeStack;

    public StateChangeStack()
    {
    	changeStack = new Stack();
		clear();
    }

    public void clear()
    {
    	changeStack.clear();
    }

    public boolean empty()
    {
		return changeStack.empty();
    }

    public void push( int x, int y, int nextState)
    {
    	changeStack.push( new StateChange( x, y, nextState));
    }

    public StateChange pop()
    {
    	if ( changeStack.empty() )
    	{
    		return null;
    	}
		else
		{
			return (StateChange) changeStack.pop();
		}
    }
}
