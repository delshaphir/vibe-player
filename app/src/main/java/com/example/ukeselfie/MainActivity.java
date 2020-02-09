package com.example.ukeselfie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.app.ActivityManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bt;
    //for CameraIntent
    private int PICK_IMAGE_REQUEST = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    //setUp for tensorflow
    public static final int INPUT_SIZE = 224;
    public static final int IMAGE_MEAN = 128;
    public static final float IMAGE_STD = 128.0f;
    public static final String INPUT_NAME = "input";
    public static final String OUTPUT_NAME = "final_result";
    public static final String MODEL_FILE = "file:///android_asset/rounded_graph.pb";
    public static final String LABEL_FILE = "file:///android_asset/retrained_labels.txt";

    public Classifier classifier;
    public Executor executor = Executors.newSingleThreadExecutor();
    public TextView TV1;
    public ImageView iV1;

    private int[] rand4(){
        Random rand = new Random();

        int upperbound = 8;

        int num1 = rand.nextInt(upperbound);
        int num2 = rand.nextInt(upperbound);
        int num3 = rand.nextInt(upperbound);
        int num4 = rand.nextInt(upperbound);


        int[] ret = {num1, num2, num3, num4};
        return ret;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MediaPlayer fight = MediaPlayer.create(this, R.raw.fightsong);
        final MediaPlayer kickSix = MediaPlayer.create(this,R.raw.kicksix);
        final MediaPlayer whoops = MediaPlayer.create(this, R.raw.whoops);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //final MusicPlayer player = new MusicPlayer(this);
        final MusicManager manager = new MusicManager(this);

        ArrayList<Integer> ukuList = new ArrayList<Integer>(8);
        ukuList.add(R.raw.a);
        ukuList.add(R.raw.b);
        ukuList.add(R.raw.c);
        ukuList.add(R.raw.cc);
        ukuList.add(R.raw.d);
        ukuList.add(R.raw.e);
        ukuList.add(R.raw.f);
        ukuList.add(R.raw.g);



        TV1 = (TextView) findViewById(R.id.textView1);
        iV1 = (ImageView) findViewById(R.id.imageView1);
        initTensorFlowAndLoadModel();
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


    private boolean isAppRunning() {
        ActivityManager m = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList =  m.getRunningTasks(10);
        Iterator<ActivityManager.RunningTaskInfo> itr = runningTaskInfoList.iterator();
        int n=0;
        while(itr.hasNext()){
            n++;
            itr.next();
        }
        if(n==1){ // App is killed
            return false;
        }

        return true; // App is in background or foreground
    }

    private void initTensorFlowAndLoadModel() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_FILE,
                            LABEL_FILE,
                            INPUT_SIZE,
                            IMAGE_MEAN,
                            IMAGE_STD,
                            INPUT_NAME,
                            OUTPUT_NAME);
                } catch (final Exception e) {
                    throw new RuntimeException("Error initializing TensorFlow!", e);
                }
            }
        });
    }

    public List<Classifier.Recognition> analyse(Bitmap bitmap)
    {
        bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);
        final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);
        return results;
    }

    public void selectPhoto(View v)
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST);

    }

    public void takePicture(View v)
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.setType("image/*");
        Log.d("takePic",takePictureIntent.toString());
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }



    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try
            {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                List<Classifier.Recognition> results = analyse(selectedImage);
                TV1.setText(results.get(0).toString());
                int imType = Integer.parseInt(results.get(0).toString().substring(1,2));
                if (imType == 0) {
                    TV1.setText("Oh no it's Big Al!");
                } else if (imType == 1) {
                    TV1.setText("Hey it's Aubie! War Eagle!");
                } else if (imType == 2) {
                    TV1.setText("That's a tiger, but it's not Aubie!");
                } else if (imType == 3) {
                    TV1.setText("I don't see a mascot in this picture!");
                }
                Log.d("results", results.get(0).toString().substring(0,3));

                setPicture(selectedImage);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //set Picture in the ImageView (iV1)
    public void setPicture(Bitmap bp)
    {
        Bitmap scaledBp =  Bitmap.createScaledBitmap(bp, iV1.getWidth(), iV1.getHeight(), false);
        iV1.setImageBitmap(scaledBp);
    }

}

