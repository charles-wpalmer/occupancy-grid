package robot;

/**
 * robot.Cell to represent each cell of the grid
 * Created by charles on 22/02/17.
 */

public class Cell implements ICell{

    private boolean occuided;

    private boolean explored;

    private boolean nearOccupied;

    private boolean isRobot;

    private boolean isGoal;


    public Cell(){
        this.occuided = false;
    }

    public void setOccuided(boolean occuided){
        this.occuided = occuided;
    }

    public void setExplored(boolean status){
        this.explored = true;
    }

    public boolean getExplored(){
        return this.explored;
    }

    public boolean getOccuided(){
        return this.occuided;
    }

    public void setIsGoal(boolean goal){
        this.isGoal = goal;
    }

    public boolean getIsGoal(){
        return this.isGoal;
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
}
