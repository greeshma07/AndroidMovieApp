/*
**************************************************************************************************************
*																											 *
* Project name: Online Movie Booking                                                                         *
* File name: MovieBooking.java                                                                               *
* Purpose: To implement movie booking screen, which displays how many seats left for a show, movie name, show*
* and input fields for users to enter their info to book for the show. The concepts of SharedPreferences is  *
* implemented to preserve the count of seats. Also, the concepts of extras is used here to get the movie     *
* name, selected show time, ticket cost and seat left from MovieSchedule Activity. Input validation are also *
* handled in this code.                                                                                      *
* Files Referred :   activity_movie_booking.xml                                                              *
* @author  Greeshma	Sasidharan Nair																		     *
*                                                  															 *
**************************************************************************************************************
*/

package edu.udayton.onlinemoviebooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieBooking extends AppCompatActivity {

    //declare the string extras
    public static final String TITLE_KEY = "MOV_TITLE";
    public static final String COST_KEY = "TICKET_COST";
    public static final String SHOWTIME_KEY = "SHOWTIME";
    public static final String SCREEN_KEY = "SCREEN_NO";

    public String screenNO;
    public  int totalAmt = 0,seatCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_booking);

        SharedPreferences sharedPreferences;
        //create the sharedPreferences object for this activity to preserve the seat count
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //display the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //get the extras from the intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null) {
            String title = extras.getString(TITLE_KEY);
            final TextView titleTxtView = (TextView) findViewById(R.id.movTitleTtextView);
            if(titleTxtView != null)
                titleTxtView.setText(title);

            String showtime = extras.getString(SHOWTIME_KEY);
            final TextView movShowTxtView = (TextView) findViewById(R.id.movShowtimeTextView);
            if(movShowTxtView != null)
                movShowTxtView.setText(showtime);

            String cost = extras.getString(COST_KEY);
            String costText;
            final int ticketPrice = Integer.parseInt(cost);
            //to display the ticket price for the movie
            final TextView costTxtView = (TextView) findViewById(R.id.ticketCostTextView);
            if(costTxtView != null) {
                costText = getResources().getString(R.string.ticketCost) + "%s" + cost;
                //concatenate string using placeholders
                costText = String.format(costText," ");
                costTxtView.setText(costText);
            }

            screenNO = extras.getString(SCREEN_KEY);
            if(screenNO != null) {
                //update seatCount according to num of tickets booked
                if (!screenNO.equalsIgnoreCase("") && screenNO.equalsIgnoreCase("screen1")) {
                    seatCount = sharedPreferences.getInt("MovieSchedule.AVAILABLE_SEATS1",
                            MovieSchedule.DEF_COUNT1);
                } else if (!screenNO.equalsIgnoreCase("") && screenNO.equalsIgnoreCase("screen2")) {
                    seatCount = sharedPreferences.getInt("MovieSchedule.AVAILABLE_SEATS2",
                            MovieSchedule.DEF_COUNT2);
                } else if (!screenNO.equalsIgnoreCase("") && screenNO.equalsIgnoreCase("screen3")) {
                    seatCount = sharedPreferences.getInt("MovieSchedule.AVAILABLE_SEATS3",
                            MovieSchedule.DEF_COUNT3);
                } else {
                    seatCount = sharedPreferences.getInt("MovieSchedule.AVAILABLE_SEATS4",
                            MovieSchedule.DEF_COUNT4);
                }
            }

            //to display how many seats left for a particular show
            final TextView seatTxtView = (TextView) findViewById(R.id.seatCountTextView);
            String seatText;
            if(seatTxtView != null){
                seatText = getResources().getString(R.string.seatCount) + "%s" + seatCount;
                //concatenate string using placeholders
                seatText = String.format(seatText," ");
                seatTxtView.setText(seatText);
            }

            Button makePayBtn = (Button) findViewById(R.id.makePayButton);
            if(makePayBtn != null)
                makePayBtn.setTransformationMethod(null);

            View.OnClickListener bListener = new View.OnClickListener(){
                //Get references for the GUI controls
                final EditText firstNameEditTxt = (EditText)findViewById(R.id.firstNameEditText);
                final EditText lastNameEditTxt = (EditText)findViewById(R.id.lastNameEditText);
                final EditText emailEditTxt = (EditText)findViewById(R.id.emailEditText);
                final EditText mobEditTxt = (EditText)findViewById(R.id.mobEditText);
                final EditText countEditTxt = (EditText)findViewById(R.id.tcktCountEditText);

                @Override
                public void onClick(View v){
                    String firstName = firstNameEditTxt.getText().toString();
                    String lastName = lastNameEditTxt.getText().toString();
                    String email = emailEditTxt.getText().toString();
                    String mob = mobEditTxt.getText().toString();
                    String count = countEditTxt.getText().toString();
                    //to set format for Mobile NO: using regex
                    Pattern pattern = Pattern.compile("^[(]\\d{3}[)]\\d{3}[-]\\d{4}$");
                    Matcher matcher = null;
                    Intent intent1;
                    try{
                        int ticketCount = 0;
                        if(!count.equalsIgnoreCase(""))
                            ticketCount = Integer.parseInt(count);
                        if(!mob.equalsIgnoreCase(""))
                            matcher = pattern.matcher(mob);
                        if(firstName.equalsIgnoreCase("") || lastName.equalsIgnoreCase("")
                                || email.equalsIgnoreCase("") || mob.equalsIgnoreCase("")
                                || count.equalsIgnoreCase("")){
                            Toast toast = Toast.makeText(MovieBooking.this,"All fields are mandatory",Toast.LENGTH_LONG);
                            toast.show();
                        }
                        //to validate firstName
                        else if(!firstName.matches("[a-zA-Z ]+")){
                            Toast toast = Toast.makeText(MovieBooking.this,"Invalid Entry in first name!",Toast.LENGTH_LONG);
                            toast.show();
                        }
                        //to validate lastName
                        else if(!lastName.matches("[a-zA-Z ]+")){
                            Toast toast = Toast.makeText(MovieBooking.this,"Invalid Entry in last name!",Toast.LENGTH_LONG);
                            toast.show();
                        }
                        //to validate mobile number
                        else if (matcher != null && !matcher.matches()) {
                            Toast toast = Toast.makeText(MovieBooking.this,"Please enter a valid Mobile NO:" +
                                    " Example:(555)555-5555",Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else if(ticketCount == 0){
                            Toast toast = Toast.makeText(MovieBooking.this,"Invalid NO: of tickets!",Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else if(ticketCount > seatCount){
                            Toast toast = Toast.makeText(MovieBooking.this,"Sorry, We have limited tickets!",Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else {
                            intent1 = new Intent(MovieBooking.this,Payment.class);
                            if(screenNO != null) {
                                //update seatCount according to num of tickets booked for each show
                                if (!screenNO.equalsIgnoreCase("") && screenNO.equalsIgnoreCase("screen1")) {
                                    intent1.putExtra(Payment.SCREENNO_KEY, 1);
                                } else if (!screenNO.equalsIgnoreCase("") && screenNO.equalsIgnoreCase("screen2")) {
                                    intent1.putExtra(Payment.SCREENNO_KEY, 2);
                                } else if (!screenNO.equalsIgnoreCase("") && screenNO.equalsIgnoreCase("screen3")) {
                                    intent1.putExtra(Payment.SCREENNO_KEY, 3);
                                } else if (!screenNO.equalsIgnoreCase("") && screenNO.equalsIgnoreCase("screen4")) {
                                    intent1.putExtra(Payment.SCREENNO_KEY, 4);
                                }
                            }
                            //update seatCount variable after ticket got booked
                            seatCount = seatCount - ticketCount;
                            //calculate total amount of tickets booked
                            totalAmt = ticketCount * ticketPrice;
                            intent1.putExtra(Payment.SEAT_KEY,seatCount);
                            intent1.putExtra(Payment.AMT_KEY,totalAmt);
                            startActivity(intent1);
                        }
                    }
                    catch (Exception e){
                        Toast toast = Toast.makeText(MovieBooking.this,e.toString(),Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            };
            if(makePayBtn != null)
                makePayBtn.setOnClickListener(bListener);
        }//end if
    }// end onCreate method
}//end MovieBooking class
