package mummymaze;

import agent.Heuristic;

public class HeuristicDoorDistance extends Heuristic<MummyMazeProblem, MummyMazeState>{

    @Override
    public double compute(MummyMazeState state){
        return state.computeDoorDistances();
    }
    
    @Override
    public String toString(){
        return "Hero distance to the door";
    }
}