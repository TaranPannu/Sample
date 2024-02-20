import javax.swing.JButton;
import javax.swing.JFrame;

public class Frome1{
    public static void main (String args[]){
JFrame f=new JFrame("this");

JButton b=new JButton("text");
b.addActionListener(e ->{
    f.setVisible(false);
    JFrame o = new JFrame("new");
    o.getContentPane().add(b);
    o.pack();
    o.setVisible(true);
});
f.getContentPane().add(b);
f.pack();
f.setVisible(true);


    }
}