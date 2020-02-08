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

    public int root, third, fifth, seventh;

    private int previous;

    private int[] funk = {50, 25, 17};

    private int[] streak = {0, 0, 0};

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

    public Chord(){
        previous = 0;

        root = 1;
        third = 3;
        fifth = 5;
        seventh = 7;

    }

    //mutate chances of a chord tendency being selected
    private void mutate(){
        if(streak[0]  > 8) {
            funk[0] -= 10;
            funk[1] += 5;
            funk[2] += 5;
        }
        if(streak[1] > 4) {
            funk[0] += 3;
            funk[1] -= 6;
            funk[2] += 3;
        }
        if(streak[2] > 2) {
            funk[0] += 1;
            funk[1] += 1;
            funk[2] -= 2;
        }
    }

    //generated the next chord
    public void next(){

        Random ran = new Random();
        int chordSeed, nextTendency;

        //use chordSeed to select tendency
        chordSeed = ran.nextInt(100);

        if(chordSeed < funk[0]) {
            nextTendency = 0;
        }
        else if(chordSeed < (funk[1]+funk[0])) {
            nextTendency = 1;
        }
        else if(chordSeed < (funk[2]+funk[1]+funk[0])) {
            nextTendency = 2;
        }
        else {
            nextTendency = 3;
        }

        //select the next chord using prev and nextTendency

        int nextChord = tendencies[previous][nextTendency];

        root = notes[nextChord][0];
        third = notes[nextChord][1];
        fifth = notes[nextChord][2];
        seventh = notes[nextChord][3];

        mutate();
    }
}