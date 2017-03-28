package robot;

public class Main {


    public static void main(String[] args) {
        Robot r = new Robot();
        r.readFromFile("/home/charles/Documents/poses.txt", "/home/charles/Documents/ranges.txt");
        //launch(args);
    }
}
