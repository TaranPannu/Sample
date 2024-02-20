import java.io.*;
import java.util.*;

public class DeathEventStack
{
    private Stack eventStack;

    public DeathEventStack()
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

    public void push( int x, int y)
    {
    	eventStack.push( new DeathEvent( x, y));
    }

    public DeathEvent pop()
    {
    	if ( eventStack.empty() )
    	{
    		return null;
    	}
    	else
    	{
    		return (DeathEvent) eventStack.pop();
    	}
    }
}