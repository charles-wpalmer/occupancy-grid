package AI;

import robot.Grid;

/**
 * Represents a node in the search tree
 * Created by charles on 10/04/17.
 */
public class Node implements Comparable<Node>{
    private int xpos, ypos;

    private int f;

    private int depth;

    private Node parent;

    public Node(int x, int y){
        this.xpos = x;
        this.ypos = y;
    }

    public Node(int x, int y, int d){
        this.xpos = x;
        this.ypos = y;
        this.depth = d;
    }

    public int getF(){
        return this.f;
    }

    public int getXpos(){
        return this.xpos;
    }

    public int getDepth(){
        return this.depth;
    }

    public int getYpos(){
        return this.ypos;
    }

    public Node getParent(){
        return this.parent;
    }

    public void setF(int val){
        this.f = val;
    }

    public void setXpos(int x){
        this.xpos = x;
    }

    public void setYpos(int y){
        this.ypos = y;
    }

    public void setParent(Node node){
        this.parent = node;
    }

    @Override
    public int compareTo(Node o) {
        return f-o.f;

    }
}
