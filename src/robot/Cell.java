package robot;

/**
 * robot.Cell to represent each cell of the grid
 * Created by charles on 22/02/17.
 */

public class Cell implements ICell{

    private boolean occuided;

    private boolean nearOccupied;

    private boolean isRobot;

    private ICell left, right, up, down;

    public Cell(){
        this.occuided = false;
    }

    public void setOccuided(boolean occuided){
        this.occuided = occuided;
    }

    public boolean getOccuided(){
        return this.occuided;
    }

    public void setMaybeOccupied(boolean status){
        this.nearOccupied = status;
    }

    public boolean getMaybeOccupied(){
        return this.nearOccupied;
    }

    public void setHasRobot(boolean status){
        this.isRobot = status;
    }

    public boolean getHasRobot(){
        return this.isRobot;
    }

    public void setLeft(ICell left){
        this.left = left;
    }

    public ICell getLeft() {
        return this.left;
    }

    public void setRight(ICell right){
        this.right = right;
    }

    public ICell getRight() {
        return this.right;
    }

    public void setUp(Cell up){
        this.up = up;
    }

    public ICell getUp() {
        return this.up;
    }

    public void setDown(Cell down){
        this.down = down;
    }

    public ICell getDown() {
        return this.down;
    }
}
