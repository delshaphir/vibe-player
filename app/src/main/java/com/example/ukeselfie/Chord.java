package com.example.ukeselfie;
import java.util.Random;

/*
 * new object
 *  Chord ch = new Chord;
 * next chord
 *  ch.next();
 * notes of the chord are represented by numbers corresponding to the major scale
 * root is the lowest seventh is the highest
 *  int ch.root
 *  int ch.third
 *  int ch.fifth
 *  int ch.seventh
 */
public class Chord {

    int root, third, fifth, seventh;

    private int previous;

    //scale degrees of the sevenc chords of a major scale
    private int[][] notes = {
            {1,3,5,7},
            {2,4,6,8},
            {3,5,7,9},
            {4,6,8,10},
            {5,7,9,11},
            {6,8,10,12},
            {7,9,11,13}
    };

    //tendencies to go to different chords based of the starting chord ranked in order of safety
    private int[][] tendencies = {
            {4,2,5,6},
            {0,3,4,6},
            {4,0,1,6},
            {4,0,1,6},
            {0,5,2,6},
            {4,3,6,2},
            {0,4,2,6}
    };

    //weights applied to the random number that picks the next chord
    private int[] weights= {50,25,17,8};

    public Chord(){
        previous = 0;

        root = 1;
        third = 3;
        fifth = 5;
        seventh = 7;
    }

    public void next(){

        Random ran = new Random();
        int chordSeed, nextTendency;

        //use chordSeed to select tendency
        chordSeed = ran.nextInt(100);

        if(chordSeed < 50) { nextTendency = 0;}
        else if(chordSeed < 75) {nextTendency = 1;}
        else if(chordSeed < 92) { nextTendency = 2;}
        else { nextTendency = 3;}

        //select the next chord using prev and nextTendency

        int nextChord = tendencies[previous][nextTendency];

        root = notes[nextChord][0];
        third = notes[nextChord][1];
        fifth = notes[nextChord][2];
        seventh = notes[nextChord][3];
    }
}