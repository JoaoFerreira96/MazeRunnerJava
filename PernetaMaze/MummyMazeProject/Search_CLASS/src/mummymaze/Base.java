package mummymaze;

public class Base {
    protected int x;
    protected int y;
    protected char character;
    char[][] matrix;

    public Base(int x, int y, char character, char[][] matrix) {
        this.x = x;
        this.y = y;
        this.character = character;
        this.matrix = matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
