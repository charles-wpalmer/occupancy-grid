package AI;

import robot.*;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Interface for A* search.
 * Created by charles on 21/03/17.
 */
public abstract class ISearcher {

    Grid board;

    protected int goalX, goalY;

    void expandAll(Node parent, Collection<Node> open, int depth){
        int x;
        int y;

        //Foreach neighbour
        //calculate f=g+h
        if(this.board.isLegal(parent.getXpos()-1, parent.getYpos())){
            x = parent.getXpos()-1;
            y = parent.getYpos();

            createNode(open, parent, depth, x, y);
        }
        if(this.board.isLegal(parent.getXpos()+1, parent.getYpos())){
            x = parent.getXpos()+1;
            y = parent.getYpos();

            createNode(open, parent, depth, x, y);
        }
        if(this.board.isLegal(parent.getXpos(), parent.getYpos()-1)){
            x = parent.getXpos();
            y = parent.getYpos()-1;

            createNode(open, parent, depth, x, y);
        }
        if(this.board.isLegal(parent.getXpos(), parent.getYpos()+1)){
            x = parent.getXpos();
            y = parent.getYpos()+1;

            createNode(open, parent, depth, x, y);
        }

    }

    public boolean isGoal(Node curr){
        if(curr.getXpos() == this.goalX && curr.getYpos() == this.goalY){
            return true;
        } else {
            return false;
        }
    }
    
    public abstract Node start(Robot r);

    public abstract void createNode(Collection<Node> open, Node parent, int depth, int x, int y);
}
