package com.example.final_project_y.activity;

import static com.android.volley.Request.Method.GET;
import static com.example.final_project_y.COMMON.GROUPS_URL;
import static com.example.final_project_y.COMMON.USER_TOKEN;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.final_project_y.AppController;
import com.example.final_project_y.R;
import com.example.final_project_y.SessionManager;
import com.example.final_project_y.adapter.AnnouncementsAssignDataAdapter;
import com.example.final_project_y.adapter.AnnouncementsDataAdapter;

import com.example.final_project_y.model.AnnouncementAssign;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AnnouncementsAssignActivity extends AppCompatActivity {

    private static final String TAG = AnnouncementsAssignActivity.class.getSimpleName();
    private final List<AnnouncementAssign> announcementsList = new ArrayList<>();
    private AnnouncementsAssignDataAdapter mAdapter;
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

        setContentView(R.layout.activity_announcement_assignment);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            group_id = extras.getInt("group_id");
        }else
        {
            group_id = 0;
        }

        context = this;
        mAdapter = new AnnouncementsAssignDataAdapter(announcementsList, context);
        session = new SessionManager(this);
        USER_TOKEN = session.getToken();

        RecyclerView recyclerView = findViewById(R.id.announcements_assignment_rv);
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
            GrabAllAnnouncements();
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        back_to_main_page=(ImageView) findViewById(R.id.back_to_main_page);

        //Back arrow to main page
        back_to_main_page.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }

    private void GrabAllAnnouncements() {
        Log.e("TOKEN",USER_TOKEN);
        Log.e("TOKEN",GROUPS_URL);
        JsonObjectRequest jsonReq = new JsonObjectRequest(GET, GROUPS_URL +"/"+ group_id+"/announcements_assign", null, response -> {
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
                AnnouncementAssign announcement = new AnnouncementAssign();
                announcement.setId(feedObj.getInt("an_no"));
                announcement.setContent(feedObj.getString("an_content"));
                announcement.setDueDate(feedObj.getString("due_date"));
                announcement.setGrade(feedObj.getInt("grade"));
                announcementsList.add(announcement);
                mAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}