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
    public Node start(Robot r) {
        Node temp;

        this.goalY = r.getGoalY();
        this.goalX = r.getGoalX();
        this.board = r.getGrid();

        DEPQ frontier = new DEPQ();
        HashSet explored = new HashSet<String>();

        temp = new Node(r.getRobotX(), r.getRobotY());

        //frontier.add(temp);
        //temp = (Grid) frontier.getLeast();

        while((temp!=null)){
            //temp.print();

            //Keep calling expand all, and iterating round
            //and adding to the queue.
            if(isGoal(temp)){
                System.out.println("Goal Found!");
                System.out.println(temp.getDepth());
                //temp.print();
                break;
            }
            if(explored.contains(temp)){
                //continue;
            }
            expandAll(temp, frontier, temp.getDepth()+1);
            explored.add(temp);
            temp = (Node) frontier.getLeast();
        }
        return temp;
    }

    public boolean isGoal(Node curr){

        System.out.println(curr.getYpos()+"---"+this.goalY+"---"+this.goalX+"---"+curr.getXpos());
        if(curr.getXpos() == this.goalX && curr.getYpos() == this.goalY){
            return true;
        } else {
            return false;
        }
    }

    private boolean isLegal(int x, int y){
        return ((x >= 0) && (x < 50) && (y >= 0) &&
                (y < 50) && !this.board.getCell(x, y).getOccuided());

    }

    private void createNode(DEPQ open, Node parent, int depth, int x, int y){
        Node temp;
        temp = new Node(x, y, depth);
        temp.setParent(parent);
        //parent.copyGrid(temp, parent);
        //temp.setThisCell(x, y, 2);
        //temp.setThisCell(parent.getRobotX(), parent.getRobotY(), 3);
        int h = this.calculateCost(temp);

        //f(n) = g(n) + h(n)
        temp.setF(depth + h);
        open.add(temp);
    }

    private void expandAll(Node parent, DEPQ open, int depth){
        int x;
        int y;
        //Foreach neighbour
        //calculate f=g+h
        if(isLegal(parent.getXpos()-1, parent.getYpos())){
            x = parent.getXpos()-1;
            y = parent.getYpos();

            createNode(open, parent, depth, x, y);
        }
        if(isLegal(parent.getXpos()+1, parent.getYpos())){
            x = parent.getXpos()+1;
            y = parent.getYpos();

            createNode(open, parent, depth, x, y);
        }
        if(isLegal(parent.getXpos(), parent.getYpos()-1)){
            x = parent.getXpos();
            y = parent.getYpos()-1;

            createNode(open, parent, depth, x, y);
        }
        if(isLegal(parent.getXpos(), parent.getYpos()+1)){
            x = parent.getXpos();
            y = parent.getYpos()+1;

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
    public int calculateCost(Node grid) {
        int y = this.goalY - grid.getYpos();
        int x = this.goalX - grid.getXpos();

        return Math.abs(x + y);
    }
}
