package Model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.control.Label;

/**
 * Created by Shannor on 9/30/2015.
 * Timer class to keep track of each players turn
 */
public class GameTimer {
    private Timeline timeline;
    private int duration;
    private IntegerProperty timeSeconds;

    public GameTimer(int duration){
        this.duration = duration;
        timeline = new Timeline();
        timeSeconds = new SimpleIntegerProperty(duration);
    }

    public void setLabel(Label label){
        label.textProperty().bind(timeSeconds.asString());
    }

    public void startTimer(){
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(duration+1),
                        new KeyValue(timeSeconds,0)));
        timeline.playFromStart();

        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Set something to actually happen here
                System.out.println("Time is up");
            }
        });
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public int getCurrentTime(){
        return timeSeconds.getValue();
    }

    public void resetTimer(){
        timeline.stop();
        int toReset = timeSeconds.getValue();
        setDuration(toReset);
        startTimer();
    }
}
