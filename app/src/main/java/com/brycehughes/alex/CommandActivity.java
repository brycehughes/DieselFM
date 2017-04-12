package com.brycehughes.alex;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.media.MediaRecorder;
import android.widget.TabHost;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import android.widget.TextView;


public class CommandActivity extends AppCompatActivity{
    private Button record,play;
    private MediaRecorder mediarecorder =null;
    private MediaPlayer mediaplayer;
    private TextView debug=null;
    private File path,file;
    TabHost tabhost = null;
    private int debugint = -1;
    Boolean isPlaying;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        //Get Buttons
        debug=(TextView)findViewById(R.id.debugtext);
        record=(Button)findViewById(R.id.recordbutton);
        play=(Button)findViewById(R.id.playbutton);
        // setup tabhost
        tabhost=(TabHost)findViewById(R.id.thost);
        tabhost.setup();
        //System.out.println("Past tabhost setup");

        TabHost.TabSpec tab1 = tabhost.newTabSpec("Record");
        tab1.setContent(R.id.Record);
        tab1.setIndicator("First Tab");
        tabhost.addTab(tab1);

        //System.out.println("Past first tab setup");

        TabHost.TabSpec tab2 = tabhost.newTabSpec("Play");
        tab2.setContent(R.id.Play);
        tab2.setIndicator("Second Tab");
        tabhost.addTab(tab2);

        //setup output file
        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        //setup media recorder


        //Record button on touch listener
        record.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                            try {
                                URL url = new URL("http://www.android.com/");
                                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                urlConnection.setRequestMethod("GET");
                                urlConnection.connect();
                                debug.setText(urlConnection.getResponseCode());
                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                AudioTrack at = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                                        AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                                        8000, AudioTrack.MODE_STREAM);
                                at.play();
                                int i=0;
                                int j=0;
                                byte[] music = new byte[512];
                                while(((i = in.read(music)) != -1) & j<10000) {
                                    at.write(music, 0, i);
                                    debug.setText(j);
                                    j++;
                                }
                                at.stop();
                                at.release();
                                in.close();
                                in.reset();
                            } catch (Exception e) {
                                e.printStackTrace();
                                // TODO: handle exception
                            }
                            break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });

        play.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });

    }
}
