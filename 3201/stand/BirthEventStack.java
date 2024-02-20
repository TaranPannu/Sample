import java.io.*;
import java.util.*;

public class BirthEventStack
{
    private Stack eventStack;

    public BirthEventStack()
    {
    	eventStack = new Stack();
		clear();
    }

    public void clear()
    {
    	eventStack.clear();
    }

    public boolean empty()
    {
    	return eventStack.empty();
    }

    public void push( int x, int y, int dx, int dy)
    {
    	eventStack.push( new BirthEvent( x, y, dx, dy));
    }

    public BirthEvent pop()
    {
    	if ( eventStack.empty() )
    	{
    		return null;
    	}
    	else
    	{
    		return (BirthEvent) eventStack.pop();
    	}
    }
}