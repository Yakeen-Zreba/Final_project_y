package com.example.final_project_y.activity;

import static com.android.volley.Request.Method.GET;
import static com.example.final_project_y.COMMON.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.final_project_y.AppController;
import com.example.final_project_y.R;
import com.example.final_project_y.SessionManager;
import com.example.final_project_y.adapter.GroupsDataAdapter;
import com.example.final_project_y.model.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GroupsActivity extends AppCompatActivity implements GroupsDataAdapter.SelectedItem {

    private static final String TAG = GroupsActivity.class.getSimpleName();

    TextView name_tv,spec_tv,id_tv;

    String name,specialization,image_link,student_id;

    private final List<Group> groupsList = new ArrayList<>();
    private GroupsDataAdapter mAdapter;
    Context context;
    SessionManager session;
    private NetworkImageView main_pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // to hide ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        //-------------

        setContentView(R.layout.activity_groups);

        name_tv=findViewById(R.id.user_name);

        context = this;
        mAdapter = new GroupsDataAdapter(groupsList, this, context);
        session = new SessionManager(this);

        USER_TOKEN = session.getToken();

        RecyclerView recyclerView = findViewById(R.id.groups_rv);
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
            GrabAllGroups();
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        main_pic = (NetworkImageView) findViewById(R.id.profile_stu_img);

        //Back arrow to main page
        main_pic.setOnClickListener(view -> {
            startActivity(new Intent(GroupsActivity.this,ProfileActivity.class));
        });
    }

    private void GrabAllGroups() {
        JsonObjectRequest jsonReq = new JsonObjectRequest(GET, GROUPS_URL, null, response -> {
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
            JSONObject profileObj = (JSONObject) response.getJSONObject("profile");

            name = profileObj.getString("full_name");
            specialization = profileObj.getString("specialization");
            image_link = profileObj.getString("u_img");
            student_id = profileObj.getString("stu_id");

            name_tv.setText(name);
           // spec_tv.setText(specialization);
          //  id_tv.setText(student_id);
            loadImage(  AVATAR_LINK + image_link);
            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);
                Group group = new Group();
                group.setId(feedObj.getInt("g_no"));
                group.setName(feedObj.getString("g_name"));
                group.setTeacher_name(feedObj.getString("teacher_name"));

                groupsList.add(group);
                mAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadImage(String image_link) throws IOException {
        main_pic.setDefaultImageResId(R.drawable.circle_shape); // image for loading...
        main_pic.setImageUrl(image_link, AppController.getInstance().getImageLoader());
    }
    @Override
    public void selectedItem(Group group) {
        Intent i = new Intent(GroupsActivity.this, GroupActivity.class);
        i.putExtra("group_id",group.getId());
        i.putExtra("group_name",group.getName());
        i.putExtra("teacher_name",group.getTeacher_name());
        startActivity(i);
    }
}