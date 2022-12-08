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

public class assignmentTrPage extends AppCompatActivity {

    private TextView text_view_assignment_tr_title ,text_view_stu_name,text_view_stu_id;
    private Button btn_open_assignment;
    private ImageView back_to_inside_group_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //------------------------------------------

        //connect to layout
        setContentView(R.layout.assignment_stu_page);
        //-------------------------------------------

        //to Disable landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //------------------------------------------------------

        //init views
        text_view_assignment_tr_title=(TextView) findViewById(R.id.text_view_assignment_tr_title);
        text_view_stu_name=(TextView) findViewById(R.id.text_view_stu_name);
        text_view_stu_id=(TextView) findViewById(R.id.text_view_stu_id);
        btn_open_assignment=(Button) findViewById(R.id.btn_open_assignment);

        //back arrow to profile
        back_to_inside_group_page=(ImageView) findViewById(R.id.back_to_inside_group_page);
        back_to_inside_group_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_back_inside_group_page= new Intent(assignmentTrPage.this,insideGroupPage.class);
                startActivity(i_back_inside_group_page);
            }
        });
        //--------------------------------------------------------

    }
}
