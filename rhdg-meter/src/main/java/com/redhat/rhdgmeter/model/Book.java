package com.redhat.rhdgmeter.model;

import com.redhat.benchmarklib.model.BaseModel;

public class Book extends BaseModel{

    private String title;
    private String author;
    


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
