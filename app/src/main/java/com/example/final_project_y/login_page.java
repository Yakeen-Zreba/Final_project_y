package com.example.final_project_y;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_page extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        /*--------------------------------------------------------------------------*/
        /*to Disable landscape orientation*/
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        /*--------------------------------------------------------------------------*/
        /*to add onclick event to login button*/
        Button loginbtn = findViewById(R.id.login_button);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainPage();
            }
        });/* the end of onclick event to login button in login page*/
        /*--------------------------------------------------------------------------*/


    }/* the end of on create*/


    /* to link the login page with the main page (method)*/
    public void openMainPage() {
        Intent intent=new Intent(this, MainPage.class);
        startActivity(intent);
    }

    /*--------------------------------------------------------------------------*/



}
