/*
**************************************************************************************************************
*																											 *
* Project name: Online Movie Booking                                                                         *
* File name: OutputScreenActivity.java                                                                       *
* Purpose: To implement output screen to display the success message to the user.                            *
* Files Referred :   activity_output_activity.xml                                                            *
* @author  Greeshma	Sasidharan Nair																		     *
*                                                  															 *
**************************************************************************************************************
*/

package edu.udayton.onlinemoviebooking;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OutputScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_screen);

        //display the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //get reference GUI control
        final TextView resultView = (TextView)findViewById(R.id.outputTextView);
        String output = getResources().getString(R.string.outText);
        if(resultView != null) {
            resultView.setTextColor(Color.parseColor("#800080"));
            //set the output message
            resultView.setText(output);
        }
    }
}
