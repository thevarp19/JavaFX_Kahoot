package kahoot.it.project3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Stack;

import static javafx.scene.text.TextAlignment.CENTER;

public class Client extends Application{
    private final double W=600.;
    private final double H=600.;
    private Pane root;
    private Socket socket;
    private BufferedWriter toServer;
    private BufferedReader fromServer;
    private Stage window;

    private void connectToServer() throws IOException{
        socket=new Socket("localhost",7006);
        toServer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        fromServer=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }



    public GridPane playGame(String name){
        GridPane pane=new GridPane();
        pane.setHgap(5);pane.setVgap(5);
        Button btnRed=new Button();
        btnRed.setStyle("-fx-background-color:red;-fx-background-radius: 5");
        btnRed.setMinWidth(300.0);
        btnRed.setMinHeight(300.0);
        Polygon polygon=new Polygon(-50.0,40.0,50.0,40.0,0.0,-60.0);
        polygon.setFill(Color.WHITE);

        btnRed.setGraphic(polygon);
        pane.add(btnRed,0,0);
        btnRed.setOnAction(e->{
            try {
                toServer.write(name+" Red");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button btnBlue=new Button();
        btnBlue.setStyle("-fx-background-color:blue;-fx-background-radius: 5");
        btnBlue.setMinWidth(300.0);
        btnBlue.setMinHeight(300.0);

        Rectangle rec=new Rectangle(100,100);
        rec.setFill(Color.WHITE);
        rec.setRotate(45);
        rec.setArcHeight(5.0);
        rec.setArcWidth(5.0);
        btnBlue.setGraphic(rec);
        pane.add(btnBlue,1,0);
        btnBlue.setOnAction(e->{
            try {
                toServer.write(name+" Blue");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button btnOrange=new Button();
        btnOrange.setStyle("-fx-background-color:orange;-fx-background-radius: 5");
        btnOrange.setMinWidth(300.0);
        btnOrange.setMinHeight(300.0);
        Circle circle=new Circle(50);
        circle.setFill(Color.WHITE);
        btnOrange.setGraphic(circle);
        pane.add(btnOrange,0,1);
        btnOrange.setOnAction(e->{
            try {
                toServer.write(name+" Orange");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button btnGreen=new Button();
        btnGreen.setStyle("-fx-background-color:green;-fx-background-radius: 5");
        btnGreen.setMinWidth(300.0);
        btnGreen.setMinHeight(300.0);
        Rectangle rec2=new Rectangle(100,100);
        rec2.setFill(Color.WHITE);
        rec2.setArcHeight(5.0);
        rec2.setArcWidth(5.0);
        btnGreen.setGraphic(rec2);
        pane.add(btnGreen,1,1);
        btnGreen.setOnAction(e->{
            try {
                toServer.write (name+" Green");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        return pane;
    }
//    public StackPane waiting() throws IOException {
//
//        StackPane stackPane=new StackPane();
//        stackPane.setStyle("-fx-background-color: #6427b9");
//        Label label=new Label("Waiting start game...");
//        label.setFont(Font.font( "Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,24));
//        stackPane.getChildren().add(label);
//        String startGame=fromServer.readLine();
//        if (startGame.equals("Start!")){
//            window.setScene(new Scene(playGame("hello"),W,H));
//
//        }
//        return stackPane;
//    }

    public StackPane nickPane(){
        StackPane stackPane=new StackPane();
        TextField tf=new TextField();
        tf.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC,18));
        tf.setMaxHeight(45);
        tf.setMaxWidth(250);
        tf.setPromptText("Enter username");
        tf.setAlignment(Pos.CENTER);
        Button btn=new Button("Enter");
        btn.setMaxHeight(45);
        btn.setMaxWidth(250);
        btn.setStyle("-fx-background-color: #000001;");
        btn.setTextFill(Color.WHITE);
        btn.setTextAlignment(CENTER);
        btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC,18));
        VBox vBox=new VBox(10);

        vBox.setMaxWidth(W/2);
        vBox.setMaxHeight(H/2);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(tf,btn);

        btn.setOnAction(e->{
            try {
                toServer.write(tf.getText());
                window.setScene(new Scene(playGame(tf.getText()),W,H));
                window.setTitle(tf.getText());
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        });
        stackPane.setStyle("-fx-background-color: #6427b9");
        stackPane.getChildren().add(vBox);
        return stackPane;
    }


    public BorderPane pinPane() throws FileNotFoundException {
        BorderPane pane=new BorderPane();
        pane.setPrefHeight(600);pane.setPrefWidth(600);
        StackPane stackPane=new StackPane();
        stackPane.setMaxHeight(150);stackPane.setMaxWidth(200);
        ImageView image=new ImageView(new Image(new FileInputStream("src/img/client.jpg")));
        image.setFitHeight(600);image.setFitWidth(600);
        stackPane.getChildren().add(image);
        BorderPane.setAlignment(stackPane,Pos.CENTER);

        VBox vBox=new VBox();
        vBox.setPrefHeight(200);vBox.setPrefWidth(100);
        vBox.setAlignment(Pos.TOP_CENTER);
        ImageView logo=new ImageView(new Image(new FileInputStream("src/img/logokah.png")));
        logo.setFitWidth(400);logo.setFitHeight(140);
        VBox.setMargin(logo,new Insets(50,0,0,0));
        vBox.getChildren().add(logo);
        stackPane.getChildren().add(vBox);

        Rectangle rec=new Rectangle();
        rec.setFill(Color.WHITE);
        rec.setArcWidth(30);rec.setArcHeight(30);
        rec.setWidth(450);rec.setHeight(160);
        stackPane.getChildren().add(rec);

        Label in=new Label("Incorrect PIN");
        in.setVisible(false);
        in.setTextFill(Color.RED);
        StackPane.setMargin(in,new Insets(135,0,0,0));
        stackPane.getChildren().add(in);

        TextField tf=new TextField();
        tf.setFont(Font.font("Trebuchet MS Bold", FontWeight.BOLD, FontPosture.ITALIC,18));
        tf.setMaxWidth(280);
        tf.setMaxHeight(45);
        tf.setPromptText("Game PIN");
        tf.setAlignment(Pos.CENTER);
        StackPane.setMargin(tf,new Insets(0,0,60,0));
        stackPane.getChildren().add(tf);

        Button btn=new Button("Enter");
        btn.setMaxHeight(45);
        btn.setMaxWidth(280);
        btn.setStyle("-fx-background-color: #000001;");
        btn.setTextFill(Color.WHITE);
        btn.setAlignment(Pos.CENTER);
        btn.setFont(Font.font("Dubai Medium", FontWeight.BOLD, FontPosture.ITALIC,18));
        StackPane.setMargin(btn,new Insets(60,0,0,0));
        stackPane.getChildren().add(btn);

        pane.setCenter(stackPane);

        btn.setOnAction(e->{
            try {
                toServer.write(Integer.parseInt(tf.getText()));
                String status=fromServer.readLine();
                System.out.println(status);
                if(status.equals("Success!")) {
//                     in.setVisible(true);
//                }else{
//                    in.setVisible(false);
                    window.setScene(new Scene(nickPane(), W, H));
                    window.setTitle("Enter NickName");
                }

            } catch (IOException ex) {
                throw new RuntimeException();
            }
        });
        return pane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        window=stage;
        connectToServer();
        root=pinPane();
        stage.setScene(new Scene(root,W,H));
        stage.show();
        stage.setTitle("Client");
        root.requestFocus();
    }
}
