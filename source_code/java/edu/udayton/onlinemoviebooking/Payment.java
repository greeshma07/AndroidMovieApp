/*
**************************************************************************************************************
*																											 *
* Project name: Online Movie Booking                                                                         *
* File name: Payment.java                                                                                    *
* Purpose: To implement payment screen, in which users can enter their credit card info to make the payment. *
* The total ticket price is displayed in this screen. Input validation are also handled in this code.        *
* Files Referred :   activity_payment.xml                                                                    *
* @author  Greeshma	Sasidharan Nair																		     *
*                                                  															 *
**************************************************************************************************************
*/

package edu.udayton.onlinemoviebooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class Payment extends AppCompatActivity {

    //declare the extras variables
    public static final String AMT_KEY = "TOTAL_AMT";
    public static final String SEAT_KEY = "SEAT_COUNT";
    public static final String SCREENNO_KEY = "SCREEN";

    private SharedPreferences sharedPreferences;

    public int screenNo,seatLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //display the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Intent intent = getIntent();
        //get integer values from the extras
        int totAmt = intent.getIntExtra(AMT_KEY,0);
        screenNo = intent.getIntExtra(SCREENNO_KEY,0);
        seatLeft = intent.getIntExtra(SEAT_KEY,0);
        final DecimalFormat currency = new DecimalFormat("$0.00");

        String amtText;
        if(totAmt != 0) {
            final TextView amtTxtView = (TextView) findViewById(R.id.amtTextView);
            amtText = getResources().getString(R.string.totalAmt) + "%s" + currency.format(totAmt);
            //concatenate string using placeholders
            amtText = String.format(amtText," ");
            if(amtTxtView != null)
                amtTxtView.setText(amtText);
        }

        Button payBtn = (Button) findViewById(R.id.payButton);
        if(payBtn != null)
            payBtn.setTransformationMethod(null);

        View.OnClickListener bListener = new View.OnClickListener(){
            final EditText cardNameEditTxt = (EditText)findViewById(R.id.cardNameEditText);
            final EditText cardNoEditTxt = (EditText)findViewById(R.id.cardNoEditText);
            final EditText expDateEditTxt = (EditText)findViewById(R.id.dateEditText);
            final EditText secEditTxt = (EditText)findViewById(R.id.secCodeEditText);

            @Override
            public void onClick(View v){
                String cardName = cardNameEditTxt.getText().toString();
                String cardNo = cardNoEditTxt.getText().toString();
                String date = expDateEditTxt.getText().toString();
                String code = secEditTxt.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                try{
                    if(cardName.equalsIgnoreCase("") || cardNo.equalsIgnoreCase("")
                            || date.equalsIgnoreCase("") || code.equalsIgnoreCase("")) {
                        Toast toast = Toast.makeText(Payment.this, "All fields are mandatory", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    //to validate Name on Card
                    else if(!cardName.matches("[a-zA-Z ]+")){
                        Toast toast = Toast.makeText(Payment.this,"Invalid Entry in Name!",Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else {
                        if(screenNo == 1) {
                            //set the seatleft count to the sharedPrefrence variable
                            editor.putInt("MovieSchedule.AVAILABLE_SEATS1", seatLeft);
                            editor.commit();
                        }
                        else  if(screenNo == 2) {
                            editor.putInt("MovieSchedule.AVAILABLE_SEATS2", seatLeft);
                            editor.commit();
                        }
                        else  if(screenNo == 3) {
                            editor.putInt("MovieSchedule.AVAILABLE_SEATS3", seatLeft);
                            editor.commit();
                        }
                        else{
                            editor.putInt("MovieSchedule.AVAILABLE_SEATS4", seatLeft);
                            editor.commit();
                        }
                        Intent intent = new Intent(Payment.this, OutputScreenActivity.class);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    Toast toast = Toast.makeText(Payment.this,e.toString(),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };
        if(payBtn != null)
            payBtn.setOnClickListener(bListener);
    }
}
