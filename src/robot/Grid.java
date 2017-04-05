package robot;

import PriorityQueue.DEPQ;
import javafx.scene.Group;

import java.util.Random;

/**
 * Class to represent the grid the robot will use
 * Created by charles on 22/02/17.
 */
public class Grid implements Comparable<Grid>{
    private int goalX, goalY, robotX, robotY;

    private Grid parent;

    private Cell occupancyGrid[][];

    private int GRID_SIZE = 50;

    // @TODO:
    // Make private
    public int f, h;
    public int depth;

    Grid() {
        this.occupancyGrid = new Cell[50][50];

        for(int x=0;x<GRID_SIZE;x++){
            for(int y=0;y<GRID_SIZE;y++){
                Cell cell = new Cell();
                this.occupancyGrid[x][y] = cell;
            }
        }
    }

    public Grid(int depth) {
        this.occupancyGrid = new Cell[50][50];
        this.depth = depth;
        for(int x=0;x<GRID_SIZE;x++){
            for(int y=0;y<GRID_SIZE;y++){
                Cell cell = new Cell();
                this.occupancyGrid[x][y] = cell;
            }
        }
    }

    public Grid getParent(){
        return this.parent;
    }

    public void setParent(Grid parent){
        this.parent = parent;
    }

    public int getDepth(){
        return this.depth;
    }

    public void setGoalState(int x, int y){
        this.goalX = x;
        this.goalY = y;
    }

    public boolean isGoal(){
        return (this.goalX == this.robotX && this.goalY == this.robotY);
    }

    public void copyGrid(Grid newG, Grid oldG){

        for(int x=0;x<GRID_SIZE;x++){
            for(int y=0;y<GRID_SIZE;y++){
                newG.occupancyGrid[x][y] = oldG.occupancyGrid[x][y];
            }
        }

    }

    private double calc_y(double y, double range, double orientation, double sensor) {
        return y + range * Math.sin(orientation + sensor);
    }

    private double calc_x(double x, double range, double orientation, double sensor) {
        return x + range * Math.cos(orientation + sensor);
    }
    
    public Cell getCell(int x, int y){
        return this.occupancyGrid[x][y];
    }

    public int getRobotX(){
        return this.robotX;
    }

    public int getRobotY(){
        return this.robotY;
    }

    void convertData(Data data){

        for(int i=0; i < 8; i++){
            if(data.getRange(i) < 2.5){
                double x_coord, y_coord;

                x_coord = calc_x(data.get_x(), data.getRange(i),
                        data.getBot_orientation(), data.getRadian(i*45));

                y_coord = calc_y(data.get_y(), data.getRange(i),
                        data.getBot_orientation(), data.getRadian(i*45));

                this.setCell(x_coord, y_coord, 1);
            }
        }

    }

    public void setThisCell(int x_cell, int y_cell, int type){
        if(type == 1) {
            if(x_cell < 50 && y_cell < 50 && x_cell >= 0 && y_cell >= 0) {
                Cell cell = new Cell();
                cell.setOccuided(true);
                cell.setHasRobot(false);
                this.occupancyGrid[y_cell][x_cell] = cell;
            }

        } else {
            Cell cell = new Cell();
            cell.setHasRobot(true);
            cell.setOccuided(false);
            this.occupancyGrid[y_cell][x_cell] = cell;
            this.robotX = x_cell;
            this.robotY = y_cell;
        }
    }

    void setCell(double x, double y, int type){
        int x_cell = (int)(x/0.2) - 1;
        int y_cell = GRID_SIZE - (int)(y/0.2) - 1;

        this.setThisCell(x_cell, y_cell, type);
    }

    public void print(){
        for(int i=0; i<GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++) {

                if (this.occupancyGrid[i][j].getHasRobot()) {
                    System.out.println(" R ");
                } else if (this.occupancyGrid[i][j].getOccuided()){
                    System.out.print(" # ");
                } else if(this.occupancyGrid[i][j].getMaybeOccupied()){
                    System.out.print(" ~ ");
                } else {
                    System.out.print("   ");
                }

            }
            System.out.println();
        }
    }

    @Override
    public int compareTo(Grid o) {
        return f-o.f;

    }
}

