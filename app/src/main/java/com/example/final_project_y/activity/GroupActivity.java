package com.example.final_project_y.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.final_project_y.MainPage;
import com.example.final_project_y.R;
import com.example.final_project_y.model.Announcement;
import com.example.final_project_y.model.Group;

public class GroupActivity extends AppCompatActivity {

    private TextView group_name,teacher_name,group_code;
    private Button btn_assignment,btn_material,btn_enquiry,btn_notification,btn_add_files;
    private ImageView back_to_main_page;
    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //connect to layout
        setContentView(R.layout.activity_group);
        //-----------------------------------------------------

        //to Disable landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //------------------------------------------------------
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            group = new Group();
            group.setId(extras.getInt("group_id"));
            group.setName(extras.getString("group_name"));
            group.setTeacher_name(extras.getString("teacher_name"));
        }else
        {
            group = null;
        }
        group_name =  findViewById(R.id.g_name);
        teacher_name =  findViewById(R.id.t_name);
        group_code =  findViewById(R.id.g_no);

        if(group!=null){
            group_name.setText(" " + group.getName() + " ");
            teacher_name.setText(" " + group.getTeacher_name() + " ");
            group_code.setText(" " + group.getId() + " ");
        }


        //init views
        btn_assignment = (Button) findViewById(R.id.btn_assignment);
        btn_material = (Button) findViewById(R.id.btn_material);
        btn_enquiry = (Button) findViewById(R.id.btn_enquiry);
        btn_notification = (Button) findViewById(R.id.btn_notification);
        btn_add_files = (Button) findViewById(R.id.btn_add_files);
        back_to_main_page=(ImageView) findViewById(R.id.back_to_main_page);

        //Back arrow to main page
        back_to_main_page.setOnClickListener(view -> {
            super.onBackPressed();
        });

        //Assignment Button
        btn_assignment.setOnClickListener(view -> {

            Intent intent= new Intent(GroupActivity.this, AssignmentsActivity.class);
            intent.putExtra("group_id", group.getId());
            startActivity(intent);
        });

        //Material Button
        btn_material.setOnClickListener(view -> {
            Intent intent= new Intent(GroupActivity.this, MaterialsActivity.class);
            intent.putExtra("group_id", group.getId());
            startActivity(intent);
        });

        btn_notification.setOnClickListener(view -> {
            Intent intent= new Intent(GroupActivity.this, AnnouncementsActivity.class);
            intent.putExtra("group_id", group.getId());
            startActivity(intent);
        });

    }
}
