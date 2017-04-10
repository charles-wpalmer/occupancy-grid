package AI;

/**
 * Represents a node in the search tree
 * Created by charles on 10/04/17.
 */
public class Node {
    private int xpos, ypos;

    private Node parent;

    public int getXpos(){
        return this.xpos;
    }

    public int getYpos(){
        return this.ypos;
    }

    public Node getParent(){
        return this.parent;
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
}
