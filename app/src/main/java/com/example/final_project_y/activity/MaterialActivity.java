package com.example.final_project_y.activity;

import static com.android.volley.Request.Method.GET;
import static com.example.final_project_y.COMMON.ASSIGNMENTS_URL;
import static com.example.final_project_y.COMMON.GROUPS_URL;
import static com.example.final_project_y.COMMON.MATERIALS_URL;
import static com.example.final_project_y.COMMON.USER_TOKEN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.final_project_y.AppController;
import com.example.final_project_y.R;
import com.example.final_project_y.SessionManager;
import com.example.final_project_y.adapter.FilesDataAdapter;
import com.example.final_project_y.model.Assignment;
import com.example.final_project_y.model.File;

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
        import static com.example.final_project_y.COMMON.ASSIGNMENTS_URL;
        import static com.example.final_project_y.COMMON.GROUPS_URL;
        import static com.example.final_project_y.COMMON.USER_TOKEN;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.DefaultItemAnimator;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.nfc.Tag;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.TextView;

        import com.android.volley.Cache;
        import com.android.volley.VolleyLog;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.example.final_project_y.AppController;
        import com.example.final_project_y.R;
        import com.example.final_project_y.SessionManager;
        import com.example.final_project_y.adapter.FilesDataAdapter;
        import com.example.final_project_y.adapter.GroupsDataAdapter;
        import com.example.final_project_y.model.Assignment;
        import com.example.final_project_y.model.File;
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
public class MaterialActivity extends AppCompatActivity implements FilesDataAdapter.SelectedItem{

    private static final String TAG = AssignmentActivity.class.getSimpleName();

    private final List<File> fileList = new ArrayList<>();
    private FilesDataAdapter mAdapter;
    Context context;
    SessionManager session;
    int id = 0;
    TextView title,description;
    private ImageView back_to_main_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_material);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("assignment_id");
        }else
        {
            id = 0;
        }
        context = this;
        mAdapter = new FilesDataAdapter(fileList, this, context);
        session = new SessionManager(this);

        USER_TOKEN = session.getToken();


        description = findViewById(R.id.description);
        title = findViewById(R.id.material_title);
        RecyclerView recyclerView = findViewById(R.id.material_files_rv);
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
            GrabAssignmentAndFiles();
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        back_to_main_page=(ImageView) findViewById(R.id.back_to_main_page);

        //Back arrow to main page
        back_to_main_page.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }
    private void GrabAssignmentAndFiles() {
        if(id > 0){
            JsonObjectRequest jsonReq = new JsonObjectRequest(GET, MATERIALS_URL + "/" + id, null, response -> {
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
        }else{
            Log.e(TAG,"Cant find this assignment");
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void parseJsonFeed(JSONObject response) {
        try {
            JSONObject feedObj = response.getJSONObject("data");
            Assignment assignment = new Assignment();
            assignment.setId(feedObj.getInt("p_no"));
            assignment.setTitle(feedObj.getString("title"));
            assignment.setDescription(feedObj.getString("description"));
            JSONArray filesArray = feedObj.getJSONArray("files");
            for (int f = 0; f < filesArray.length(); f++) {
                JSONObject fileObj = (JSONObject) filesArray.get(f);
                File file = new File();
                file.setId(fileObj.getInt("f_no"));
                file.setName(fileObj.getString("f_name"));
                file.setLink(fileObj.getString("f_name"));
                assignment.setFiles(file);
                fileList.add(file);
            }
            mAdapter.notifyDataSetChanged();

            title.setText(assignment.getTitle());
            description.setText(assignment.getDescription());



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectedItem(File file) {
        Log.e(TAG,"Downloading");

        Uri uri = Uri.parse(file.getLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}