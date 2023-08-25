package com.example.quote_application.Model;

import com.google.gson.annotations.SerializedName;

public class Search_Quotes {
    @SerializedName("results")
    String author;
    String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
