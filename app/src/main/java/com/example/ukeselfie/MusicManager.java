package com.example.ukeselfie;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

public class MusicManager implements Runnable {

    private boolean playing;

    private double interval;
    private int count;

    private MusicPlayer ukulelePlayer;
    private MusicPlayer drumPlayer;

    private Chord chordLogic;

    /**
     * Manage playback (mood, tempo, rhythm)
     * using `Chord` and keep time.
     */
    public MusicManager(AppCompatActivity context) {
        chordLogic = new Chord();
        playing = false;
        ukulelePlayer = new MusicPlayer(context, Sounds.CHORDS);
//        drumPlayer = new MusicPlayer(context, drumList);
    }

    @Override
    public void run() {
        playing = true;
        int[] weights = {50,25,17};
        while (playing) {
            ukulelePlayer.playChords(chordLogic.next(weights));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.d("exception", e.getMessage());
            }
        }
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
        playing = true;
        Chord chrd = new Chord();
        int[] weights = {50,25,17};
        int nextChord = chrd.next(weights);
        ukulelePlayer.playChords(nextChord);
    }

    public void stop() {
        ukulelePlayer.release();
        // drumPlayer.release();
        playing = false;
    }

    public void toggle() throws InterruptedException {
        Log.d("playing?", this.playing + "");
        if (this.playing) {
            this.stop();
        } else {
            this.start();
        }
    }

    private void setTempo(double bpm) {
    }

}
