package com.example.ukeselfie;

import android.media.AudioManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Button> btns;

    Hashtable<Integer, String> notes;
    MediaPlayer mp;
    String uriRoot = "android.resource://" + getPackageName() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        btns = Arrays.asList(
                (Button) findViewById(R.id.a_btn),
                (Button) findViewById(R.id.b_btn),
                (Button) findViewById(R.id.c_btn),
                (Button) findViewById(R.id.d_btn),
                (Button) findViewById(R.id.e_btn),
                (Button) findViewById(R.id.f_btn),
                (Button) findViewById(R.id.g_btn));
        notes = new Hashtable();
        notes.put(btns.get(0).hashCode(), "a_piano");
        notes.put(btns.get(1).hashCode(), "b_piano");
        notes.put(btns.get(2).hashCode(), "c_piano");
        notes.put(btns.get(3).hashCode(), "d_piano");
        notes.put(btns.get(4).hashCode(), "e_piano");
        notes.put(btns.get(5).hashCode(), "f_piano");
        notes.put(btns.get(6).hashCode(), "g_piano");
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        for (final Button b: btns) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playSound(Uri.parse(uriRoot + notes.get(b.hashCode())));
                }
            });
        }
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

    public void playSound(Uri noteUri) {
        android.util.Log.d("noteUri", noteUri.toString());
        try {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.setDataSource(getApplicationContext(), noteUri);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
