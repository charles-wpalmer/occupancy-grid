package AI;

import PriorityQueue.DEPQ;
import robot.Grid;
import robot.Robot;

import java.util.HashSet;
import java.util.Random;


/**
 * Class to handle A* searching.
 * Created by charles on 22/03/17.
 */
public class AStar implements IAStar{

    @Override
    public void start(Robot r) {
        Grid temp;

        DEPQ frontier = new DEPQ();
        HashSet explored = new HashSet<String>();

        frontier.add(r.getGrid());
        temp = (Grid) frontier.getLeast();
        int i =0;
        while((temp!=null)){
            if(i > 1) {
                break;
            }
            temp.print();
            //Keep calling expand all, and iterating round
            //and adding to the queue.
            if(temp.isGoal()){
                //End, at goal
            }
            if(explored.contains(temp)){
                continue;
            }
            expandAll(temp, frontier, temp.getDepth()+1);
            explored.add(temp);
            temp = (Grid) frontier.getLeast();
            i++;
        }
    }

    private int calcHeuristic(){
        return 1;
    }

    private boolean isLegal(int x, int y, Grid board){
        return ((x >= 0) && (x < 50) && (y >= 0) &&
                (y < 50) && !board.getCell(x, y).getOccuided());

    }

    private void createNode(DEPQ open, Grid parent, int depth, int x, int y){
        Grid temp;
        temp = new Grid(depth);

        parent.copyGrid(temp, parent);
        temp.setThisCell(x, y, 2);
        temp.setThisCell(parent.getRobotX(), parent.getRobotY(), 1);

        int h = this.calcHeuristic();
        temp.setParent(parent);

        temp.f = depth + h;
        open.add(temp);
    }

    private void expandAll(Grid parent, DEPQ open, int depth){
        int x;
        int y;
        //Foreach neighbour
        //calculate f=g+h
        if(isLegal(parent.getRobotX() -1, parent.getRobotY(), parent)){
            //System.out.println(isLegal(parent.robotX -1, parent.robotY)+" "+parent.robotX);
            x = parent.getRobotX()-1;
            y = parent.getRobotY();

            createNode(open, parent, depth, x, y);
        }
        if(isLegal(parent.getRobotX() +1, parent.getRobotY(), parent)){
            //System.out.println(isLegal(parent.robotX -1, parent.robotY)+" "+parent.robotX);
            x = parent.getRobotX()+1;
            y = parent.getRobotY();

            createNode(open, parent, depth, x, y);
        }
        if(isLegal(parent.getRobotX(), parent.getRobotY() -1, parent)){
            //System.out.println(isLegal(parent.robotX -1, parent.robotY)+" "+parent.robotX);
            x = parent.getRobotX();
            y = parent.getRobotY()-1;

            createNode(open, parent, depth, x, y);
        }
        if(isLegal(parent.getRobotX(), parent.getRobotY() +1, parent)){
            //System.out.println(isLegal(parent.robotX -1, parent.robotY)+" "+parent.robotX);
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
    public int calculateCost() {
        return 0;
    }
}
