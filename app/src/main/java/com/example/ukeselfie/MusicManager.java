package com.example.ukeselfie;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

public class MusicManager implements Runnable {

    private boolean running;
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
        running = false;
        playing = false;
        ukulelePlayer = new MusicPlayer(context, Sounds.CHORDS);
//        drumPlayer = new MusicPlayer(context, drumList);
    }

    @Override
    public void run() {
        running = true;
        playing = true;
        int[] weights = {50,25,17};
        while (running) {
            if (playing) {
                ukulelePlayer.playChords(chordLogic.next(weights));
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                ukulelePlayer.release();
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
    public void start() {
        playing = true;
    }

    public void stop() {
        playing = false;
//        ukulelePlayer.release();
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
