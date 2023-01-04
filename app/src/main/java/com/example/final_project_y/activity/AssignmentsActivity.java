package com.example.final_project_y.activity;

import static com.android.volley.Request.Method.GET;
import static com.example.final_project_y.COMMON.GROUPS_URL;
import static com.example.final_project_y.COMMON.USER_TOKEN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.final_project_y.AppController;
import com.example.final_project_y.AssignmentsPage;

import com.example.final_project_y.R;
import com.example.final_project_y.SessionManager;
import com.example.final_project_y.adapter.AssignmentsDataAdapter;
import com.example.final_project_y.adapter.AssignmentsDataAdapter;
import com.example.final_project_y.adapter.GroupsDataAdapter;
import com.example.final_project_y.model.Assignment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.android.volley.Request.Method.GET;
import static com.example.final_project_y.COMMON.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Cache;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.final_project_y.AppController;
import com.example.final_project_y.R;
import com.example.final_project_y.adapter.AssignmentsDataAdapter;
import com.example.final_project_y.model.Assignment;
import com.example.final_project_y.model.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

    public class AssignmentsActivity extends AppCompatActivity implements AssignmentsDataAdapter.SelectedItem {

        private static final String TAG = AssignmentsActivity.class.getSimpleName();

        private final List<Assignment> assignmentsList = new ArrayList<>();
        private AssignmentsDataAdapter mAdapter;
        Context context;
        SessionManager session;
        private int group_id;
        private ImageView back_to_main_page;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            Objects.requireNonNull(getSupportActionBar()).hide();

            setContentView(R.layout.activity_assignments);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                group_id = extras.getInt("group_id");
            }else
            {
                group_id = 0;
            }
            context = this;
            mAdapter = new AssignmentsDataAdapter(assignmentsList, this, context);
            session = new SessionManager(this);
            USER_TOKEN = session.getToken();

            RecyclerView recyclerView = findViewById(R.id.assignments_rv);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

            Cache cache = AppController.getInstance().getRequestQueue().getCache();
            Cache.Entry entry = cache.get(GROUPS_URL);
            if (entry != null) {
                String data = new String(entry.data, StandardCharsets.UTF_8);
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                GrabAllAssignments();
            }
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            back_to_main_page=(ImageView) findViewById(R.id.back_to_main_page);

            //Back arrow to main page
            back_to_main_page.setOnClickListener(view -> {
                super.onBackPressed();
            });
        }

        private void GrabAllAssignments() {
            Log.e("TOKEN",USER_TOKEN);
            Log.e("TOKEN",GROUPS_URL);
            JsonObjectRequest jsonReq = new JsonObjectRequest(GET, GROUPS_URL +"/"+ group_id+"/assignments", null, response -> {
                VolleyLog.d(TAG, "Response: " + response.toString());
                Log.e("RE", response.toString());
                parseJsonFeed(response);
            }, error -> Log.d("VOLLEY ERROR", error.toString())) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headerMap = new HashMap<>();
                    headerMap.put("Content-Type", "application/json");
                    headerMap.put("Authorization", "Bearer " + USER_TOKEN);
                    return headerMap;
                }
            };
            AppController.getInstance().addToRequestQueue(jsonReq);
        }

        @SuppressLint("NotifyDataSetChanged")
        private void parseJsonFeed(JSONObject response) {
            try {
                JSONArray feedArray = response.getJSONArray("data");
                for (int i = 0; i < feedArray.length(); i++) {
                    JSONObject feedObj = (JSONObject) feedArray.get(i);
                    Assignment assignment = new Assignment();
                    assignment.setId(feedObj.getInt("p_no"));
                    assignment.setTitle(feedObj.getString("title"));
                    assignment.setDescription(feedObj.getString("description"));
                    assignmentsList.add(assignment);
                    mAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void selectedItem(Assignment assignment) {
            Intent i = new Intent(AssignmentsActivity.this, AssignmentActivity.class);
            i.putExtra("assignment_id",assignment.getId());
            startActivity(i);

        }
    }

