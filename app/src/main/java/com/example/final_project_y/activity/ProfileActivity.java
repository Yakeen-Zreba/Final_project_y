package com.example.final_project_y.activity;

import static com.android.volley.Request.Method.GET;
import static com.example.final_project_y.COMMON.AVATAR_LINK;
import static com.example.final_project_y.COMMON.FILES_LINK;
import static com.example.final_project_y.COMMON.GROUPS_URL;
import static com.example.final_project_y.COMMON.PROFILE_URL;
import static com.example.final_project_y.COMMON.USER_TOKEN;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.final_project_y.AppController;
import com.example.final_project_y.R;
import com.example.final_project_y.SessionManager;
import com.example.final_project_y.model.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = GroupsActivity.class.getSimpleName();

    TextView name_tv,spec_tv,id_tv;
    ImageView avatar;
    String name,specialization,image_link,student_id;
    Button logOutBtn;
    Context context;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        context = this;
        session = new SessionManager(context);

        USER_TOKEN = session.getToken();
        logOutBtn = findViewById(R.id.btn_logout_stu);

        name_tv = findViewById(R.id.text_view_stu_name);
        spec_tv = findViewById(R.id.text_view_stu_spec);
        id_tv = findViewById(R.id.text_view_stu_id);
        //avatar = findViewById(R.id.profile_stu_img);

        logOutBtn.setOnClickListener(view -> {
            context.getSharedPreferences("file_share_app", 0).edit().clear().apply();
            finishAffinity();
        });
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

    }

    private void GrabAllGroups() {
        JsonObjectRequest jsonReq = new JsonObjectRequest(GET, PROFILE_URL, null, response -> {
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
            JSONObject feedObj = (JSONObject) response.getJSONObject("data");

            name = feedObj.getString("full_name");
            specialization = feedObj.getString("specialization");
            image_link = feedObj.getString("u_img");
            student_id = feedObj.getString("stu_id");

            name_tv.setText(name);
            spec_tv.setText(specialization);
            id_tv.setText(student_id);
            loadImage(  AVATAR_LINK + image_link);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadImage(String image_link) throws IOException {
//        URL newurl = new URL(image_link);
//        Bitmap image  = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
//        avatar.setImageBitmap(image);

        NetworkImageView nv = (NetworkImageView) findViewById(R.id.profile_stu_img);
        nv.setDefaultImageResId(R.drawable.profile_image_girl); // image for loading...
        nv.setImageUrl(image_link, AppController.getInstance().getImageLoader());

    }
}