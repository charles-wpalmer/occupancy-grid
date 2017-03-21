package robot;

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

    public Robot(){
        this.environment = new Grid();
    }

    public void print(){

    }

    public void addRobot(double x, double y){
        this.environment.setCell(2, 2, 2);
    }

    private double[] convertString(String[] data, int size){
        double[] newData = new double[size];
        int i = 0;

        for (String str : data)
            newData[i++] = Double.valueOf(str);

        return newData;
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

            while((rangesLine = brr.readLine()) != null && (posesLine = brp.readLine()) != null){
                String[] ranges = rangesLine.split(" ");
                String[] poses = posesLine.split(" ");

                double[] range = this.convertString(ranges, 8);

                double[] pose = this.convertString(poses, 3);

                Data dataStruct = new Data(pose[0], pose[1], pose[2], range);

                this.environment.convertData(dataStruct);
            }
            this.environment.print();

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
