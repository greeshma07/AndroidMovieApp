/*
**************************************************************************************************************
*																											 *
* Project name: Online Movie Booking                                                                         *
* File name: MainActivity.java                                                                               *
* Purpose: To implement the home screen of the app which contains two radio button options for the user to   *
* view the movie list and an audio button to play an MP3 audio file.                      .                  *
* Files Referred :   activity_main.xml                                                                       *
* @author  Greeshma	Sasidharan Nair																		     *
*                                                  															 *
**************************************************************************************************************
*/

package edu.udayton.onlinemoviebooking;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private MediaPlayer mpFile;
    private int playMode;
    private int PLAYING = 1,NOT_PLAYING = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create MediaPlayer
        mpFile = new MediaPlayer();
        mpFile = MediaPlayer.create(this,R.raw.audio_demo);

        //initialize playMode to NOT_PLAYING
        playMode = NOT_PLAYING;

        //display action bar icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //get reference for the GUI control
        final Button movieList = (Button)findViewById(R.id.movieListButton);
        if(movieList != null) {
            //set background color for the button
            movieList.setBackgroundColor(Color.parseColor("#FFC300"));
            movieList.setTransformationMethod(null);
            View.OnClickListener buttonListener = new View.OnClickListener() {
                final RadioButton runMovButton = (RadioButton) findViewById(R.id.radioButtonRunMovies);

                @Override
                public void onClick(View v) {
                    Intent intent;
                    try {
                        if (runMovButton.isChecked()) {
                            intent = new Intent(MainActivity.this, RunningMovies.class);
                        } else {
                            intent = new Intent(MainActivity.this, UpcomingMovies.class);
                        }
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            };
            movieList.setOnClickListener(buttonListener);
        }

        final Button audioBtn = (Button)findViewById(R.id.audioButton);
        if(audioBtn != null) {
            audioBtn.setTransformationMethod(null);
            audioBtn.setBackgroundColor(Color.parseColor("#C70039"));
            View.OnClickListener audioListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        switch (playMode) {
                            //case for NOT_PLAYNG
                            case 0:
                                mpFile.start();
                                playMode = PLAYING;
                                //change button label according to the playMode
                                audioBtn.setText(getResources().getString(R.string.pauseLbl));
                                break;
                            //case for PLAYING
                            case 1:
                                mpFile.pause();
                                playMode = NOT_PLAYING;
                                //change button label according to the playMode
                                audioBtn.setText(getResources().getString(R.string.playLbl));
                                break;
                        }
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            };
            audioBtn.setOnClickListener(audioListener);

            //to notify when the audio file finishes playing
            mpFile.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //reset the button label to 'Play' when the audio completes
                    audioBtn.setText(getResources().getString(R.string.playLbl));
                    //change the playmode
                    playMode = NOT_PLAYING;
                }

            });
        }
    }//end onCreate method
}//end MainActivity class
