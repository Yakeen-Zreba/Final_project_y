package com.example.final_project_y;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InsideGroupPage extends AppCompatActivity {

    private Button btn_assignment,btn_material,btn_enquiry,btn_notification,btn_add_files;
    private ImageView back_to_main_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //-----------------------------------------------------

        //connect to layout
        setContentView(R.layout.inside_group_page);
        //-----------------------------------------------------

        //to Disable landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //------------------------------------------------------

        //init views
        btn_assignment = (Button) findViewById(R.id.btn_assignment);
        btn_material = (Button) findViewById(R.id.btn_material);
        btn_enquiry = (Button) findViewById(R.id.btn_enquiry);
        btn_notification = (Button) findViewById(R.id.btn_notification);
        btn_add_files = (Button) findViewById(R.id.btn_add_files);
        back_to_main_page=(ImageView) findViewById(R.id.back_to_main_page);

        //Back arrow to main page
        back_to_main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_back_stu_to_prof= new Intent(InsideGroupPage.this, MainPage.class);
                startActivity(i_back_stu_to_prof);
            }
        });

        //Assignment Button
        btn_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_to_assignment= new Intent(InsideGroupPage.this, AssignmentsPage.class);
                startActivity(i_to_assignment);
            }
        });

        //Material Button
        btn_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_to_material= new Intent(InsideGroupPage.this, MaterialsPage.class);
                startActivity(i_to_material);
            }
        });

    }
}
