package kahoot.it.project2;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.*;
import java.util.*;

import static javafx.scene.text.TextAlignment.CENTER;


public class Quiz extends Application {
    Scanner in=new Scanner(System.in);
    Test test;
    FillIn fill=new FillIn();
    String path;
    ArrayList<String> questions=new ArrayList<>();
    ArrayList<ArrayList<String>> variety=new ArrayList<>();
    ArrayList<String> correctAnswer=new ArrayList<>();
    ArrayList<Integer>shuffleId=new ArrayList<>();
    int IdSH;
    int and;
    int variety_size;

    Timeline timeline;
    Text timeText;
    int hour;
    int mins;
    int secs;
    int millis;



//   public void setTime(Text text) {
//        if(millis == 1000) {
//            secs++;
//            millis = 0;
//        }
//        if(secs == 60) {
//            mins++;
//            secs = 0;
//        }
//        millis++;
//        text.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
//                + (((secs/10) == 0) ? "0" : "") + secs + ""
//        );
//    }
    @Override
    public void start(Stage stage) throws Exception {
//        BorderPane pane=new BorderPane();
//        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,15);
//        Font font2 = Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR,12);
//        Text text=new Text("Your Result: ");
//        text.setFont(font);
//        Button show=new Button("Show answers");
//        show.setStyle("-fx-background-radius: 5;-fx-font-weight: bold;-fx-text-fill: white; -fx-background-color: #0606d5;-fx-font-size: 15;-fx-stroke:#003dff;");
//        show.setMaxWidth(250);
//        show.setMinWidth(250);
//
//        Button close=new Button("Close test");
//        close.setStyle("-fx-background-radius: 5;-fx-font-weight: bold;-fx-text-fill: white; -fx-background-color: #e80606;-fx-font-size: 15;-fx-stroke:#e30d33;");
//        close.setMaxWidth(250);
//        close.setMinWidth(250);
//        close.setOnAction(e->{
//            stage.close();
//        });
//
//        Label percent=new Label("30.35%");
//        Label numCorr=new Label("Number of correct answers: ");
//        Label lastTime=new Label("Finished in 15:12");
//        percent.setFont(font2);
//        numCorr.setFont(font2);
//        numCorr.setWrapText(true);
//        lastTime.setFont(font2);
//
//        Image result=new Image(new FileInputStream("src/img/result.png"));
//        ImageView imageView=new ImageView(result);
//        imageView.setFitWidth(500);
//        imageView.setFitHeight(250);
//
//
//        VBox vBox =new VBox(15);
//        vBox.setAlignment(Pos.TOP_CENTER);
//        pane.setCenter(vBox);
//        vBox.getChildren().addAll(text,percent,numCorr,lastTime,show,close);
//        pane.setCenter(vBox);
//        pane.setBottom(new StackPane(imageView));


       // stage.setScene(new Scene(pane,605.0,450.0));
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }
//      //  ShuffleId();
//        Pane pane =new Pane();
//        FlowPane root=new FlowPane(Orientation.HORIZONTAL,3,3);
//        Rectangle []rectangle=new Rectangle[4];
//        for(int i=0;i<4;i++){
//            rectangle[i]=new Rectangle(300,70);
//            root.getChildren().addAll(rectangle[i]);
//        }
//        root.setPadding(new Insets(3));
//        root.setLayoutX(0);root.setLayoutY(304);
//        rectangle[0].setFill(Color.RED);
//        rectangle[1].setFill(Color.BLUE);
//        rectangle[2].setFill(Color.ORANGE);
//        rectangle[3].setFill(Color.GREEN);
//
//
//        Image image=new Image(new FileInputStream("src/img/logo.png"));
//        ImageView imageView=new ImageView(image);
//        imageView.setFitHeight(210);
//        imageView.setFitWidth(320);
//        imageView.setX(140);imageView.setY(70);
//        RadioButton r1=new RadioButton();
//        Button bR=new Button("❯");
//        Button bL=new Button("❮");
//        Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC,17);
//        bR.setFont(font);
//        bL.setFont(font);
//        bL.setLayoutX(0);bL.setLayoutY(155);
//        bR.setLayoutX(575);bR.setLayoutY(155);
//        pane.getChildren().addAll(root,imageView,bR,bL);
//        Scene scene=new Scene(pane,605,450);
//        stage.setScene(scene);
//        stage.show();
//    }


//    public Quiz(String path) throws IOException {
//            this.path = path;
//        //loadFromFile();
//    }
//
//    public void ShuffleQuestion(int id){
//        IdSH=shuffleId.get(id);
//        variety_size=variety.get(IdSH).size();
//        addQuestion();
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void addQuestion(){
//     if(variety_size == test.getOptionAt()){
//         test.setDescription(questions.get(IdSH));
//         test.setOptions(variety.get(IdSH));
//         test.setAnswer(correctAnswer.get(IdSH));
//         and=0;
//     }else{
//         fill.setDescription(questions.get(IdSH));
//         fill.setAnswer(correctAnswer.get(IdSH));
//         and=1;
//     }
//
//    }
//    public void ShuffleId(){
//        for(int i=0;i<questions.size();i++){
//            shuffleId.add(i);}
//        Collections.shuffle(shuffleId);
//    }
//
//    public void loadFromFile() throws IOException {
//        File file = new File(getPath());
//        Scanner in=new Scanner(file);
//        String line;
//        int count_var=-1;
//        while(in.hasNextLine()){
//            line=in.nextLine();
//            if(line.contains("-")){continue;}
//            if(line.contains("?")) {
//                questions.add(line);
//                variety.add(new ArrayList<>());
//                count_var++;
//            }if(line.contains(")")){
//                String []var=line.split(" *\\) *");
//                correctAnswer.add(var[0]);
//                for(int i=0;i<var.length;i++)
//                    variety.get(count_var).add(var[i]);
//            }
//        }
//    }
//    int corrAns=0;
//    public void finish(int a){
//        System.out.println("\nCorrect Answers: "+a+"/6 ("+(a*100)/6+".0%) ");}
//
//    String choice;
//    public void start(){
//        ShuffleId();
//        for(int i=0;i<6;i++) {
//            ShuffleQuestion(i);
//            if(and==0){
//            System.out.println((i+1)+". "+test.toString()+"\n--------------------------------------");}
//            else{
//            System.out.println((i+1)+". "+fill.toString()+"\n--------------------------------------");}
//            System.out.print("Enter the correct choice: ");
//            QuizFormat();
//        }
//        finish(corrAns);
//    }
//    public void QuizFormat(){
//        while (true) {
//            try {
//                choice = in.next();
//                if(and==0&&!(choice.equals("A")||choice.equals("B")||choice.equals("C")||choice.equals("D"))) throw new InvalidQuizFormatException();
//            }
//            catch (InvalidQuizFormatException ex1) {
//                System.out.print("Invalid choice! Try again(Ex: A, B, ...): ");
//            continue;}
//            if(and==0&&choice.equals(test.getAnswer()))
//            {System.out.println("Correct!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");corrAns++;}
//            else if(and==1&&choice.equals(fill.getAnswer()))
//            {System.out.println("Correct!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");corrAns++;}
//            else{System.out.println("Incorrect!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");}
//            break;
//        }



    }
  //  }
