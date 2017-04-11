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

    private int goalX, goalY;

    private Grid board;

    @Override
    public void start(Robot r) {
        Grid temp;

        this.goalY = r.getGoalY();
        this.goalX = r.getGoalX();
        this.board = r.getGrid();

        DEPQ frontier = new DEPQ();
        HashSet explored = new HashSet<String>();

        Node temp = new Node(r.getRobotX(), r.getRobotY());

        frontier.add(node);
        //temp = (Grid) frontier.getLeast();

        while((temp!=null)){
            //temp.print();

            //Keep calling expand all, and iterating round
            //and adding to the queue.
            if(temp.isGoal(this.GoalX(), this.GoalY())){
                System.out.println("Goal Found!");
                temp.print();
                break;
            }
            if(explored.contains(temp)){
                continue;
            }
            expandAll(temp, frontier, temp.getDepth()+1);
            explored.add(temp);
            temp = (Grid) frontier.getLeast();
        }
    }

    private boolean isLegal(int x, int y, Grid board){
        return ((x >= 0) && (x < 50) && (y >= 0) &&
                (y < 50) && !board.getCell(x, y).getOccuided());

    }

    private void createNode(DEPQ open, Grid parent, int depth, int x, int y){
        Grid temp;
        temp = new Node(x, y, depth);

        temp.setParent(parent);
        //parent.copyGrid(temp, parent);
        //temp.setThisCell(x, y, 2);
        //temp.setThisCell(parent.getRobotX(), parent.getRobotY(), 3);

        int h = this.calculateCost(temp);

        //f(n) = g(n) + h(n)
        temp.f = depth + h;
        open.add(temp);
    }

    private void expandAll(Grid parent, DEPQ open, int depth){
        int x;
        int y;
        //Foreach neighbour
        //calculate f=g+h
        if(isLegal(parent.getRobotX() -1, parent.getRobotY(), parent)){
            x = parent.getRobotX()-1;
            y = parent.getRobotY();

            createNode(open, parent, depth, x, y);
        }
        if(isLegal(parent.getRobotX() +1, parent.getRobotY(), parent)){
            x = parent.getRobotX()+1;
            y = parent.getRobotY();

            createNode(open, parent, depth, x, y);
        }
        if(isLegal(parent.getRobotX(), parent.getRobotY() -1, parent)){
            x = parent.getRobotX();
            y = parent.getRobotY()-1;

            createNode(open, parent, depth, x, y);
        }
        if(isLegal(parent.getRobotX(), parent.getRobotY() +1, parent)){
            x = parent.getRobotX();
            y = parent.getRobotY()+1;

            createNode(open, parent, depth, x, y);
        }

    }

    @Override
    public void finalise() {

    }

    @Override
    public void playSolution() {

    }

    @Override
    public int calculateCost(Grid grid) {
        int y = this.goalY - grid.getRobotY();
        int x = this.goalX - grid.getRobotX();

        return Math.abs(x + y);
    }
}
