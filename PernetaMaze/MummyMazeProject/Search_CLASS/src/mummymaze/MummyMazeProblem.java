package mummymaze;

import agent.Action;
import agent.Problem;

import java.util.LinkedList;
import java.util.List;

public class MummyMazeProblem extends Problem<MummyMazeState> {

    protected List<Action> actions;

    public MummyMazeProblem(MummyMazeState initialState) {
        super(initialState);
        actions = new LinkedList<Action>() {{
            add(new ActionDontMove());
            add(new ActionUp());
            add(new ActionDown());
            add(new ActionRight());
            add(new ActionLeft());
        }};

    }

    @Override
    public List<Action<MummyMazeState>> getActions(MummyMazeState state) {
        List<Action<MummyMazeState>> possibleActions = new LinkedList<>();
    if (!state.isHeroDead()) {
        for (Action action : actions) {
            if (action.isValid(state)) {
                possibleActions.add(action);
            }
        }
    }
        return possibleActions;
    }

    @Override
    public MummyMazeState getSuccessor(MummyMazeState state, Action action){
        MummyMazeState successor = state.clone();
        action.execute(successor);
        return successor;
    }

    @Override
    public boolean isGoal(MummyMazeState state) {
        if(state.getLineHero() == state.getLineSaida() && state.getColumnHero() == state.getColumnSaida())
            return true;
        return false;
    }

    @Override
    public double computePathCost(List<Action> path) {
        return path.size();
    }


}
