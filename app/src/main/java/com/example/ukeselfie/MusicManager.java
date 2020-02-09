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
        ArrayList<Integer> ukuList = new ArrayList<Integer>(9);
        ukuList.add(R.raw.a_piano);
        ukuList.add(R.raw.b_piano);
        ukuList.add(R.raw.bb_piano);
        ukuList.add(R.raw.c_piano);
        ukuList.add(R.raw.d_piano);
        ukuList.add(R.raw.e_piano);
        ukuList.add(R.raw.eb_piano);
        ukuList.add(R.raw.f_piano);
        ukuList.add(R.raw.g_piano);

        playing = true;
        ukulelePlayer = new MusicPlayer(context, ukuList);
//        drumPlayer = new MusicPlayer(context);
    }

    /// Generates a random chord (for testing purposes)
    private int[] randChord() {
        Random rand = new Random();
        int[] chord = new int[4];
        for (int i = 0; i < chord.length; i++) {
            chord[i] = rand.nextInt(9);
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
