package com.example.final_project_y.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.final_project_y.R;
import com.example.final_project_y.model.Announcement;
import java.util.List;

public class AnnouncementsDataAdapter extends RecyclerView.Adapter<AnnouncementsDataAdapter.MyViewHolder> {
    private final List<Announcement> AnnouncementsList;
    Context context;
    public
    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView content;

        public MyViewHolder(View view) {
            super(view);
            content = view.findViewById(R.id.tv_an_context);
        }
    }

    public AnnouncementsDataAdapter(List<Announcement> AnnouncementsList, Context context) {
        this.AnnouncementsList = AnnouncementsList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_announcement, parent, false);

        return new MyViewHolder(itemView);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Announcement Announcement = AnnouncementsList.get(position);
        holder.content.setText(Announcement.getContent());
    }

    @Override
    public int getItemCount() {
        return AnnouncementsList.size();
    }
}
