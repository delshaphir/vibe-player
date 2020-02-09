package com.example.ukeselfie;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
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
        ArrayList<Integer> ukuList = new ArrayList<Integer>(8);
        ukuList.add(R.raw.a);
        ukuList.add(R.raw.b);
        ukuList.add(R.raw.c);
        ukuList.add(R.raw.cc);
        ukuList.add(R.raw.d);
        ukuList.add(R.raw.e);
        ukuList.add(R.raw.f);
        ukuList.add(R.raw.g);

        playing = true;
        ukulelePlayer = new MusicPlayer(context, ukuList);
//        drumPlayer = new MusicPlayer(context);
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
            int[] chord = randChord();
            ukulelePlayer.loadChords(chord);
            ukulelePlayer.playChords();
            Thread.sleep(1000);
        }
    }

    public void stop() {
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
