package com.example.ukeselfie;

import java.util.Random;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayer{

    int chord1;
    int chord2;
    int chord3;
    int chord4;


    //MediaPlayer[] mpList = {aNote, bNote, bbNote, cNote, dNote, eNote, ebNote, fNote, gNote};
    ArrayList<MediaPlayer> mpList = new ArrayList<MediaPlayer>(8);

    public MusicPlayer(AppCompatActivity context, List<Integer> soundList){

        final MediaPlayer aNote = MediaPlayer.create(context, soundList.get(0));
        final MediaPlayer bNote = MediaPlayer.create(context, soundList.get(1));
        final MediaPlayer bbNote = MediaPlayer.create(context, soundList.get(2));
        final MediaPlayer cNote = MediaPlayer.create(context, soundList.get(3));
        final MediaPlayer dNote = MediaPlayer.create(context, soundList.get(4));
        final MediaPlayer eNote = MediaPlayer.create(context, soundList.get(5));
        final MediaPlayer fNote = MediaPlayer.create(context, soundList.get(6));
        final MediaPlayer gNote = MediaPlayer.create(context, soundList.get(7));
        //trap = MediaPlayer.create(context, R.raw.trap);

        mpList.add(aNote);
        mpList.add(bNote);
        mpList.add(bbNote);
        mpList.add(cNote);
        mpList.add(dNote);
        mpList.add(eNote);
        mpList.add(fNote);
        mpList.add(gNote);

    }

    public void loadChords(int[] chords){

        chord1 = chords[0];
        chord2 = chords[1];
        chord3 = chords[2];
        chord4 = chords[3];

    }

    public void playChords(){
        mpList.get(chord1).start();
        mpList.get(chord2).start();
        mpList.get(chord3).start();
        mpList.get(chord4).start();
    }

    public void release(){
        MediaPlayer np = new MediaPlayer();
        np.release();

    }

}
