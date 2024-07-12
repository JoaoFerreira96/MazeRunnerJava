package mummymaze;

import agent.Heuristic;

public class HeuristicMummyDistanceHero extends Heuristic<MummyMazeProblem, MummyMazeState> {

    @Override
    public double compute(MummyMazeState state) {
        return state.computeMummyDistanceHero(state.isGoal());
    }
    
    @Override
    public String toString(){
        return "Distance Between enemies and the hero";
    }    
}