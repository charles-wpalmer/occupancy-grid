package AI;

import robot.Robot;

import java.util.PriorityQueue;

/**
 * Class to handle BFS search of the grid
 * Created by charles on 21/04/17.
 */
public class BFS extends ISearcher {
    @Override
    public Node start(Robot r) {
        return null;
    }

    @Override
    public void createNode(PriorityQueue open, Node parent, int depth, int x, int y) {

    }
}
