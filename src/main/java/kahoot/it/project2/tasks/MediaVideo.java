package kahoot.it.project2.tasks;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class MediaVideo extends Application {
    Media media;
    MediaPlayer player;
    MediaView mediaView;
    Slider time = new Slider();
    Slider vol = new Slider();
    Button PlayButton = new Button("||");
    Label volume = new Label("Volume: ");
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane=new BorderPane();
        media=new Media(new File("C:\\Users\\asus\\Videos\\Captures\\videoplayback.mp4").toURI().toString());
        player=new MediaPlayer(media);
        mediaView=new MediaView(player);
        player.setAutoPlay(true);
        HBox hBox=new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 10, 5, 10));
        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100);
        HBox.setHgrow(time,Priority.SOMETIMES);
        PlayButton.setPrefWidth(30);
        hBox.getChildren().addAll(PlayButton,time,volume,vol);
        pane.setBottom(hBox);
        pane.setCenter(mediaView);

        PlayButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                MediaPlayer.Status status = player.getStatus();
                if (status == status.PLAYING) {
                    if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
                        player.seek(player.getStartTime());
                        player.play();
                    }
                    else {
                        player.pause();
                        PlayButton.setText(">");
                    }
                }
                if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
                    player.play();
                    PlayButton.setText("||");
                }
            }
        });
        player.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov)
            {
                updatesValues();
            }
        });
        time.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (time.isPressed()) {
                    player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
                }
            }
        });
        vol.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (vol.isPressed()) {
                    player.setVolume(vol.getValue() / 100);
                }
            }
        });


        Scene scene = new Scene(pane, 750, 550);
        primaryStage.setTitle("MediaDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   public void updatesValues() {
        Platform.runLater(new Runnable() {
            public void run() {
                time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
            }
        });
    }
}
