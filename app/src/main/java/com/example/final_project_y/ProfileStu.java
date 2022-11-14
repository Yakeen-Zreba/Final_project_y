package com.example.final_project_y;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileStu extends AppCompatActivity {
    TextView text_stu_photo, text_stu_id, text_stu_name, text_stu_spe, text_stu_pass;
    Button stu_btn_edit,stu_logout;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //----

        setContentView(R.layout.profile_stu_page);

        text_stu_photo=(TextView) findViewById(R.id.text_stu_photo);
        text_stu_id=(TextView) findViewById(R.id.text_view_stu_id);
        text_stu_name=(TextView) findViewById(R.id.text_view_stu_name);
        text_stu_spe=(TextView) findViewById(R.id.text_view_stu_spec);
        text_stu_pass=(TextView) findViewById(R.id.text_view_stu_pass);

        stu_logout=(Button) findViewById(R.id.btn_logout_stu);

        stu_btn_edit=(Button) findViewById(R.id.btn_edit_stu);

        stu_btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_edit_stu_page=new Intent(ProfileStu.this, EditStuProfile.class);
                startActivity(i_edit_stu_page);
            }
        });
    }



}
