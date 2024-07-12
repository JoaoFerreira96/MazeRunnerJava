package mummymaze;

public class Porta{

    private int x;
    private int y;
    public Porta(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean isDoor(int x, int y){
        return (this.x == x && this.y == y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
