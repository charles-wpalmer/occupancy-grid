package robot;

import AI.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to represent the robot
 * Created by charles on 22/02/17.
 */
public class Robot {

    private Grid environment;
    private double xpos, ypos, orientation;
    ArrayList<Data> data_list;
    private int goalX;
    private int goalY;

    public Robot(){
        this.environment = new Grid();
    }

    public void addRobot(int x, int y){
        this.environment.setThisCell(2, 2, 2);
    }

    public void addGoal(int x, int y){
        this.goalX = x;
        this.goalY = y;
        this.environment.setThisCell(x, y, 4);
    }

    /**
     * TODO:
     *  -   instantiate a*, and finish algorithm
     *
     */
    public void navigate() {
        AStar searcher = new AStar();

        searcher.start(this);
    }

    private double[] convertString(String[] data, int size){
        double[] newData = new double[size];
        int i = 0;

        for (String str : data)
            newData[i++] = Double.valueOf(str);

        return newData;
    }

    public int getGoalX(){
        return this.goalX;
    }

    public int getGoalY(){
        return this.goalY;
    }

    public Grid getGrid(){
        return this.environment;
    }

    public void readFromFile(String posesPath, String rangesPath){
        BufferedReader brp = null;
        BufferedReader brr = null;

        FileReader frp = null;
        FileReader frr = null;

        try {
            frp = new FileReader(posesPath);
            frr = new FileReader(rangesPath);
            brp = new BufferedReader(frp);
            brr = new BufferedReader(frr);

            String rangesLine, posesLine = null;

            this.addRobot(1, 1);
            this.addGoal(30, 15);

            while((rangesLine = brr.readLine()) != null && (posesLine = brp.readLine()) != null){
                String[] ranges = rangesLine.split(" ");
                String[] poses = posesLine.split(" ");

                double[] range = this.convertString(ranges, 8);

                double[] pose = this.convertString(poses, 3);

                Data dataStruct = new Data(pose[0], pose[1], pose[2], range);

                this.environment.convertData(dataStruct);
            }

            this.environment.print();

            this.navigate();

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (brr != null){
                    brr.close();
                    brp.close();
                }

                if (frr != null) {
                    frr.close();
                    frp.close();
                }

            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
