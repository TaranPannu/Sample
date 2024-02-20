public class DiceCombo {
    private int x;
    private int y;
    public DiceCombo(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int sum(){
        return x + y;
    }
    public Boolean equalTo(DiceCombo comp){
        return ((this.x == comp.getX()) & (this.y == comp.getY())) | ((this.y == comp.getX()) & (this.x == comp.getY()));
    }

    public int getX(){return x;}
    public int getY(){return y;}
}
