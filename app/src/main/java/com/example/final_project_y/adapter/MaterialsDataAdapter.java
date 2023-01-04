package com.example.final_project_y.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_y.R;
import com.example.final_project_y.model.Material;

import java.util.List;

public class MaterialsDataAdapter extends RecyclerView.Adapter<MaterialsDataAdapter.MyViewHolder> {
    public SelectedItem selectedItem;
    private final List<Material> MaterialsList;
    Context context;
    public
    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public Button open_btn;
        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.assignment_title_tv);
            open_btn = view.findViewById(R.id.btn_open_assignment);
            open_btn.setOnClickListener(view1 -> selectedItem.selectedItem(MaterialsList.get(getAdapterPosition())));
        }
    }

    public MaterialsDataAdapter(List<Material> MaterialsList, SelectedItem mSelectedItem, Context context) {
        this.MaterialsList = MaterialsList;
        this.context = context;
        this.selectedItem = mSelectedItem;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_assignment, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Material Material = MaterialsList.get(position);
        holder.title.setText(Material.getTitle());


    }

    public interface SelectedItem{
        void selectedItem(Material assignment);
    }
    @Override
    public int getItemCount() {
        return MaterialsList.size();
    }
}
