package com.example.ukeselfie;

public class MusicManager {

    private boolean playing;

    private double interval;
    private int count;

    /**
     * Manage playback (mood, tempo, rhythm)
     * using `Chord` and keep time.
     */
    public MusicManager() {
        playing = true;
    }

    public void start() {
        // pick a starting chord
        // start keeping time
        while (playing) {

        }
    }

    public void stop() {
        playing = false;
    }

    private void setTempo(double bpm) {
    }

}
