package com.example.final_project_y.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.final_project_y.R;
import com.example.final_project_y.model.File;

import java.util.List;

public class FilesDataAdapter extends RecyclerView.Adapter<FilesDataAdapter.MyViewHolder>  {
    List<File> filesList;
    public SelectedItem selectedItem;
    Context context;

    public FilesDataAdapter(List<File> FilesList,SelectedItem mSelectedItem,Context context) {
        this.filesList = FilesList;
        this.context = context;
        this.selectedItem = mSelectedItem;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Button download_btn;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.file_name);
            download_btn = view.findViewById(R.id.btn_download);
            download_btn.setOnClickListener(view1 -> selectedItem.selectedItem(filesList.get(getAdapterPosition())));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new MyViewHolder(itemView);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        File File = filesList.get(position);
        holder.name.setText(File.getName());
    }
    @Override
    public int getItemCount() {
        return filesList.size();
    }

    public interface SelectedItem{
        void selectedItem(File file);
    }
}