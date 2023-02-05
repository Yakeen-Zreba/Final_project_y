package com.example.final_project_y.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.final_project_y.AppController;
import com.example.final_project_y.COMMON;
import com.example.final_project_y.R;
import com.example.final_project_y.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    SessionManager session;
    EditText inputPassword, inputEmail;
    private ProgressDialog pDialog;
    String  full_name, password;
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // to hide nav
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        //-----------

        setContentView(R.layout.activity_login);
        session = new SessionManager(this);
        inputPassword = findViewById(R.id.password);
        inputEmail = findViewById(R.id.user_name);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        checkLogin();
        /*--------------------------------------------------------------------------*/
        /*to add onclick event to login button*/
        Button loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openMainPage();

                if (checkNotNULL()) {
                    password = Objects.requireNonNull(inputPassword.getText()).toString().trim();
                    full_name = Objects.requireNonNull(inputEmail.getText()).toString().trim();
                    Log.d(TAG, "P:" + full_name + "//W:" + password);
                    login();
                } else {
                       Toast.makeText(getApplicationContext(), R.string.please_enter_credentials, Toast.LENGTH_LONG).show();
                }

            }
        });/* the end of onclick event to login button in login page*/
        /*--------------------------------------------------------------------------*/


    }/* the end of on create*/


    /* to link the login page with the main page (method)*/
    public void openMainPage() {
    //    Intent intent=new Intent(this, MainPage.class);
    //    startActivity(intent);
    }

/*--------------------------------------------------------------------------*/



        private void login() {
            if (checkNotEmpty()) {
                sendLogin();
            } else {
                Toast.makeText(getApplicationContext(), "Please Enter your Credentials", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onBackPressed() {
            finishAffinity();
        }

        @Override
        public boolean onSupportNavigateUp() {
            //onBackPressed();
            finish();
            return true;
        }
        /**
         * function to verify login details in mysql db
         */
        private void sendLogin() {
            // Tag used to cancel the request
            String tag_string_req = "req_login";
            pDialog.setMessage("Please Wait ...");
            showDialog();
            StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, COMMON.LOGIN_URL, response -> {
                Log.d(TAG, "Login Response: " + response);
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean success = jObj.getBoolean("success");
                    if (success) {
                        session.setLogin(true);
                        JSONObject data = jObj.getJSONObject("data");
                        String token = data.getString("accessToken");
                        session.setToken(token);
                        COMMON.CURRENT_USER_PASSWORD = password;
                        COMMON.CURRENT_USER_EMAIL = full_name;
                        status = true;
                        session.setEmailPassword(full_name, password, status); // لتعبأت ال sharedPreferences بالقيم
                        startActivity(new Intent(LoginActivity.this, GroupsActivity.class));
                        finish();
                    } else {
                        String errorMsg = jObj.getString("message");
                        Log.e(TAG,errorMsg);
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e(TAG,e.getMessage());
                }
            }, error -> {
                Log.e(TAG, "Login Error: " + error.getMessage());
                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Accept", "application/json");
                    params.put("full_name", full_name);
                    params.put("password", password);
                    return params;
                }
            };
            AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_string_req);
        }



        private void checkLogin() {

            if (session.isLoggedIn() ) {
                Intent intent = new Intent(LoginActivity.this, GroupsActivity.class);
                startActivity(intent);
                finish();
            }
        }

        private boolean checkNotEmpty() {
            return (!password.isEmpty() || !full_name.isEmpty());
        }

        private boolean checkNotNULL() {
            return (!inputPassword.toString().isEmpty() || !inputEmail.toString().isEmpty());
        }


        private void showDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog() {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }

        public void onStart() {
            super.onStart();
            checkLogin();
        }
    }
