package com.example.flavioluiz.testesantander.repository.response;

import com.google.gson.annotations.SerializedName;

public class Statement {

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String description;

    @SerializedName("date")
    private String date;

    @SerializedName("value")
    private String value;

    public Statement(String title, String description, String date, String value){
        this.title = title;
        this.description = description;
        this.date = date;
        this.value = value;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return "R$ " + value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
