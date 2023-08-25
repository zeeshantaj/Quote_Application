package com.example.quote_application.Model;

import com.google.gson.annotations.SerializedName;

public class Quote {
    private String author,a;
    private String text,q,content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getA() {
        return a;
    }

    public String getQ() {
        return q;
    }
}
