package com.example.final_project_y;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ProfileStu extends AppCompatActivity {
    TextView text_stu_photo, text_stu_id, text_stu_name, text_stu_spe;
    Button stu_btn_edit,stu_logout;
    ImageView profile_stu_img,back_stu_to_main_page;
    private String strJson, apiUrl="http://192.168.0.104/final-project-php-master/controls/android_profile_stu.php";

    private OkHttpClient client;
    private Response response;
    private Request request;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //-----------------------------------------------------

        //connect to layout
        setContentView(R.layout.profile_stu_page);
        //-----------------------------------------------------

        //to Disable landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //------------------------------------------------------


        // for dialog message
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        //-------------------------------------------------------

        text_stu_photo=(TextView) findViewById(R.id.text_stu_photo);
        text_stu_id=(TextView) findViewById(R.id.text_view_stu_id);
        text_stu_name=(TextView) findViewById(R.id.text_view_stu_name);
        text_stu_spe=(TextView) findViewById(R.id.text_view_stu_spec);
        profile_stu_img=(ImageView) findViewById(R.id.profile_stu_img) ;

        //Back arrow to main page
        back_stu_to_main_page=(ImageView) findViewById(R.id.back_stu_to_main_page);
        back_stu_to_main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_back_stu_to_prof= new Intent(ProfileStu.this,main_page.class);
                startActivity(i_back_stu_to_prof);
            }
        });

        //LogOut Button
        stu_logout=(Button) findViewById(R.id.btn_logout_stu);
        //--------------------------------------------------------

        //edit button
        stu_btn_edit=(Button) findViewById(R.id.btn_edit_stu);
        stu_btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditPage();
            }
        });
        //----------------------------------------------------------------

        progressDialog.show();
        client =new OkHttpClient();
        new GetUserDataRequest().execute();
    }
    public class  GetUserDataRequest extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            request = new Request.Builder().url(apiUrl).build();
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            try {
                strJson = response.body().string();
                updateUserData(strJson);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUserData(String strJson) throws JSONException {
        try {
            JSONArray parent = new JSONArray(strJson);
            JSONObject child = parent.getJSONObject(0);

            //for other textView
            String stu_id=child.getString("stu_id");
            String stu_name=child.getString("full_name");
            String stu_spe=child.getString("stu_specialization");

            text_stu_id.setText(stu_id);
            text_stu_name.setText(stu_name);
            text_stu_spe.setText(stu_spe);

            progressDialog.hide();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //to link the Profile stu page with the edit  page (method)
    private void openEditPage() {
        Intent i_edit_stu_page=new Intent(ProfileStu.this, EditStuProfile.class);
        startActivity(i_edit_stu_page);
    }
    //--------------------------------------------------------------

}
