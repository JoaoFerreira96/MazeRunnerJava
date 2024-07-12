package mummymaze;

public class Mummy extends Enemies {



    public Mummy(int x, int y, char[][] maze) {
        super(x, y, 'M', maze);
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
                    } else if (collumnHero < this.getY()) {
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

                    else if (this.getX() == rowHero && this.getY() == collumnHero) {
                        setDead(true);
                    }
        }
    }
}

