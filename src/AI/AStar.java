package AI;

import PriorityQueue.DEPQ;
import robot.Grid;
import robot.Robot;

import java.util.HashSet;


/**
 * Class to handle A* searching.
 * Created by charles on 22/03/17.
 */
public class AStar implements IAStar{
    private HashSet explored;

    private DEPQ frontier;

    @Override
    public void start(Robot r) {
        Grid temp;

        frontier = new DEPQ();
        explored = new HashSet<String>();
        frontier.add(r.getGrid());
        temp = (Grid) frontier.getLeast();

        while((temp!=null)){
            temp.print();
            //Keep calling expand all, and iterating round
            //and adding to the queue.
            if(temp.isGoal()){
                //End, at goal
            }
            if(explored.contains(temp)){
                continue;
            }
            temp.expandAll(frontier);
            explored.add(temp);
            temp = (Grid) frontier.getLeast();
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
