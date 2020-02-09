package com.example.ukeselfie;

import java.util.Random;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayer{

    int chord1;
    int chord2;
    int chord3;
    int chord4;


    //MediaPlayer[] mpList = {aNote, bNote, bbNote, cNote, dNote, eNote, ebNote, fNote, gNote};
    List<MediaPlayer> mediaPlayers = new ArrayList<MediaPlayer>();

    public MusicPlayer(AppCompatActivity context, List<int[]> soundList){

        for (int[] x: soundList) {
            //MediaPlayer temp = new MediaPlayer(context, x[0]);

            mediaPlayers.add(MediaPlayer.create(context, x[0]));
            mediaPlayers.add(MediaPlayer.create(context, x[1]));
            mediaPlayers.add(MediaPlayer.create(context, x[2]));
            //for (Integer id : x) {
            //    mediaPlayers.add(MediaPlayer.create(context, id));
            //}
        }

    }

    public void loadChords(int[] chords){
        chord1 = chords[0];
        chord2 = chords[1];
        chord3 = chords[2];
        chord4 = chords[3];
    }

    public void playChords(int chordf){
        mediaPlayers.get(chordf).start();
        Log.d("tag", String.valueOf(chordf));
    }

    public void stop() {
        for (MediaPlayer player: mediaPlayers) {
            player.stop();
        }
    }

    public void release(){
        for (MediaPlayer player: mediaPlayers) {
            player.release();
        }
    }


    private class MelodiePicker {




    }

}


