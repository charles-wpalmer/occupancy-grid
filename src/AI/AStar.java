package AI;

import PriorityQueue.DEPQ;
import robot.Grid;
import robot.Robot;


/**
 * Class to handle A* searching.
 * Created by charles on 22/03/17.
 */
public class AStar implements IAStar{

    @Override
    public void expandAll(Grid grid, int depth) {

    }

    @Override
    public void start(Robot r) {
        Grid temp;

        DEPQ frontier = new DEPQ();

        frontier.add(r.getGrid());
        temp = (Grid) frontier.inspectMost();

        while(r.getGrid().isGoal(r.getGrid())){
            //Keep calling expand all, and iterating round
            //and adding to the queue.

            frontier.getLeast();
        }
    }

    @Override
    public void finalise() {

    }

    @Override
    public void playSolution() {

    }

    @Override
    public int calculateCost() {
        return 0;
    }
}
