package com.example.final_project_y.model;

import com.example.final_project_y.COMMON;

public class File {
    int id;
    String name,link;

    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return COMMON.FILES_LINK + link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
