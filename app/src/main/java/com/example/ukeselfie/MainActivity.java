package com.example.ukeselfie;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button bt;

    private int[] rand4(){
        Random rand = new Random();

        int upperbound = 9;

        int num1 = rand.nextInt(upperbound);
        int num2 = rand.nextInt(upperbound);
        int num3 = rand.nextInt(upperbound);
        int num4 = rand.nextInt(upperbound);


        int[] ret = {num1, num2, num3, num4};
        return ret;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MusicPlayer player = new MusicPlayer(this);
        final MusicManager manager = new MusicManager(this);

        bt = (Button)findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //mp.start();
//                player.loadChords(rand4());
//                player.playChords();
                try {
                    manager.start();
                } catch (InterruptedException e) {
                    Log.d("exception", e.getMessage());
                }
            }
        }
        );


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
