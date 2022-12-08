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

public class materialPage extends AppCompatActivity {

    TextView material_title,material_description;
    Button btn_open_material;
    ImageView back_to_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //------------------------------------------

        //connect to layout
        setContentView(R.layout.material_page);
        //-------------------------------------------

        //to Disable landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //------------------------------------------------------

        //init views
        material_title=(TextView) findViewById(R.id.material_title);
        material_description=(TextView) findViewById(R.id.material_description);
        btn_open_material=(Button) findViewById(R.id.btn_open_material);

        //back arrow to profile
        back_to_main_page=(ImageView) findViewById(R.id.back_to_main_page);
        back_to_main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_back_main_page= new Intent(materialPage.this,main_page.class);
                startActivity(i_back_main_page);
            }
        });
        //--------------------------------------------------------
    }
}
