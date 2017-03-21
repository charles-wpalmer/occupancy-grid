package robot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TestApp.fxml"));


        StackPane layout = new StackPane();
        Button btn = new Button();
        btn.setText("Butttooonnn");
        btn.setOnAction(e -> System.out.println("OOoooooooo"));
        layout.getChildren().add(btn);


        BorderPane bp = new BorderPane();
        bp.setTop(root);
        bp.setLeft(layout);

        primaryStage.setScene(new Scene(bp, 300, 275));
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Robot r = new Robot();
        r.readFromFile("/home/charles/Documents/poses.txt", "/home/charles/Documents/ranges.txt");
        //launch(args);
    }
}
