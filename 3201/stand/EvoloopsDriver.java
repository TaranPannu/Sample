/**
 * Sayama's Evoloops program split into separate class files with code commented
 * and indented for ease of navigation and understanding
 *
 * Sayama's original overview comments are retained intact in the Evoloops class
 *
 * Any code I have written / amended is clearly commented to show its extent
 * and purpose
 *
 * CODE ALTERATION
 * note: 'main' method removed from orginal Evoloops class and replaced with
 * startUp method, taking parameters for the transition rule filename, a
 * boolean for whether or not dissolve state is to be implemented and the file
 * name for the ancestor loop
 *
 * this has allowed the key parameters for the program to be set in this driver
 *
 * M.Hatcher
 */

public class EvoloopsDriver
{
	public static void main( String args[])
	{
		Evoloops.startUp( "rule evolvable", true, "left/10.img");
    }
}
