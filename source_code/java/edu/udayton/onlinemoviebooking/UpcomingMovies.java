/*
**************************************************************************************************************
*																											 *
* Project name: Online Movie Booking                                                                         *
* File name: UpcomingMovies.java                                                                             *
* Purpose: To implement a screen which displays list of upcoming movies with their release dates.            *
* By clicking on each movies take you to an external webpage which is implemented using implicit intents.    *
* Files Referred :   activity_upcoming_movies.xml                                                            *
* @author  Greeshma	Sasidharan Nair																		     *
*                                                  															 *
**************************************************************************************************************
*/


package edu.udayton.onlinemoviebooking;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

public class UpcomingMovies extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //converting string array of running movies to list
        List<String> upcomingMov = Arrays.asList(getResources().getStringArray(R.array.upcomingMovies));
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_upcoming_movies,
                R.id.upcomingMovListTextView, upcomingMov));
    }//end onCreate method

    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Intent intent = null;
        try {
            switch (position) {
                //using implicit intents to go to an external webpage
                case 0: intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getResources().getString(R.string.suicideSquadUrl)));
                        break;
                case 1: intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getResources().getString(R.string.petesDragonUrl)));
                        break;
                case 2: intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getResources().getString(R.string.benHurUrl)));
                        break;
                case 3: intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getResources().getString(R.string.kuboUrl)));
                        break;
                case 4: intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getResources().getString(R.string.mechanicUrl)));
                        break;
            } //end switch case
            startActivity(intent);
        } catch (Exception e) {
            Toast toast = Toast.makeText(UpcomingMovies.this, e.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
    } //end onListItemClick method
} //end UpcomingMovies Class
