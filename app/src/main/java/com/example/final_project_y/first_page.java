package com.example.final_project_y;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class first_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //------------------------------------------

        setContentView(R.layout.first_page);

        /*to Disable landscape orientation*/
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*to add onclick event to login button*/
        Button loginbtn = findViewById(R.id.loginButton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             openLoginPage();
            }
        });/* the of onclick event to login button*/

        /*--------------------------------------------------------------------------*/

    }/* the end of on create*/
    /*--------------------------------------------------------------------------*/


    /* to link the first page with the login page method*/
    public void openLoginPage() {
//        Intent intent=new Intent(this,login_page.class);
    //    startActivity(intent);
    }







}
