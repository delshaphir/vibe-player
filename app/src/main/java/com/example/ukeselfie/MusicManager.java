package com.example.ukeselfie;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicManager {

    private boolean playing;

    private double interval;
    private int count;

    private MusicPlayer ukulelePlayer;
    private MusicPlayer drumPlayer;

    /**
     * Manage playback (mood, tempo, rhythm)
     * using `Chord` and keep time.
     */
    public MusicManager(AppCompatActivity context) {
        playing = true;
        ukulelePlayer = new MusicPlayer(context, Sounds.CHORDS);
//        drumPlayer = new MusicPlayer(context, drumList);
    }

    /// Generates a random chord (for testing purposes)
    private int[] randChord() {
        Random rand = new Random();
        int[] chord = new int[4];
        for (int i = 0; i < chord.length; i++) {
            chord[i] = rand.nextInt(8);
        }
        return chord;
    }

    /// Starts keeping time
    public void start() throws InterruptedException {
        while (playing) {
            Chord chrd = new Chord();
            int[] weights = {50,25,17};
            int nextChord = chrd.next(weights);
            ukulelePlayer.playChords(nextChord);
            Thread.sleep(1000);
        }
    }

    public void stop() {
        ukulelePlayer.release();
        // drumPlayer.release();
        playing = false;
    }

    public void toggle() {
        if (playing) {
            this.stop();
        } else {
            try {
                this.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setTempo(double bpm) {
    }

}
