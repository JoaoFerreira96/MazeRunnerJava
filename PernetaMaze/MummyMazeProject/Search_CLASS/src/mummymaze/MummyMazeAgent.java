package mummymaze;

import agent.Agent;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class MummyMazeAgent extends Agent<MummyMazeState> {

    protected MummyMazeState initialEnvironment;

    public MummyMazeAgent(MummyMazeState environemt) {
        super(environemt);
        initialEnvironment = (MummyMazeState) environemt.clone();
        heuristics.add(new HeuristicDoorDistance());
        heuristics.add(new HeuristicMummyDistanceHero());
        heuristic = heuristics.get(0);
    }

    public MummyMazeState resetEnvironment() {
        environment = (MummyMazeState) initialEnvironment.clone();
        return environment;
    }

    public MummyMazeState readInitialStateFromFile(File file) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(file);
        char[][] matrix = new char[13][13];
        MummyMazeState.traps = new LinkedList<>();
        MummyMazeState.doors = new LinkedList<>();
        for (int i = 0; i < 13; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();
            for (int j = 0; j < 13; j++) {
                if (matrix[i][j] == 'S') {
                    MummyMazeState.lineSaida = i;
                    MummyMazeState.columnSaida = j;
                }
                if (matrix[i][j] == 'C') {
                    MummyMazeState.lineKey = i;
                    MummyMazeState.columnKey = j;

                }
                if (matrix[i][j] == '=' || matrix[i][j] == ')' || matrix[i][j] == '"' || matrix[i][j] == '_') {
                    MummyMazeState.doors.add(new Porta(i, j));

                }
                if (matrix[i][j] == 'A') {
                    MummyMazeState.traps.add(new Trap(i, j, matrix));
                }
            }
        }
        initialEnvironment = new MummyMazeState(matrix);
        resetEnvironment();
        return environment;
    }
}
