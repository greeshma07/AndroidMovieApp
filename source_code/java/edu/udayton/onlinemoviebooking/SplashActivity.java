/*
**************************************************************************************************************
*																											 *
* Project name: Online Movie Booking                                                                         *
* File name: SplashActivity.java                                                                             *
* Purpose: To implement a splash screen with a 5-sec pause.                                                  *
* Files Referred :   activity_splash.xml                                                                     *
* @author  Greeshma	Sasidharan Nair																		     *
*                                                  															 *
**************************************************************************************************************
*/

package edu.udayton.onlinemoviebooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    public static final long DELAY = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //display action bar icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //create the timer task to execute after the delay
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //shutdown the splash activity
                finish();
                //launch MainActivity via explicit intent
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,DELAY);
    }//end onCreate method
}//end SplashActivity class
