/*
**************************************************************************************************************
*																											 *
* Project name: Online Movie Booking                                                                         *
* File name: MovieSchedule.java                                                                              *
* Purpose: To implement movie schedule screen, which is implemented using explicit intents by declaring      *
* extras. The movie logo, movie rating, More Info button, Watch Trailer and show times are the various       *
* extras used here.                                                                                          *
* Files Referred :   activity_movie_schedule.xml                                                             *
* @author  Greeshma	Sasidharan Nair																		     *
*                                                  															 *
**************************************************************************************************************
*/

package edu.udayton.onlinemoviebooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MovieSchedule extends AppCompatActivity {

    //declare extras variables
    public static final String SHOWTIME_KEY = "SHOWTIMES";
    public static final String RATE_KEY = "RATE_ID";
    public static final String LOGO_KEY = "LOGO_ID";
    public static final String RAD1_LBL = "RAD_BTN_1";
    public static final String RAD2_LBL = "RAD_BTN_2";
    public static final String RAD3_LBL = "RAD_BTN_3";
    public static final String RAD4_LBL = "RAD_BTN_4";
    public static final String BTN_KEY = "BTN_LABEL";
    public static final String URL_KEY = "URL";
    public static final String TRAILER_URL_KEY = "TRAILER_URL";
    public static final String TITLE_KEY = "MOV_TITLE";
    public static final String COST_KEY = "TICKET_COST";

    public static final String AVAILABLE_SEATS1 = "SEAT1_KEY";
    public static final int DEF_COUNT1 = 100;
    public static final String AVAILABLE_SEATS2 = "SEAT2_KEY";
    public static final int DEF_COUNT2 = 100;
    public static final String AVAILABLE_SEATS3 = "SEAT3_KEY";
    public static final int DEF_COUNT3 = 100;
    public static final String AVAILABLE_SEATS4 = "SEAT4_KEY";
    public static final int DEF_COUNT4 = 100;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_schedule);

        //display the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            String imageID = extras.getString(RATE_KEY);
            int imgID = Integer.parseInt(imageID);
            final ImageView gadgetImg = (ImageView) findViewById(R.id.ratingBarImageView);
            if(gadgetImg != null)
                gadgetImg.setImageResource(imgID);

            String logoID = extras.getString(LOGO_KEY);
            int logoImg = Integer.parseInt(logoID);
            final ImageView movLogo = (ImageView) findViewById(R.id.movLogoImageView);
            if(movLogo != null)
                movLogo.setImageResource(logoImg);

            String showTimeText = extras.getString(SHOWTIME_KEY);
            final TextView showtimesTxtView = (TextView) findViewById(R.id.showtimeTextView);
            if(showtimesTxtView != null)
                showtimesTxtView.setText(showTimeText);

            String radBtn1Val = extras.getString(RAD1_LBL);
            final RadioButton radBtn1 = (RadioButton) findViewById(R.id.radioButton1);
            if(radBtn1 != null)
                radBtn1.setText(radBtn1Val);
            String radBtn2Val = extras.getString(RAD2_LBL);
            final RadioButton radBtn2 = (RadioButton) findViewById(R.id.radioButton2);
            if(radBtn2 != null)
                radBtn2.setText(radBtn2Val);
            String radBtn3Val = extras.getString(RAD3_LBL);
            final RadioButton radBtn3 = (RadioButton) findViewById(R.id.radioButton3);
            if(radBtn3 != null)
                radBtn3.setText(radBtn3Val);
            String radBtn4Val = extras.getString(RAD4_LBL);
            final RadioButton radBtn4 = (RadioButton) findViewById(R.id.radioButton4);
            if(radBtn4 != null)
                radBtn4.setText(radBtn4Val);

            final String movTitle = extras.getString(TITLE_KEY);
            final String cost = extras.getString(COST_KEY);

            String btnLbl = extras.getString(BTN_KEY);
            final Button btnSite = (Button) findViewById(R.id.moreInfoButton);
            if(btnSite != null) {
                btnSite.setText(btnLbl);
                btnSite.setTransformationMethod(null);
            }

            final String url = extras.getString(URL_KEY);

            View.OnClickListener buttonListener1 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent1);
                }
            };
            if(btnSite != null)
                btnSite.setOnClickListener(buttonListener1);

            final String trailerUrl = extras.getString(TRAILER_URL_KEY);
            final Button trailerBtn = (Button) findViewById(R.id.trailerButton);
            if(trailerBtn != null)
                trailerBtn.setTransformationMethod(null);
            View.OnClickListener buttonListener2 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl));
                    startActivity(intent1);
                }
            };
            if(trailerBtn != null)
                trailerBtn.setOnClickListener(buttonListener2);

            final Button buyTicketBtn = (Button)findViewById(R.id.bookButton);
            if(buyTicketBtn != null)
                buyTicketBtn.setTransformationMethod(null);
            View.OnClickListener buttonListener3 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String showTime;
                    Intent intent1;
                    try{
                        if(!radBtn1.isChecked() && !radBtn2.isChecked()
                                && !radBtn3.isChecked() && !radBtn4.isChecked()){
                            Toast toast = Toast.makeText(MovieSchedule.this,"You must select Showtime",Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else {
                            intent1 = new Intent(MovieSchedule.this, MovieBooking.class);
                            //pass movie title, ticket cost, showtime selected to MovieBooking activity
                            intent1.putExtra(MovieBooking.TITLE_KEY, movTitle);
                            intent1.putExtra(MovieBooking.COST_KEY, cost);

                            if (radBtn1.isChecked()) {
                                showTime = radBtn1.getText().toString();
                                intent1.putExtra(MovieBooking.SCREEN_KEY,"screen1");
                                editor.putInt(AVAILABLE_SEATS1,DEF_COUNT1);
                                editor.commit();
                            }
                            else if (radBtn2.isChecked()) {
                                showTime = radBtn2.getText().toString();
                                intent1.putExtra(MovieBooking.SCREEN_KEY,"screen2");
                                editor.putInt(AVAILABLE_SEATS2,DEF_COUNT2);
                                editor.commit();
                            }
                            else if (radBtn3.isChecked()) {
                                showTime = radBtn3.getText().toString();
                                intent1.putExtra(MovieBooking.SCREEN_KEY,"screen3");
                                editor.putInt(AVAILABLE_SEATS3,DEF_COUNT3);
                                editor.commit();
                            }
                            else{
                                showTime = radBtn4.getText().toString();
                                intent1.putExtra(MovieBooking.SCREEN_KEY,"screen4");
                                editor.putInt(AVAILABLE_SEATS4,DEF_COUNT4);
                                editor.commit();
                            }
                            intent1.putExtra(MovieBooking.SHOWTIME_KEY, showTime);
                            startActivity(intent1);
                        }
                    }catch (Exception e){
                        Toast toast = Toast.makeText(MovieSchedule.this,e.toString(),Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            };
            buyTicketBtn.setOnClickListener(buttonListener3);
        }// end if extras
    }// end onCreate method
}// end MovieSchedule class
