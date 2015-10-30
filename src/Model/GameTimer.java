package model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    }

    public void setDuration(int duration){
        this.duration = duration;
        this.timeSeconds.setValue(duration);
    }

    public void stopTimer(){
        timeline.stop();
    }

    public Timeline getTimeline(){
        return this.timeline;
    }

    public int getTime() {
        return timeSeconds.intValue();

    }
}