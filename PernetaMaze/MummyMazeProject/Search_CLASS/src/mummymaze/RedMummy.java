package mummymaze;

public class RedMummy extends Enemies {



    public RedMummy(int x, int y, char[][] maze) {
        super(x, y, 'V', maze);
    }

    @Override
    public void move(int collumnHero, int rowHero) {
        if (!isDead()) {

                if(collumnHero == this.getY() && rowHero==this.getX()){
                    this.setX(rowHero);
                    this.setY(collumnHero);
                }else{
                    if (rowHero == this.getX()) {
                        if (collumnHero < this.getY()) {
                            if (canMoveLeft()) {
                                this.moveLeft();
                            }
                        } else if (collumnHero > this.getY()) {
                            if (canMoveRight()) {
                                this.moveRight();
                            }
                        }
                    } else if (rowHero < this.getX()) {
                        if (canMoveUp()) {
                            this.moveUp();
                        } else {
                            if (collumnHero < this.getY()) {
                                if (canMoveLeft()) {
                                    this.moveLeft();
                                }
                            } else if (collumnHero > this.getY()) {
                                if (canMoveRight()) {
                                    this.moveRight();
                                }
                            }
                        }
                    }else{
                        if (canMoveDown()) {
                            this.moveDown();
                        }else{
                            if (collumnHero < this.getY()) {
                                if (canMoveLeft()) {
                                    this.moveLeft();
                                }
                            } else if (collumnHero > this.getY()){
                                if (canMoveRight()) {
                                    this.moveRight();
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



}
