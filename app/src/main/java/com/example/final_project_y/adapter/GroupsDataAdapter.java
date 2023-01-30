package com.example.final_project_y.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_y.R;
import com.example.final_project_y.model.Group;

import java.util.List;

public class GroupsDataAdapter extends RecyclerView.Adapter<GroupsDataAdapter.MyViewHolder>  {
    List<Group> groupsList; //اخت المصفوفة
    public SelectedItem selectedItem;
    Context context;

    public GroupsDataAdapter(List<Group> GroupsList,SelectedItem mSelectedItem,Context context) {
        this.groupsList = GroupsList;
        this.context = context;
        this.selectedItem = mSelectedItem;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Button group_btn;
        public MyViewHolder(View view) {
            super(view);
            group_btn = view.findViewById(R.id.btn_group);

            group_btn.setOnClickListener(view1 -> selectedItem.selectedItem(groupsList.get(getAdapterPosition())));// لجلب ال Position للعنصر الى تم الضغط عليه
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new MyViewHolder(itemView);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Group Group = groupsList.get(position);
        holder.group_btn.setText(Group.getName());
    }
    @Override
    public int getItemCount() {
        return groupsList.size();
    }

    public interface SelectedItem{
        void selectedItem(Group group);
    }
}