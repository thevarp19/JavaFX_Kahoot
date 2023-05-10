package kahoot.it.project2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static javafx.scene.text.TextAlignment.CENTER;


public class QuizMaker extends Application {
    Test test=new Test();
    FillIn fill=new FillIn();
    ArrayList<String> questions=new ArrayList<>();
    ArrayList<ArrayList<String>> variety=new ArrayList<>();
    ArrayList<String> correctAnswer=new ArrayList<>();
    ArrayList<Integer>shuffleId=new ArrayList<>();
    boolean and=false;
    int width=605;
    int height=450;
    Scene[] scenes;
    public QuizMaker() throws URISyntaxException {
    }
    public void loadFromFile(String path) throws IOException {
        File file = new File(path);
        Scanner in=new Scanner(file);
        String line;
        int count_var=-1;
        while(in.hasNextLine()){
            line=in.nextLine();
            if(line.contains("-")){continue;}
            if(line.contains("?")) {
                questions.add(line);
                variety.add(new ArrayList<>());
                count_var++;
            }if(line.contains(")")){
                String []var=line.split(" *\\) *");
                correctAnswer.add(var[0]);
                for(int i=0;i<var.length;i++)
                    variety.get(count_var).add(var[i]);
            }
        }
        scenes=new Scene[questions.size()];
    }
    public void ShuffleId(){
        for(int i=0;i<questions.size();i++){
            shuffleId.add(i);}
        Collections.shuffle(shuffleId);
    }
        public void addQuestion(int index) {
            if (variety.get(index).size() == 4) {
                test.setDescription(questions.get(index));
                test.setOptions(variety.get(index));
                test.setAnswer(correctAnswer.get(index));
                and = true;
            } else {
                fill.setDescription(questions.get(index));
                fill.setAnswer(correctAnswer.get(index));
                and = false;
            }
        }
    Label time;
    Stage stage;
    int millis;
    int mins;
    int secs;
    int INDEX=0;
    TextField[] textField;
    Timeline timeline=new Timeline(new KeyFrame(Duration.millis(1),e->{
        if(millis == 1000) {
            secs++;
            millis = 0;
        }
        if(secs == 60) {
            mins++;
            secs = 0;
        }
        millis++;
        time.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
                + (((secs/10) == 0) ? "0" : "") + secs + "");
    }));
    Button bR;
    Button bL;
    RadioButton []rb;
    Media media = new Media(getClass().getResource("/kahoot_music.mp3").toURI().toString());
    MediaPlayer mediaPlayer=new MediaPlayer(media);

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.getIcons().add(new Image(new FileInputStream("src/img/logo.png")));
        stage.setTitle("Project 2");
        stage.setScene(FileChoose());
        stage.show();
    }
    private Label getQuestion(int idx){
      Label labelTest = new Label((idx+1) + ") " + (and ? test : fill).toString());
            labelTest.setTextAlignment(CENTER);
            labelTest.setAlignment(Pos.CENTER);
            labelTest.setWrapText(true);
            labelTest.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
     return labelTest;
    }
    private Scene FileChoose() throws Exception {
        FileChooser f1=new FileChooser();
        f1.setInitialDirectory(new File("C:\\Users\\asus\\IdeaProjects\\Project2"));
        StackPane pane=new StackPane();
        Image image=new Image(new FileInputStream("src/img/background.jpg"));
        ImageView imageView=new ImageView(image);
        imageView.setFitWidth(605);
        imageView.setFitHeight(450);
        Button button=new Button("Choose a file");
        button.setOnAction(ev -> {
            File file = f1.showOpenDialog(stage);
            if (file != null) {
                try {
                    loadFromFile(file.getAbsolutePath());
                    ShuffleId();
                    time=new Label();
                    textField=new TextField[scenes.length];
                    rb=new RadioButton[scenes.length];
                    for (int i=0;i< questions.size();i++) {
                        addQuestion(shuffleId.get(i));
                        if(and){
                        scenes[i]=TestQuestion(i);}
                        else{
                        scenes[i]=FillQuestion(i);}
                    }
                    stage.setScene(scenes[0]);
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    mediaPlayer.play();
                    timeline.play();
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }
            }
        });
        pane.getChildren().addAll(imageView,button);
        Scene scene=new Scene(pane,width,height);
        return scene;
    }
    ToggleGroup gr=new ToggleGroup();
    private Scene TestQuestion (int idx) throws Exception {
    INDEX=idx;
        RadioButton red=new RadioButton(test.labels.get(0));
        red.setWrapText(true);
        red.setTextAlignment(CENTER);

        RadioButton blue=new RadioButton(test.labels.get(1));
        blue.setWrapText(true);
        blue.setTextAlignment(CENTER);
        RadioButton orange=new RadioButton(test.labels.get(2));
        orange.setWrapText(true);
        orange.setTextAlignment(CENTER);

        RadioButton green=new RadioButton(test.labels.get(3));
        green.setWrapText(true);
        green.setTextAlignment(CENTER);

        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,15);
        red.setFont(font);blue.setFont(font);orange.setFont(font);green.setFont(font);
        red.setTextFill(Color.WHITE);blue.setTextFill(Color.WHITE);orange.setTextFill(Color.WHITE);green.setTextFill(Color.WHITE);
        red.setToggleGroup(gr);blue.setToggleGroup(gr);orange.setToggleGroup(gr);green.setToggleGroup(gr);
        BorderPane pane = new BorderPane();
        Rectangle[] rectangle = new Rectangle[4];
        for (int i = 0; i < 4; i++) {
            rectangle[i] = new Rectangle(300, 70);
            pane.getChildren().addAll(rectangle[i]);
        }
        rectangle[0].setX(2);rectangle[0].setY(306);rectangle[0].setFill(Color.RED);rectangle[0].setArcHeight(10);rectangle[0].setArcWidth(10);
        rectangle[1].setX(304);rectangle[1].setY(306);rectangle[1].setFill(Color.BLUE);rectangle[1].setArcHeight(10);rectangle[1].setArcWidth(10);
        rectangle[2].setX(2);rectangle[2].setY(378);rectangle[2].setFill(Color.ORANGE);rectangle[2].setArcHeight(10);rectangle[2].setArcWidth(10);
        rectangle[3].setX(304);rectangle[3].setY(378);rectangle[3].setFill(Color.GREEN);rectangle[3].setArcHeight(10);rectangle[3].setArcWidth(10);

        Image image = new Image(new FileInputStream("src/img/logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(210);
        imageView.setFitWidth(320);
        imageView.setX(140);
        imageView.setY(70);

        time=new Label();
        bR=new Button("❯");
        bL=new Button("❮");
        bR.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        bL.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        VBox key=new VBox(20);
        key.setAlignment(Pos.TOP_CENTER);

        FlowPane flow=new FlowPane(4,4);
        flow.setAlignment(Pos.TOP_CENTER);
        flow.getChildren().addAll(new StackPane(rectangle[0],red),new StackPane(rectangle[1],blue),new StackPane(rectangle[2],orange),new StackPane(rectangle[3],green));
        if(idx!=0){bL.setVisible(true);}
        else{bL.setVisible(false);}
        if(idx != questions.size()-1){bR.setText("❯");}
        else bR.setText("✔");
        bR.setOnAction(ev->{
            rb[idx]=(RadioButton)gr.getSelectedToggle();
            if(rb[idx]!=null){
              //  System.out.println(rb[idx].getText()+" "+correctAnswer.get(shuffleId.get(idx)));
                if(correctAnswer.get(shuffleId.get(idx)).equals(rb[idx].getText())){
                    countTest++;
                }
            }
            try {
                if(idx == questions.size()-1){
                    mediaPlayer.stop();
                    timeline.pause();
                    stage.setScene(finalStage());
                }else {stage.setScene(scenes[idx+1]);}
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        bL.setOnAction(ev->{
            rb[idx]=(RadioButton)gr.getSelectedToggle();
            if(rb[idx]!=null){
                try {
                    if(!correctAnswer.get(shuffleId.get(idx)).equals(rb[idx].getText())||correctAnswer.get(shuffleId.get(idx)).equals(rb[idx].getText())){
                    countTest--;}
                    stage.setScene(scenes[idx-1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }});
            pane.setCenter(key);
            pane.setRight(new StackPane(bR));
            pane.setLeft(new StackPane(bL));
            pane.setBottom(flow);
            key.getChildren().addAll(getQuestion(idx),time,imageView);
        Scene scene=new Scene(pane,width,height);
        scene.getRoot().setStyle("\n" +
                "-fx-base: rgb(93,22,138);\n" +
                "-fx-background: rgb(93,22,138);\n"+
                "-fx-background-color: linear-gradient(to top, -fx-base, derive(-fx-base,30%));\n"
                );
        return scene;
    }
    public Scene FillQuestion(int idx) throws Exception {
        INDEX=idx;
        BorderPane pane = new BorderPane();
        Text text = new Text("Type your answer here: ");
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        text.setFont(font);
        textField[idx] = new TextField();
        textField[idx].setAlignment(Pos.CENTER);
        textField[idx].setPrefColumnCount(30);
        textField[idx].setMinWidth(380);
        textField[idx].setMaxWidth(380);

        Image logo = new Image(new FileInputStream("src/img/k.png"));
        ImageView imageLogo = new ImageView(logo);
        imageLogo.setFitHeight(25);
        imageLogo.setFitWidth(45);
        Image image = new Image(new FileInputStream("src/img/fillin.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(350);
        imageView.setFitHeight(180);

        time=new Label();
        bR=new Button("❯");
        bL=new Button("❮");
        bR.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        bL.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));

        if (idx != 0) {
            bL.setVisible(true);
        } else {
            bL.setVisible(false);}
        bR.setOnAction(ev -> {
            if(textField[idx]!=null){
               // System.out.println(correctAnswer.get(shuffleId.get(idx))+" "+textField[idx].getText());
                if (correctAnswer.get(shuffleId.get(idx)).equals(textField[idx].getText()))
                    countFill++;}
            try {
                stage.setScene(scenes[idx+1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        bL.setOnAction(ev -> {
            if (idx > 0){
                try {
                    if(textField[idx]!=null){
                    if(correctAnswer.get(shuffleId.get(idx)).equals(textField[idx].getText()) ){
                        countFill--;}}
                    stage.setScene(scenes[idx-1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if(idx != questions.size()-1){bR.setText("❯");
        }else{
            bR.setText("✔");
                bR.setOnAction(e->{
                mediaPlayer.stop();
                timeline.pause();
                        try {
                            stage.setScene(finalStage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
            );}
        VBox vBox =new VBox(25);
        HBox hBox=new HBox(imageLogo, getQuestion(idx));
        hBox.setAlignment(Pos.TOP_CENTER);
        vBox.setAlignment(Pos.TOP_CENTER);
        pane.setCenter(vBox);
        pane.setLeft(new StackPane(bL));
        pane.setRight(new StackPane(bR));
        vBox.getChildren().addAll(hBox,time,imageView,text,textField[idx]);
        Scene scene=new Scene(pane,width,height);
        scene.getRoot().setStyle("\n" +
                "-fx-base: rgb(93,22,138);\n" +
                "-fx-background: rgb(93,22,138);\n" +
                "-fx-control-inner-background:  rgb(93,22,138);\n" +
                "-fx-background-color: linear-gradient(to top, -fx-base, derive(-fx-base,30%));\n" +
                "-fx-background-color: linear-gradient(to bottom, -fx-base, derive(-fx-base,30%));\n" +
                "-fx-background-color:\n" +
                "linear-gradient(to bottom, derive(-fx-base,+50%), derive(-fx-base,-40%), derive(-fx-base,-20%));\n" +
                "-fx-background-color: transparent;\n" +
                "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n" +
                "-fx-color: -fx-hover-base;\n" +
                "-fx-table-cell-border-color:derive(-fx-base,+10%);\n" +
                "-fx-table-header-border-color:derive(-fx-base,+20%);\n" +
                "-fx-border-color: transparent -fx-base transparent -fx-base;\n" +
                "-fx-background-color: transparent, derive(-fx-base,20%);\n" +
                "-fx-background-insets: 0, 0 1 0 1;\n" +
                "-fx-background-color: radial-gradient(radius 100%, derive(-fx-base,20%), derive(-fx-base,-20%));\n" +
                "-fx-text-fill: #5d168a;\n");
        return scene;
    }
    int countTest=0;
    int countFill=0;
    private Scene finalStage() throws Exception{
        BorderPane pane=new BorderPane();
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,15);
        Font font2 = Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR,12);
        Text text=new Text("Your Result: ");
        text.setFont(font);
        text.setStyle("-fx-text-fill: white");
        Button show=new Button("Show answers");
        show.setStyle("-fx-background-radius: 5;-fx-font-weight: bold;-fx-text-fill: white; -fx-background-color: #0606d5;-fx-font-size: 15;-fx-stroke:#003dff;");
        show.setMaxWidth(250);
        show.setMinWidth(250);

        Button close=new Button("Close test");
        close.setStyle("-fx-background-radius: 5;-fx-font-weight: bold;-fx-text-fill: white; -fx-background-color: #e80606;-fx-font-size: 15;-fx-stroke:#e30d33;");
        close.setMaxWidth(250);
        close.setMinWidth(250);
        close.setOnAction(e->{
            stage.close();
        });
        Label percent=new Label(((100*(countTest+countFill))/scenes.length)+"%");
        Label numCorr=new Label("Number of correct answers: "+(countTest+countFill)+"/"+scenes.length);
        Label lastTime=new Label("Finished in "+time.getText());
        percent.setFont(font2);
        numCorr.setFont(font2);
        numCorr.setWrapText(true);
        lastTime.setFont(font2);
        Image result=new Image(new FileInputStream("src/img/result.png"));
        ImageView imageView=new ImageView(result);
        imageView.setFitWidth(500);
        imageView.setFitHeight(250);
        VBox vBox =new VBox(15);
        vBox.setAlignment(Pos.TOP_CENTER);
        pane.setCenter(vBox);
        vBox.getChildren().addAll(text,percent,numCorr,lastTime,show,close);
        pane.setCenter(vBox);
        pane.setBottom(new StackPane(imageView));
        Scene scene=new Scene(pane,width,height);
        scene.getRoot().setStyle("\n" +
                "-fx-base: rgb(93,22,138);\n" +
                "-fx-background: rgb(97,19,143);\n" +
                "-fx-control-inner-background:  rgb(97,19,143);\n" +
                "-fx-background-color: linear-gradient(to top, -fx-base, derive(-fx-base,30%));\n" +
                "-fx-background-color: linear-gradient(to bottom, -fx-base, derive(-fx-base,30%));\n" +
                "-fx-background-color:\n" +
                "linear-gradient(to bottom, derive(-fx-base,+50%), derive(-fx-base,-40%), derive(-fx-base,-20%));\n" +
                "-fx-background-color: transparent;\n" +
                "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n" +
                "-fx-color: -fx-hover-base;\n" +
                "-fx-table-cell-border-color:derive(-fx-base,+10%);\n" +
                "-fx-table-header-border-color:derive(-fx-base,+20%);\n" +
                "-fx-border-color: transparent -fx-base transparent -fx-base;\n" +
                "-fx-background-color: transparent, derive(-fx-base,20%);\n" +
                "-fx-background-insets: 0, 0 1 0 1;\n" +
                "-fx-background-color: radial-gradient(radius 100%, derive(-fx-base,20%), derive(-fx-base,-20%));\n" +
                "-fx-text-fill: #5d168a;\n");
        return scene;
    }


}
