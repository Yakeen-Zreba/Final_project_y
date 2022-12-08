package com.example.final_project_y;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class main_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //------------------------------------------

        setContentView(R.layout.main_page);

        /*--------------------------------------------------------------------------*/
        /*to Disable landscape orientation*/
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        /*--------------------------------------------------------------------------*/
        /* to add the date from the calender to the main page*/
        Calendar calendar=Calendar.getInstance();
        String curruentdate= DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
        TextView dateView=findViewById(R.id.cal);
        dateView.setText(curruentdate);
        
        /* the End  of adding date */


        /*--------------------------------------------------------------------------*/
        /* to get the user name from DB */
        TextView user_name=findViewById(R.id.user_name);
        user_name.setText("NNN");

        /*--------------------------------------------------------------------------*/



    }

}
