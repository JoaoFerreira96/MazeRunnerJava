package mummymaze;

public class Trap extends Base{
    public Trap(int x, int y, char[][] matrix) {
        super(x, y, 'A', matrix);
    }
    public boolean isTrap(int x, int y){
        return (this.x == x && this.y == y);
    }

}
