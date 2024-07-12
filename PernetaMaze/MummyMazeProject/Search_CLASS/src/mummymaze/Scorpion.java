package mummymaze;

public class Scorpion extends Enemies {

    public Scorpion(int x, int y, char[][] maze) {
        super(x, y, 'E', maze);
    }

    @Override
    public void move(int collumnHero, int rowHero) {
        if (!isDead()) {
            if (collumnHero == this.getY()) {
                if (rowHero < this.getX()) {
                    if (canMoveUp()) {
                        this.moveUp();
                    }
                } else if (rowHero > this.getX()){
                    if (canMoveDown()) {
                        this.moveDown();
                    }
                }
            } else if (collumnHero > this.getY()) {
                if (canMoveRight()) {
                    this.moveRight();
                } else {
                    if (rowHero < this.getX()) {
                        if (canMoveUp()) {
                            this.moveUp();
                        }
                    } else if (rowHero > this.getX()){
                        if (canMoveDown()) {
                            this.moveDown();
                        }
                    }
                }
            } else {
                if (canMoveLeft()) {
                    this.moveLeft();
                } else {
                    if (rowHero < this.getX()) {
                        if (canMoveUp()) {
                            this.moveUp();
                        }
                    } else if (rowHero > this.getX()){

                        if (canMoveDown()) {
                            this.moveDown();
                        }
                    }
                }
            }

            if (this.getX() == rowHero && this.getY() == collumnHero) {
                setDead(true);
            }
        }
    }

}

