package mummymaze;

public class Enemies extends Base {
    private boolean isDead;
    public Enemies(int x, int y, char character, char[][] matrix) {
        super(x, y, character, matrix);
        isDead = false;
    }

    //is a enemy is that position

    public boolean isEnemy(int x, int y){
        return (this.x == x && this.y == y);
    }

    public char[][] moveUp() {

        if (matrix[x - 2][y] == 'M' || matrix[x - 2][y] == 'E' || matrix[x - 2][y] == 'V') {
            matrix[x][y] = '.';
            x -= 2;
            isDead = true;
        }else {
            matrix[x][y] = '.';
            x -= 2;
            matrix[x][y] = character;
        }
        return matrix;
    }

    //return value isDead
    public boolean isDead() {
        return isDead;
    }

    //set value isDead
    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public char[][] moveRight() {

        if (matrix[x][y + 2] == 'M' || matrix[x][y + 2] == 'E' || matrix[x][y + 2] == 'V') {
            isDead = true;
            matrix[x][y] = '.';
            y += 2;
        }else {
            matrix[x][y] = '.';
            y += 2;
            matrix[x][y] = character;
        }
        return matrix;
    }

    public char[][] moveDown() {
        if (matrix[x + 2][y] == 'M' || matrix[x + 2][y] == 'E' || matrix[x + 2][y] == 'V') {
            matrix[x][y] = '.';
            x += 2;
            isDead = true;
        } else {
            matrix[x][y] = '.';
            x += 2;
        matrix[x][y] = character;
         }
        return matrix;
    }

    public char[][] moveLeft() {
        if (matrix[x][y - 2] == 'M' || matrix[x][y - 2] == 'E' || matrix[x][y - 2] == 'V') {
            isDead = true;
            matrix[x][y] = '.';
            y -= 2;
        }else{
            matrix[x][y] = '.';
            y -= 2;
            matrix[x][y] = character;
        }

        return matrix;
    }

    public boolean canMoveUp() {
        //Verificar este codigo

        if (matrix[x - 2][y] != 'E' || matrix[x - 2][y] != 'A' || matrix[x - 2][y] != 'M' || matrix[x - 2][y] != 'V')  {
            return matrix[x - 1][y] == ' ' || matrix[x - 1][y] == '_';
        }
        else{
            return false;
        }


    }

    public boolean canMoveDown() {
        //Verificar este codigo

        if (matrix[x + 2][y] != 'E' || matrix[x + 2][y] != 'A' || matrix[x + 2][y] != 'M' || matrix[x + 2][y] != 'V') {
            return matrix[x + 1][y] == ' ' || matrix[x + 1][y] == '_';
        }
        else{
            return false;
        }
    }

     public boolean canMoveLeft() {
         //Verificar este codigo
         if (matrix[x][y - 2] != 'E' || matrix[x][y - 2] != 'A' || matrix[x][y - 2] != 'M' || matrix[x][y - 2] != 'V') {
             return matrix[x][y - 1] == ' ' || matrix[x][y - 1] == ')';
         } else {
             return false;
         }
     }

     public boolean canMoveRight() {
        //Verificar este codigo
            if (matrix[x][y + 2] != 'E' || matrix[x][y + 2] != 'A' || matrix[x][y + 2] != 'M' || matrix[x][y + 2] != 'V') {
                return matrix[x][y + 1] == ' ' || matrix[x][y + 1] == ')';
            } else {
                return false;
            }
     }

     public void move(int collumnHero, int rowHero){}

}




