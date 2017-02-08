/*
**************************************************************************************************************
*																											 *
* Project name: Online Movie Booking                                                                         *
* File name: Running movies.java                                                                             *
* Purpose: To display list of running movies. The user can go to the MovieSchedule activity by clicking each *
* movie in the list. This file passes movie logo, movie title, ticket price, webpage Url, trailer Url,       *
* showtime etc to the MovieSchedule activity  using explicit intents.                                        *
* Files Referred :   activity_running_movies.xml                                                             *
* @author  Greeshma	Sasidharan Nair																		     *
*                                                  															 *
**************************************************************************************************************
*/


package edu.udayton.onlinemoviebooking;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

public class RunningMovies extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //converting string array of running movies to list
        List<String> runningMov = Arrays.asList(getResources().getStringArray(R.array.runningMovies));
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_running_movies,
                R.id.runningMoviesListTextView, runningMov));
    }//end oncreate method

    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Intent intent = null;
        try {
            switch (position) {
                //setting extras using explicit intents
                case 0:
                    intent = new Intent(RunningMovies.this, MovieSchedule.class);
                    intent.putExtra(MovieSchedule.SHOWTIME_KEY, getResources().getString(R.string.showtimesLights));
                    intent.putExtra(MovieSchedule.RATE_KEY, Integer.toString(R.drawable.rating3));
                    intent.putExtra(MovieSchedule.LOGO_KEY, Integer.toString(R.drawable.lightsout_logo));
                    intent.putExtra(MovieSchedule.RAD1_LBL,getResources().getString(R.string.radBtn1Lights));
                    intent.putExtra(MovieSchedule.RAD2_LBL,getResources().getString(R.string.radBtn2Lights));
                    intent.putExtra(MovieSchedule.RAD3_LBL,getResources().getString(R.string.radBtn3Lights));
                    intent.putExtra(MovieSchedule.RAD4_LBL,getResources().getString(R.string.radBtn4Lights));
                    intent.putExtra(MovieSchedule.BTN_KEY, getResources().getString(R.string.buttonLights));
                    intent.putExtra(MovieSchedule.URL_KEY,getResources().getString(R.string.lightsUrl));
                    intent.putExtra(MovieSchedule.TRAILER_URL_KEY,getResources().getString(R.string.trailerLights));
                    intent.putExtra(MovieSchedule.TITLE_KEY,getResources().getString(R.string.lightsTitle));
                    intent.putExtra(MovieSchedule.COST_KEY,getResources().getString(R.string.lightsCost));
                    break;
                case 1:
                    intent = new Intent(RunningMovies.this, MovieSchedule.class);
                    intent.putExtra(MovieSchedule.SHOWTIME_KEY, getResources().getString(R.string.showtimesShallows));
                    intent.putExtra(MovieSchedule.RATE_KEY, Integer.toString(R.drawable.rating2));
                    intent.putExtra(MovieSchedule.LOGO_KEY, Integer.toString(R.drawable.shallows_logo));
                    intent.putExtra(MovieSchedule.RAD1_LBL,getResources().getString(R.string.radBtn1Shal));
                    intent.putExtra(MovieSchedule.RAD2_LBL,getResources().getString(R.string.radBtn2Shal));
                    intent.putExtra(MovieSchedule.RAD3_LBL,getResources().getString(R.string.radBtn3Shal));
                    intent.putExtra(MovieSchedule.RAD4_LBL,getResources().getString(R.string.radBtn4Shal));
                    intent.putExtra(MovieSchedule.BTN_KEY, getResources().getString(R.string.buttonShallows));
                    intent.putExtra(MovieSchedule.URL_KEY,getResources().getString(R.string.shallowsUrl));
                    intent.putExtra(MovieSchedule.TRAILER_URL_KEY,getResources().getString(R.string.trailerShallows));
                    intent.putExtra(MovieSchedule.TITLE_KEY,getResources().getString(R.string.shallowsTitle));
                    intent.putExtra(MovieSchedule.COST_KEY,getResources().getString(R.string.shallowsCost));
                    break;
                case 2:intent = new Intent(RunningMovies.this, MovieSchedule.class);
                    intent.putExtra(MovieSchedule.SHOWTIME_KEY, getResources().getString(R.string.showtimesCon));
                    intent.putExtra(MovieSchedule.RATE_KEY, Integer.toString(R.drawable.rating4));
                    intent.putExtra(MovieSchedule.LOGO_KEY, Integer.toString(R.drawable.conjuring_logo));
                    intent.putExtra(MovieSchedule.RAD1_LBL,getResources().getString(R.string.radBtn1Con));
                    intent.putExtra(MovieSchedule.RAD2_LBL,getResources().getString(R.string.radBtn2Con));
                    intent.putExtra(MovieSchedule.RAD3_LBL,getResources().getString(R.string.radBtn3Con));
                    intent.putExtra(MovieSchedule.RAD4_LBL,getResources().getString(R.string.radBtn4Con));
                    intent.putExtra(MovieSchedule.BTN_KEY, getResources().getString(R.string.buttonCon));
                    intent.putExtra(MovieSchedule.URL_KEY,getResources().getString(R.string.conUrl));
                    intent.putExtra(MovieSchedule.TRAILER_URL_KEY,getResources().getString(R.string.trailerCon));
                    intent.putExtra(MovieSchedule.TITLE_KEY,getResources().getString(R.string.conTitle));
                    intent.putExtra(MovieSchedule.COST_KEY,getResources().getString(R.string.conCost));
                    break;
                case 3:
                    intent = new Intent(RunningMovies.this, MovieSchedule.class);
                    intent.putExtra(MovieSchedule.SHOWTIME_KEY, getResources().getString(R.string.showtimesPets));
                    intent.putExtra(MovieSchedule.RATE_KEY, Integer.toString(R.drawable.rating3));
                    intent.putExtra(MovieSchedule.LOGO_KEY, Integer.toString(R.drawable.pets_logo));
                    intent.putExtra(MovieSchedule.RAD1_LBL,getResources().getString(R.string.radBtn1Pets));
                    intent.putExtra(MovieSchedule.RAD2_LBL,getResources().getString(R.string.radBtn2Pets));
                    intent.putExtra(MovieSchedule.RAD3_LBL,getResources().getString(R.string.radBtn3Pets));
                    intent.putExtra(MovieSchedule.RAD4_LBL,getResources().getString(R.string.radBtn4Pets));
                    intent.putExtra(MovieSchedule.BTN_KEY, getResources().getString(R.string.btnPets));
                    intent.putExtra(MovieSchedule.URL_KEY,getResources().getString(R.string.petsUrl));
                    intent.putExtra(MovieSchedule.TRAILER_URL_KEY,getResources().getString(R.string.trailerPets));
                    intent.putExtra(MovieSchedule.TITLE_KEY,getResources().getString(R.string.petsTitle));
                    intent.putExtra(MovieSchedule.COST_KEY,getResources().getString(R.string.costPets));
                    break;
                case 4:
                    intent = new Intent(RunningMovies.this, MovieSchedule.class);
                    intent.putExtra(MovieSchedule.SHOWTIME_KEY, getResources().getString(R.string.showtimesGhost));
                    intent.putExtra(MovieSchedule.RATE_KEY, Integer.toString(R.drawable.rating3));
                    intent.putExtra(MovieSchedule.LOGO_KEY, Integer.toString(R.drawable.ghost_logo));
                    intent.putExtra(MovieSchedule.RAD1_LBL,getResources().getString(R.string.radBtn1Ghost));
                    intent.putExtra(MovieSchedule.RAD2_LBL,getResources().getString(R.string.radBtn2Ghost));
                    intent.putExtra(MovieSchedule.RAD3_LBL,getResources().getString(R.string.radBtn3Ghost));
                    intent.putExtra(MovieSchedule.RAD4_LBL,getResources().getString(R.string.radBtn4Ghost));
                    intent.putExtra(MovieSchedule.BTN_KEY, getResources().getString(R.string.btnGhost));
                    intent.putExtra(MovieSchedule.URL_KEY,getResources().getString(R.string.ghostUrl));
                    intent.putExtra(MovieSchedule.TRAILER_URL_KEY,getResources().getString(R.string.trailerGhost));
                    intent.putExtra(MovieSchedule.TITLE_KEY,getResources().getString(R.string.ghostTitle));
                    intent.putExtra(MovieSchedule.COST_KEY,getResources().getString(R.string.costGhost));
                    break;
                case 5:
                    intent = new Intent(RunningMovies.this, MovieSchedule.class);
                    intent.putExtra(MovieSchedule.SHOWTIME_KEY, getResources().getString(R.string.showtimesDory));
                    intent.putExtra(MovieSchedule.RATE_KEY, Integer.toString(R.drawable.rating3));
                    intent.putExtra(MovieSchedule.LOGO_KEY, Integer.toString(R.drawable.dory_logo));
                    intent.putExtra(MovieSchedule.RAD1_LBL,getResources().getString(R.string.radBtn1Dory));
                    intent.putExtra(MovieSchedule.RAD2_LBL,getResources().getString(R.string.radBtn2Dory));
                    intent.putExtra(MovieSchedule.RAD3_LBL,getResources().getString(R.string.radBtn3Dory));
                    intent.putExtra(MovieSchedule.RAD4_LBL,getResources().getString(R.string.radBtn4Dory));
                    intent.putExtra(MovieSchedule.BTN_KEY, getResources().getString(R.string.btnDory));
                    intent.putExtra(MovieSchedule.URL_KEY,getResources().getString(R.string.doryUrl));
                    intent.putExtra(MovieSchedule.TRAILER_URL_KEY,getResources().getString(R.string.trailerDory));
                    intent.putExtra(MovieSchedule.TITLE_KEY,getResources().getString(R.string.doryTitle));
                    intent.putExtra(MovieSchedule.COST_KEY,getResources().getString(R.string.costDory));
                    break;
                case 6:
                    intent = new Intent(RunningMovies.this, MovieSchedule.class);
                    intent.putExtra(MovieSchedule.SHOWTIME_KEY, getResources().getString(R.string.showtimesLegend));
                    intent.putExtra(MovieSchedule.RATE_KEY, Integer.toString(R.drawable.rating4));
                    intent.putExtra(MovieSchedule.LOGO_KEY, Integer.toString(R.drawable.tarzan_logo));
                    intent.putExtra(MovieSchedule.RAD1_LBL,getResources().getString(R.string.radBtn1Tarzan));
                    intent.putExtra(MovieSchedule.RAD2_LBL,getResources().getString(R.string.radBtn2Tarzan));
                    intent.putExtra(MovieSchedule.RAD3_LBL,getResources().getString(R.string.radBtn3Tarzan));
                    intent.putExtra(MovieSchedule.RAD4_LBL,getResources().getString(R.string.radBtn4Tarzan));
                    intent.putExtra(MovieSchedule.BTN_KEY, getResources().getString(R.string.btnTarzan));
                    intent.putExtra(MovieSchedule.URL_KEY,getResources().getString(R.string.tarzanUrl));
                    intent.putExtra(MovieSchedule.TRAILER_URL_KEY,getResources().getString(R.string.trailerTarzan));
                    intent.putExtra(MovieSchedule.TITLE_KEY,getResources().getString(R.string.tarzanTitle));
                    intent.putExtra(MovieSchedule.COST_KEY,getResources().getString(R.string.costTarzan));
                    break;
                case 7:
                    intent = new Intent(RunningMovies.this, MovieSchedule.class);
                    intent.putExtra(MovieSchedule.SHOWTIME_KEY, getResources().getString(R.string.showtimesJungle));
                    intent.putExtra(MovieSchedule.RATE_KEY, Integer.toString(R.drawable.rating4));
                    intent.putExtra(MovieSchedule.LOGO_KEY, Integer.toString(R.drawable.junglebook_logo));
                    intent.putExtra(MovieSchedule.RAD1_LBL,getResources().getString(R.string.radBtn1Jungle));
                    intent.putExtra(MovieSchedule.RAD2_LBL,getResources().getString(R.string.radBtn2Jungle));
                    intent.putExtra(MovieSchedule.RAD3_LBL,getResources().getString(R.string.radBtn3Jungle));
                    intent.putExtra(MovieSchedule.RAD4_LBL,getResources().getString(R.string.radBtn4Jungle));
                    intent.putExtra(MovieSchedule.BTN_KEY, getResources().getString(R.string.btnJungle));
                    intent.putExtra(MovieSchedule.URL_KEY,getResources().getString(R.string.jungleUrl));
                    intent.putExtra(MovieSchedule.TRAILER_URL_KEY,getResources().getString(R.string.trailerJungle));
                    intent.putExtra(MovieSchedule.TITLE_KEY,getResources().getString(R.string.jungleTitle));
                    intent.putExtra(MovieSchedule.COST_KEY,getResources().getString(R.string.costJungle));
                    break;

            }//end switch case
            startActivity(intent);
        } catch (Exception e) {
            Toast toast = Toast.makeText(RunningMovies.this, e.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
    } //end onListItemClick method
}//end RunningMovies class
