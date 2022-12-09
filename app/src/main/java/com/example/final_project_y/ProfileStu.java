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

public class ProfileStu extends AppCompatActivity {
    private TextView text_stu_photo, text_stu_id, text_stu_name, text_stu_spe;
    private Button stu_logout;
    private ImageView profile_stu_img,back_stu_to_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //-----------------------------------------------------

        //connect to layout
        setContentView(R.layout.profile_stu_page);
        //-----------------------------------------------------

        //to Disable landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //------------------------------------------------------


        //init views
        text_stu_photo = (TextView) findViewById(R.id.text_stu_photo);
        text_stu_id = (TextView) findViewById(R.id.text_view_stu_id);
        text_stu_name = (TextView) findViewById(R.id.text_view_stu_name);
        text_stu_spe = (TextView) findViewById(R.id.text_view_stu_spec);
        profile_stu_img = (ImageView) findViewById(R.id.profile_stu_img);

        //Back arrow to main page
        back_stu_to_main_page = (ImageView) findViewById(R.id.back_stu_to_main_page);
        back_stu_to_main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_back_stu_to_prof = new Intent(ProfileStu.this, MainPage.class);
                startActivity(i_back_stu_to_prof);
            }
        });

        //LogOut Button
        stu_logout = (Button) findViewById(R.id.btn_logout_stu);
        //--------------------------------------------------------



    }


}
