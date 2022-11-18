package com.example.final_project_y;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class ProfileTr extends AppCompatActivity {
    TextView text_tr_photo, text_tr_id, text_tr_name, text_tr_phone, text_tr_pass;
    Button tr_btn_edit ,tr_logout;
    ImageView back_tr_to_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //-----------------------------------------------------

        //connect to the layout
        setContentView(R.layout.profile_tr_page);
        //-----------------------------------------------------

        //to Disable landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //------------------------------------------------------

        text_tr_photo=(TextView) findViewById(R.id.text_tr_photo);
        text_tr_id=(TextView) findViewById(R.id.text_tr_id);
        text_tr_name=(TextView) findViewById(R.id.text_tr_name);
        text_tr_phone=(TextView) findViewById(R.id.text_tr_phone);
        text_tr_pass=(TextView) findViewById(R.id.text_view_pass);

        //Back arrow to main page
        back_tr_to_main_page=(ImageView) findViewById(R.id.back_tr_to_main_page);
        back_tr_to_main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_back_tr_to_prof= new Intent(ProfileTr.this,main_page.class);
                startActivity(i_back_tr_to_prof);
            }
        });
        //--------------------------------------------------------

        //LogOut Button
        tr_logout=(Button) findViewById(R.id.btn_logout_tr);
        //--------------------------------------------------------

        //Edit Button
        tr_btn_edit=(Button) findViewById(R.id.btn_edit_tr);
        tr_btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditPage();
            }
        });
        //--------------------------------------------------------

    }

    //to link the Profile tr page with the edit  page (method)
    private void openEditPage() {
        Intent i_edit_tr_page=new Intent(ProfileTr.this,EditTrProfile.class);
        startActivity(i_edit_tr_page);
    }
    //--------------------------------------------------------------



}
