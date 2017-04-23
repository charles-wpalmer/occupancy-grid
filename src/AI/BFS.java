package AI;

import robot.Grid;
import robot.Robot;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Class to handle BFS search of the grid
 * Created by charles on 21/04/17.
 */
public class BFS extends ISearcher {

    private int ne;

    @Override
    public Node start(Robot r) {
        Node temp;

        this.goalY = r.getGoalY();
        this.goalX = r.getGoalX();
        this.board = r.getGrid();

        LinkedList<Node> frontier = new LinkedList<Node>();
        HashSet explored = new HashSet<String>();

        temp = new Node(r.getRobotX(), r.getRobotY());

        while((temp!=null)){

            if(isGoal(temp)){
                System.out.println("Goal Found! Nodes Expanded: "+this.ne);
                System.out.println(temp.getDepth());
                //temp.print();
                break;
            }
            if(!explored.contains(temp)) {
                expandAll(temp, frontier, temp.getDepth() + 1);
                explored.add(temp);
            }

            temp = (Node) frontier.poll();
        }
        return temp;
    }

    @Override
    public void createNode(Collection<Node> open, Node parent, int depth, int x, int y) {
        this.ne++;
        Node temp;
        temp = new Node(x, y, depth);
        temp.setParent(parent);

        open.add(temp);
    }
}
