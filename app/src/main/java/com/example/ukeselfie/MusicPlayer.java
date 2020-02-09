package com.example.ukeselfie;

import java.util.Random;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MusicPlayer{

    int chord1;
    int chord2;
    int chord3;
    int chord4;


    //MediaPlayer[] mpList = {aNote, bNote, bbNote, cNote, dNote, eNote, ebNote, fNote, gNote};
    ArrayList<MediaPlayer> mpList = new ArrayList<MediaPlayer>(9);

    public MusicPlayer(AppCompatActivity context){

        final MediaPlayer aNote = MediaPlayer.create(context, R.raw.a_piano);
        final MediaPlayer bNote = MediaPlayer.create(context, R.raw.b_piano);
        final MediaPlayer bbNote = MediaPlayer.create(context, R.raw.bb_piano);
        final MediaPlayer cNote = MediaPlayer.create(context, R.raw.c_piano);
        final MediaPlayer dNote = MediaPlayer.create(context, R.raw.d_piano);
        final MediaPlayer eNote = MediaPlayer.create(context, R.raw.e_piano);
        final MediaPlayer ebNote = MediaPlayer.create(context, R.raw.eb_piano);
        final MediaPlayer fNote = MediaPlayer.create(context, R.raw.f_piano);
        final MediaPlayer gNote = MediaPlayer.create(context, R.raw.g_piano);
        //trap = MediaPlayer.create(context, R.raw.trap);

        mpList.add(aNote);
        mpList.add(bNote);
        mpList.add(bbNote);
        mpList.add(cNote);
        mpList.add(dNote);
        mpList.add(eNote);
        mpList.add(ebNote);
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

}
