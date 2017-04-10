package robot;

/**
 * Class to represent each individual set
 * of data
 * Created by charles on 01/03/17.
 */
public class Data {

    private double x_pos, y_pos, bot_orientation;

    private double[] ranges;

    public double getRadian(double degree){
        return degree * Math.PI/180;
    }

    public Data(double x, double y, double orientation, double[] ranges){
        this.x_pos = x;
        this.y_pos = y;
        this.bot_orientation = this.getRadian(orientation);
        this.ranges = ranges;
    }

    public void setXpos(double x){
        this.x_pos = x;
    }

    public void setYpos(double y){
        this.y_pos = y;
    }

    public void setOrientation(double orientation){
        this.bot_orientation = orientation;
    }

    public double get_x(){
        return x_pos;
    }

    public double get_y(){
        return y_pos;
    }

    public double[] getRanges(){
        return this.ranges;
    }

    public double getRange(int index){
        if(this.ranges.length > index){
            return this.ranges[index];
        } else {
            return 0;
        }
    }

    public double getBot_orientation(){
        return this.bot_orientation;
    }

    public void printAllData(){
        System.out.println("Current x pos: " + this.get_x());
        System.out.println("Current y pos: " + this.get_y());
        System.out.println("Current orientation: " + this.getBot_orientation());

        System.out.println("Ranges at current location:\n");
        for(double t : this.ranges)
            System.out.println(t);
    }
}
