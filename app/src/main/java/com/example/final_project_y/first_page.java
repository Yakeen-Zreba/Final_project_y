package com.example.final_project_y;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class first_page extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        /*to add onclick event to sign up button*/
        Button signupbtn = findViewById(R.id.sginupButton);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupPage();
            }
        });/* the of onclick event to sign up button*/





    }/* the end of on create*/
    /*--------------------------------------------------------------------------*/


    /* to link the first page with the login page method*/
    public void openLoginPage() {
        Intent intent=new Intent(this,login_page.class);
        startActivity(intent);
    }

    /*--------------------------------------------------------------------------*/
    /* to link the first page with the signup page (method)*/
    public void openSignupPage() {
        Intent intent=new Intent(this,signup_page.class);
        startActivity(intent);
    }





}
