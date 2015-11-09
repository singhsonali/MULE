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
    /**
     * Timeline class that keeps track of the time.
     */
    private final Timeline timeline;
    /**
     * int to hold how long the timer should run.
     */
    private transient int duration;
    /**
     * FX class that displays the time on a Label in the game.
     */
    private transient IntegerProperty timeSeconds;

    /**
     * GameTimer constructor.
     * @param time amount of time the timer should run.
     */
    public GameTimer(final int time) {
        this.duration = time;
        timeline = new Timeline();
        timeSeconds = new SimpleIntegerProperty(time);
    }

    /**
     * Bind the timer to the label being passed in.
     * @param label label in the game to display the timer.
     */
    public final void setLabel(final Label label) {
        label.textProperty().bind(timeSeconds.asString());
    }
    /**
     * Starts the timer based on the information given.
     * Stops the timer when it gets to zero.
     */
    public final void startTimer() {
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(duration + 1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }
    /**
     * Sets the duration to the value passed in.
     * @param time new duration that is desired.
     */
    public final void setDuration(final int time) {
        this.duration = time;
        this.timeSeconds.setValue(duration);
    }

    /**
     * Method to stop the timer from running.
     */
    public final void stopTimer() {
        timeline.stop();
    }

    /**
     * Returns a reference to the timeline.
     * Used to setup the event handler when timer hits zero.
     * @return timeline
     */
    public final Timeline getTimeline() {
        return this.timeline;
    }

    /**
     * Returns the integer representation of the current timer time.
     * @return (int)timeSeconds
     */
    public final int getTime() {
        return timeSeconds.intValue();

    }
}
