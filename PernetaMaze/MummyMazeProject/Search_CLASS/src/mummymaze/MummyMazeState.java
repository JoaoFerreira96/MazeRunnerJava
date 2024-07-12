package mummymaze;

import agent.Action;
import agent.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MummyMazeState extends State implements Cloneable {
    private final char[][] matrix;
    public static int lineSaida;
    public static int columnSaida;
    private int lineHero;
    private int columnHero;

    private boolean heroDead = false;

    private LinkedList<Enemies> enemies;
    public static LinkedList<Porta> doors = new LinkedList<>();
    public static LinkedList<Trap> traps = new LinkedList<>();

    public static int lineKey;

    public static int columnKey;

    private static boolean keyActive = true;

    public MummyMazeState(char[][] matrix) {
        this.matrix = new char[matrix.length][matrix.length];
        enemies = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 'H') {
                    lineHero = i;
                    columnHero = j;
                }
                if (this.matrix[i][j] == 'M') {
                    enemies.add(new Mummy(i, j, matrix));
                }
                if (this.matrix[i][j] == 'E') {
                    enemies.add(new Scorpion(i, j, matrix));
                }
                if (this.matrix[i][j] == 'V') {
                    enemies.add(new RedMummy(i, j, matrix));
                }

            }
        }


        if (lineHero == 0 && columnHero == 0) {
            heroDead = true;
        }
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveUp() {
        if (lineHero == 1 && matrix[lineHero - 1][columnHero] == 'S') {
            return true;
        }

        return (matrix[lineHero - 1][columnHero] == ' ' || matrix[lineHero - 1][columnHero] == '_') && matrix[lineHero - 1][columnHero] != '=' && lineHero != 1 && (matrix[lineHero - 2][columnHero] == '.' || matrix[lineHero - 2][columnHero] == 'C');
    }

    public boolean canMoveRight() {
        if (columnHero == 11 && matrix[lineHero][columnHero + 1] == 'S') {
            return true;
        }

        return (matrix[lineHero][columnHero + 1] == ' ' || matrix[lineHero][columnHero + 1] == ')') && matrix[lineHero][columnHero + 1] != '"' && columnHero != 11 && (matrix[lineHero][columnHero + 2] == '.' || matrix[lineHero][columnHero + 2] == 'C');
    }

    public boolean canMoveDown() {
        if (lineHero == 11 && matrix[lineHero + 1][columnHero] == 'S') {
            return true;
        }

        return (matrix[lineHero + 1][columnHero] == ' ' || matrix[lineHero + 1][columnHero] == '_') && matrix[lineHero + 1][columnHero] != '=' && lineHero != 11 && (matrix[lineHero + 2][columnHero] == '.' || matrix[lineHero + 2][columnHero] == 'C');
    }

    public boolean canMoveLeft() {
        if (columnHero == 1 && matrix[lineHero][columnHero - 1] == 'S') {
            return true;
        }

        return (matrix[lineHero][columnHero - 1] == ' ' || matrix[lineHero][columnHero - 1] == ')') && matrix[lineHero][columnHero - 1] != '"' && columnHero != 1 && (matrix[lineHero][columnHero - 2] == '.' || matrix[lineHero][columnHero - 2] == 'C');
    }

    public boolean canStay() {
        return true;
    }

    /*
     * In the next four methods we don't verify if the actions are valid.
     * Doing the verification in these methods would imply that a clone of the
     * state was created whether the operation could be executed or not.
     */
    public void moveUp() {

            if (lineHero == 1) {
                matrix[lineHero][columnHero] = '.';
                lineHero--;
                matrix[lineHero][columnHero] = 'H';

                return;
            } else {
                matrix[lineHero][columnHero] = '.';
                lineHero -= 2;
            }
            if (matrix[lineHero][columnHero] == 'C') {
                if (!keyActive) {
                    for (Porta p : doors) {
                        mudarEstado(p);

                    }
                }
                keyActive = true;
            }

            for (Enemies e :
                    enemies) {
                if (e.isEnemy(lineHero, columnHero)) {
                    heroDead = true;
                }
            }


            matrix[lineHero][columnHero] = 'H';


            trapsandKeys();
            enemyMove();

        System.out.println(toString(matrix));

    }

    public void moveRight() {

            if (columnHero == 11) {
                matrix[lineHero][columnHero] = '.';
                columnHero++;
                matrix[lineHero][columnHero] = 'H';
                return;
            } else {
                matrix[lineHero][columnHero] = '.';
                columnHero += 2;
            }
            if (matrix[lineHero][columnHero] == 'C') {
                if (!keyActive) {
                    for (Porta p : doors) {

                        mudarEstado(p);

                    }
                }
                keyActive = true;
            }

            for (Enemies e :
                    enemies) {
                if (e.isEnemy(lineHero, columnHero)) {
                    heroDead = true;
                }
            }
            matrix[lineHero][columnHero] = 'H';


            trapsandKeys();
            enemyMove();

        System.out.println(toString(matrix));

    }

    public void dontMove() {

            if (matrix[lineHero][columnHero] == 'C') {
                if (!keyActive) {
                    for (Porta p : doors) {
                        mudarEstado(p);
                    }
                }
                keyActive = true;
            }

            for (Enemies e :
                    enemies) {
                if (e.isEnemy(lineHero, columnHero)) {
                    heroDead = true;
                }
            }
            matrix[lineHero][columnHero] = 'H';

            trapsandKeys();
            enemyMove();

        System.out.println(toString(matrix));
    }

    public void moveDown() {

            if (lineHero == 11) {
                matrix[lineHero][columnHero] = '.';
                lineHero++;

                matrix[lineHero][columnHero] = 'H';
                return;
            } else {
                matrix[lineHero][columnHero] = '.';
                lineHero += 2;
            }

            if (matrix[lineHero][columnHero] == 'C') {
                if (!keyActive) {
                    for (Porta p : doors) {

                        mudarEstado(p);

                    }
                }
                keyActive = true;
            }

            for (Enemies e :
                    enemies) {
                if (e.isEnemy(lineHero, columnHero)) {
                    heroDead = true;
                }
            }

            matrix[lineHero][columnHero] = 'H';


            trapsandKeys();

            enemyMove();

        System.out.println(toString(matrix));

    }

    public void moveLeft() {

            if (columnHero == 1) {
                matrix[lineHero][columnHero] = '.';
                columnHero--;

                matrix[lineHero][columnHero] = 'H';
                return;
            } else {
                matrix[lineHero][columnHero] = '.';
                columnHero -= 2;
            }

            if (matrix[lineHero][columnHero] == 'C') {
                if (!keyActive) {
                    for (Porta p : doors) {

                        mudarEstado(p);
                    }
                }
                keyActive = true;

            }

            for (Enemies e :
                    enemies) {
                if (e.isEnemy(lineHero, columnHero)) {
                    heroDead = true;
                }
            }
            matrix[lineHero][columnHero] = 'H';


            trapsandKeys();
            enemyMove();

        System.out.println(toString(matrix));
    }

    public boolean isHeroDead() {
        return heroDead;
    }

    public double computeMummyDistanceHero(boolean isGoal) {
        double h = 0;

        if (isGoal) {
            return h;
        }

        for (Enemies e : enemies) {
            h += Math.abs(lineHero - e.getX()) + Math.abs(columnHero - e.getY());

            if (h == 0) {
                return 169;
            }
        }

        return Math.ceil(169 / (h / enemies.size()));
    }

    public boolean isGoal() {
        if (getLineHero() == getLineSaida() && getColumnHero() == getColumnSaida())
            return true;
        return false;
    }


    public double computeDoorDistances() {
        return Math.abs(lineHero - lineSaida) + Math.abs(columnHero - columnSaida);
    }

    public int getNumLines() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public int getTileValue(int line, int column) {
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return matrix[line][column];
    }

    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MummyMazeState)) {
            return false;
        }

        MummyMazeState o = (MummyMazeState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public MummyMazeState clone() {
        return new MummyMazeState(matrix);
    }

    //Listeners
    private transient ArrayList<MummyMazeListener> listeners = new ArrayList<MummyMazeListener>(3);

    public synchronized void removeListener(MummyMazeListener l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(MummyMazeListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(MummyMazeEvent pe) {
        for (MummyMazeListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public int getLineHero() {
        return lineHero;
    }

    public int getColumnHero() {
        return columnHero;
    }

    public int getLineSaida() {
        return lineSaida;
    }

    public int getColumnSaida() {
        return columnSaida;
    }

    //transform my matrix to a string
    public String toString(char[][] matrix) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    public void trapsandKeys() {

        if (matrix[lineKey][columnKey] == '.') {
            matrix[lineKey][columnKey] = 'C';
            keyActive = false;
        }

        for (Trap trap : traps) {
            if (matrix[trap.getX()][trap.getY()] == '.') {
                matrix[trap.getX()][trap.getY()] = 'A';
            }
        }
    }

    public void enemyMove() {
        char[][] newmaze;
        int numMove;
        for (Enemies enemy : enemies) {
            numMove = 2;
            if (enemy instanceof Scorpion) {
                numMove = 1;
            }

            for (int K = 0; K < numMove; K++) {
                enemy.setMatrix(matrix);
                enemy.move(columnHero, lineHero);
                trapsandKeys();
                newmaze = enemy.getMatrix();

                if (enemy.getX() == lineHero && enemy.getY() == columnHero) {
                    heroDead = true;
                }
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix.length; j++) {
                        matrix[i][j] = newmaze[i][j];
                    }

                }

                if (matrix[lineKey][columnKey] == 'C') {
                    if (!keyActive) {
                        for (Porta p : doors) {
                            mudarEstado(p);
                        }
                    }
                    keyActive = true;
                }

            }
        }
    }

    public void mudarEstado(Porta p) {
        if (matrix[p.getX()][p.getY()] == '_') {
            matrix[p.getX()][p.getY()] = '=';
        } else if (matrix[p.getX()][p.getY()] == '"') {
            matrix[p.getX()][p.getY()] = ')';
        } else if (matrix[p.getX()][p.getY()] == ')') {
            matrix[p.getX()][p.getY()] = '"';
        } else if (matrix[p.getX()][p.getY()] == '=') {
            matrix[p.getX()][p.getY()] = '_';
        }
    }

}


