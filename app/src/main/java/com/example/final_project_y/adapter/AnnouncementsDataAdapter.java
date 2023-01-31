package com.example.final_project_y.adapter;

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
        public TextView content, due_date;

        public MyViewHolder(View view) {
            super(view);
            content = view.findViewById(R.id.content);
        //    due_date = view.findViewById(R.id.due_date);


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
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Announcement Announcement = AnnouncementsList.get(position);
        holder.content.setText(Announcement.getContent());
        holder.due_date.setText(Announcement.getDueDate());
    }

    @Override
    public int getItemCount() {
        return AnnouncementsList.size();
    }
}
