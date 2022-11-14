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

public class EditPassword extends AppCompatActivity {
    EditText text_input_pass,text_input_confirm_pass;
    TextView text_error_pass,text_error_conf_pass;
    Button btn_save;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //----

        setContentView(R.layout.edit_password_page);

        text_input_pass=(EditText ) findViewById(R.id.text_input_pass);
        text_input_confirm_pass=(EditText ) findViewById(R.id.text_input_conf_pass);
        text_error_pass=(TextView) findViewById(R.id.text_error_pass);
        text_error_conf_pass=(TextView) findViewById(R.id.text_error_conf_pass);

        //back arrow
        back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_edit_tr_pass= new Intent(EditPassword.this,EditTrProfile.class);
                startActivity(i_edit_tr_pass);
            }
        });


        //save button
        btn_save=(Button) findViewById(R.id.btn_save_pass);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatePass();
            }
        });
    }

    public  boolean validatePass() {
        String t_i_pass=text_input_pass.getText().toString();
        String t_i_confirm_pass=text_input_confirm_pass.getText().toString();

        if(TextUtils.isEmpty(t_i_pass)){
            text_error_pass.setText("*required");
            text_input_pass.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_red));
            return false;
        }
        else if(TextUtils.isEmpty(t_i_confirm_pass)&&!TextUtils.isEmpty(t_i_pass)){
            text_error_conf_pass.setText("*Enter Again New Password");
            text_input_confirm_pass.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_red));
            text_error_pass.setText("");
            text_input_pass.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_orange));

            return false;
        }
        else if(!t_i_pass.equals(t_i_confirm_pass)) {
            text_error_conf_pass.setText("Password Would Not be matched");
            text_input_confirm_pass.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_red));

            return false;
        }
        else{
            text_error_pass.setText("");
            text_input_pass.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_orange));
            text_input_confirm_pass.setBackground(getDrawable(R.drawable.rectangle_text_input_stoke_orange));
            text_error_conf_pass.setText("");
            return true;
        }
    }
}
