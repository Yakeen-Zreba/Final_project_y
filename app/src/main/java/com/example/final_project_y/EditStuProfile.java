package com.example.final_project_y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditStuProfile extends AppCompatActivity {
    EditText text_input_stu_id, text_input_stu_name,text_input_stu_spec;
    Button stu_btn_save;
    TextView text_error_id,text_error_name,text_error_spe,text_stu_edit_pass;
    ImageView back_stu_prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //----

        setContentView(R.layout.edit_stu_profile_page);


        text_input_stu_id=(EditText) findViewById(R.id.text_input_stu_id);
        text_input_stu_name=(EditText) findViewById(R.id.text_input_stu_name);
        text_input_stu_spec=(EditText) findViewById(R.id.text_input_stu_spec);
        text_error_name=(TextView) findViewById(R.id.text_error_name);
        text_error_spe=(TextView) findViewById(R.id.text_error_spe);
        text_error_id=(TextView) findViewById(R.id.text_error_id);

        //back arrow
        back_stu_prof=(ImageView) findViewById(R.id.back_stu_prof);
        back_stu_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_back_stu_pass= new Intent(EditStuProfile.this,ProfileStu.class);
                startActivity(i_back_stu_pass);
            }
        });

        //edit password
        text_stu_edit_pass=(TextView) findViewById(R.id.text_stu_edit_pass);
        text_stu_edit_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_edit_stu_pass= new Intent(EditStuProfile.this,EditPassword.class);
                startActivity(i_edit_stu_pass);
            }
        });

        //save button
        stu_btn_save=(Button) findViewById(R.id.stu_btn_save);
        stu_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateId();
                validateName();
                validateSpec();
            }
        });
    }

    // check ID
    public  boolean validateId() {
        String t_i_tr_id=text_input_stu_id.getText().toString();

        if(TextUtils.isEmpty(t_i_tr_id)){
            text_error_id.setText("*required");
            text_input_stu_id.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_red));
            return false;
        }else{
            text_error_id.setText("");
            text_input_stu_id.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_orange));
            return true;
        }
    }

    //check Name
    public  boolean validateName() {
        String t_i_tr_name=text_input_stu_name.getText().toString();

        if(TextUtils.isEmpty(t_i_tr_name)){
            text_error_name.setText("*required");
            text_input_stu_name.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_red));
            return false;
        }else{
            text_error_name.setText("");
            text_input_stu_name.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_orange));
            return true;
        }
    }

    //check Specialization
    public  boolean validateSpec() {
        String t_i_tr_phone=text_input_stu_spec.getText().toString();

        if(TextUtils.isEmpty(t_i_tr_phone)){
            text_error_spe.setText("*required");
            text_input_stu_spec.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_red));
            return false;
        }else{
            text_error_spe.setText("");
            text_input_stu_spec.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_orange));
            return true;
        }
    }
}