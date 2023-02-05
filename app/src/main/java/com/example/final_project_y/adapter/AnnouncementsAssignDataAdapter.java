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
import com.example.final_project_y.model.AnnouncementAssign;

import java.util.List;

public class AnnouncementsAssignDataAdapter extends RecyclerView.Adapter<AnnouncementsAssignDataAdapter.MyViewHolder> {
    private final List<AnnouncementAssign> AnnouncementsList;
    Context context;
    public
    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView content, due_date,create_date,grade;

        public MyViewHolder(View view) {
            super(view);
            content = view.findViewById(R.id.tv_an_context);
            create_date = view.findViewById(R.id.tv_create_at);
            grade = view.findViewById(R.id.tv_point);
            due_date = view.findViewById(R.id.tv_due_date);


        }
    }

    public AnnouncementsAssignDataAdapter(List<AnnouncementAssign> AnnouncementsList, Context context) {
        this.AnnouncementsList = AnnouncementsList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_announcement_assignment, parent, false);

        return new MyViewHolder(itemView);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AnnouncementAssign AnnouncementAssign = AnnouncementsList.get(position);
        holder.content.setText(AnnouncementAssign.getContent());
        holder.create_date.setText(AnnouncementAssign.getContent());
        holder.due_date.setText(AnnouncementAssign.getDueDate());
        holder.grade.setText(""+AnnouncementAssign.getGrade()+"");
    }

    @Override
    public int getItemCount() {
        return AnnouncementsList.size();
    }
}
