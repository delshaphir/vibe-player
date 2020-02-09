package com.example.ukeselfie;

import java.util.Random;


/*
 * new object
 *  Chord ch = new Chord;
 * next chord returns list with scale degrees
 *  int[] ch.next(int[] weights);
 * a good starter value for weights is {50,25,17}
 * which corresponds to playing the simplest option 50 % of the timem
 *  the second simplest 25% of the time etc.
 *
 * Add Chord.SIMPLE, Chord.MEDIUM, or Chord.COMPLEX to modify weights
 *
 */
public class Chord {

    static final int[] SIMPLE = {10, -5, -5};
    static final int[] MEDIUM = {-5, 10, -5};
    static final int[] COMPLEX = {-5, -5, 10};

    public int previous = 0;

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

    public Chord(){}

    //generated the next chord
    public int[] next(int[] weights){

        int chordSeed, nextTendency;
        Random rand = new Random();

        //use chordSeed to select tendency
        chordSeed = rand.nextInt(100);

        if(chordSeed < weights[0]) {
            nextTendency = 0;
        }
        else if(chordSeed < (weights[1]+weights[0])) {
            nextTendency = 1;
        }
        else if(chordSeed < (weights[2]+weights[1]+weights[0])) {
            nextTendency = 2;
        }
        else {
            nextTendency = 3;
        }

        //select the next chord using prev and nextTendency

        int nextChord = tendencies[previous][nextTendency];

        previous = nextChord;

        return notes[nextChord];


    }
}