package com.example.final_project_y;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class signup_page extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        /*--------------------------------------------------------------------------*/
        /*to Disable landscape orientation*/
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        /*--------------------------------------------------------------------------*/
        /*to add onclick event to login button*/
        Button signupbtn = findViewById(R.id.signup_button);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainPage();
            }
        });/* the end of onclick event to login button in login page*/
        /*--------------------------------------------------------------------------*/

        /*to add onclick event to signup text*/
        TextView logintv = findViewById(R.id.signupText);
        logintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginPage();
            }
        });/* the end of onclick event to signup text at the end of  login page*/
        /*--------------------------------------------------------------------------*/


    }/* the end of on create*/

    public void openMainPage() {
        Intent intent = new Intent(this, main_page.class);
        startActivity(intent);
    }

    /*--------------------------------------------------------------------------*/

    /* to link the login page with the signup page (method)*/
    public void openLoginPage() {
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);
    }
}/* the end of the activity*/

/*--------------------------------------------------------------------------*/
