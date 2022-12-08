package com.example.final_project_y;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class cardItemAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<cardItem> cardItemArrayList;

    //constructor
    public cardItemAdapter(Context context, ArrayList<cardItem> cardItemArrayList) {
        this.context = context;
        this.cardItemArrayList = cardItemArrayList;
    }



    @Override
    public int getCount() {
        return cardItemArrayList.size();//return size of items in list
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //inflate layout card_item_inside_group.xml
       View view = LayoutInflater.from(context).inflate(R.layout.card_item_inside_group,container,false);

       //init uid views from card_item_inside_group.xml
        ImageView image_view_material= view.findViewById(R.id.image_view_material);
        TextView card_title = view.findViewById(R.id.card_title);


        //get data
        cardItem card = cardItemArrayList.get(position);
        int image = card.getImage();
        String title=card.getTitle();

        //set data
        image_view_material.setImageResource(image);
        card_title.setText(title);

        //handle card click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
            }
        });

        //add view to container
        container.addView(view, position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
