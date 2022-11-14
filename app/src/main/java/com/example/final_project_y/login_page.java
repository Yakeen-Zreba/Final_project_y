package com.example.final_project_y;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        /*to add onclick event to signup text*/
        TextView signuptv=findViewById(R.id.signupText);
        signuptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupPage();
            }
        });/* the end of onclick event to signup text at the end of  login page*/
        /*--------------------------------------------------------------------------*/

    }/* the end of on create*/


    /* to link the login page with the main page (method)*/
    public void openMainPage() {
        Intent intent=new Intent(this,main_page.class);
        startActivity(intent);
    }

    /*--------------------------------------------------------------------------*/

    /* to link the login page with the signup page (method)*/
    public void openSignupPage() {
        Intent intent=new Intent(this,signup_page.class);
        startActivity(intent);
    }

    /*--------------------------------------------------------------------------*/

}
