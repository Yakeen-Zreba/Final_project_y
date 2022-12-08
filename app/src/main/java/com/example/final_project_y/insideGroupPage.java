package com.example.final_project_y;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class insideGroupPage extends AppCompatActivity {

    private TextView text_view_g_name,text_view_tr_name,text_view_g_no;
    private Button btn_add_files;
    private ImageView back_to_main_page;

    //actionbar
    private ActionBar actionBar;

    //UI Views
    private ViewPager view_page;

    private ArrayList<cardItem> cardItemArrayList;
    private cardItemAdapter cardItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar and hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //------------------------------------------

        //connect to layout
        setContentView(R.layout.inside_group_page);
        //-------------------------------------------

        //to Disable landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //------------------------------------------------------

        //init actionbar
        actionBar = getSupportActionBar();

        //init UI Views
        text_view_g_name=(TextView) findViewById(R.id.text_view_g_name);
        text_view_tr_name=(TextView) findViewById(R.id.text_view_tr_name);
        text_view_g_no=(TextView) findViewById(R.id.text_view_g_no);
        btn_add_files=(Button) findViewById(R.id.btn_add_files);

        //back arrow to profile
        back_to_main_page=(ImageView) findViewById(R.id.back_to_main_page);
        back_to_main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_back_main_page= new Intent(insideGroupPage.this,main_page.class);
                startActivity(i_back_main_page);
            }
        });
        //--------------------------------------------------------

        view_page=findViewById(R.id.view_page);
        loadCards();

        //set viewpager change listener
        view_page.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //ill just change the title of action
                String title=cardItemArrayList.get(position).getTitle();
                actionBar.setTitle(title);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void loadCards() {
        // init list
        cardItemArrayList = new ArrayList<>();

        //add items to list
        cardItemArrayList.add(new cardItem(R.drawable.materials,"Materials"));
        cardItemArrayList.add(new cardItem(R.drawable.assignments,"Assignments"));
        cardItemArrayList.add(new cardItem(R.drawable.notiifications,"Notifications"));
        cardItemArrayList.add(new cardItem(R.drawable.inquiries,"Inquiries"));

        //setup adepter
        cardItemAdapter = new cardItemAdapter(this,cardItemArrayList);
        //set adapter to view
        view_page.setAdapter(cardItemAdapter);
        //set default padding from left/right
        view_page.setPadding(100,0,100,0);



    }
}
