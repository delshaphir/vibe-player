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

    public MusicPlayer(AppCompatActivity context, List<Integer> soundList){

        for (Integer id: soundList) {
            mediaPlayers.add(MediaPlayer.create(context, id));
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

    public void release(){
        MediaPlayer np = new MediaPlayer();
        np.release();

    }

}
