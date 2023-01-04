package com.example.final_project_y.model;

import java.util.ArrayList;

public class Assignment {
    int id;
    String title,description;
    ArrayList<File> files = new ArrayList<>();

    public Assignment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<File> getFiles() {return this.files;}

    public void setFiles(File file) {this.files.add(file);}
}
