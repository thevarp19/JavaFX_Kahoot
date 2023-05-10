package kahoot.it.project2.tasks;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CAr extends Application {
    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();
        Pane carPane = new Pane();
        Circle circle1 = new Circle(15, 100 - 5, 5, Color.BLACK);
        Circle circle2 = new Circle(35, 100 - 5, 5, Color.BLACK);
        Rectangle carRec = new Rectangle(0, 100 - 20, 50, 10);
        carRec.setFill(Color.KHAKI);
        Polygon carTop = new Polygon(10, 80, 40, 80, 30, 70, 20, 70);
        carTop.setFill(Color.BLUE);
        carPane.getChildren().addAll(circle1, circle2, carRec, carTop);
        pane.setBottom(carPane);




        PathTransition pt = new PathTransition(Duration.millis(5000), new Line(200, 25, 550, 25));
        pt.setNode(carPane);
        pt.setCycleCount(PathTransition.INDEFINITE);
        pt.play();


        Button pause=new Button(">");
        pause.setOnAction(e->{
            pt.pause();});
        Button play=new Button("Play");
        play.setOnAction(e->{
           pt.play();
        });
        pane.setTop(play);
        pane.setLeft(pause);


        Scene scene = new Scene(pane, 400, 125);
        primaryStage.setTitle("Car");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

