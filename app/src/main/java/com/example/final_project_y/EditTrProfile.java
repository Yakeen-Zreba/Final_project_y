package com.example.final_project_y;

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


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditTrProfile extends AppCompatActivity {
    EditText text_input_tr_name,text_input_tr_phone;
    Button tr_btn_save;
    TextView text_error_name,text_error_phone,text_tr_edit_pass;
    ImageView back_tr_prof;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //----

        setContentView(R.layout.edit_tr_profile_page);

        text_input_tr_name=(EditText) findViewById(R.id.text_input_tr_name);
        text_input_tr_phone=(EditText) findViewById(R.id.text_input_tr_phone);
        text_error_name=(TextView) findViewById(R.id.text_error_name);
        text_error_phone=(TextView) findViewById(R.id.text_error_phone);

        //back arrow
        back_tr_prof=(ImageView) findViewById(R.id.back_tr_prof);
        back_tr_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_back_tr_pass= new Intent(EditTrProfile.this,ProfileTr.class);
                startActivity(i_back_tr_pass);
            }
        });

        //edit password
        text_tr_edit_pass=(TextView) findViewById(R.id.text_tr_edit_pass);
        text_tr_edit_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_edit_tr_pass= new Intent(EditTrProfile.this,EditPassword.class);
                startActivity(i_edit_tr_pass);
            }
        });

        //save button
        tr_btn_save=(Button) findViewById(R.id.tr_btn_save);
        tr_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateName();
                validatePhone();
            }
        });
    }

    public  boolean validateName() {
        String t_i_tr_name=text_input_tr_name.getText().toString();

        if(TextUtils.isEmpty(t_i_tr_name)){
            text_error_name.setText("*required");
            text_input_tr_name.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_red));
            return false;
        }else{
            text_error_name.setText("");
            text_input_tr_name.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_orange));
            return true;
        }
    }

    public  boolean validatePhone() {
        String t_i_tr_phone=text_input_tr_phone.getText().toString();

        if(TextUtils.isEmpty(t_i_tr_phone)){
            text_error_phone.setText("*required");
            text_input_tr_phone.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_red));
            return false;
        }else if(t_i_tr_phone.length()<10){
            text_error_phone.setText("*Enter full phone number Start by 09***");
            text_input_tr_phone.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_red));
            return false;
        }
        else{
            text_error_phone.setText("");
            text_input_tr_phone.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_orange));
            return true;
        }
    }
}
