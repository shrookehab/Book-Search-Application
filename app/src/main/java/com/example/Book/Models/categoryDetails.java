package com.example.Book.Models;

public class categoryDetails {
    private int categImgView;
    private String categText;

    public categoryDetails(int categImgView, String categText) {
        this.categImgView = categImgView;
        this.categText = categText;
    }

    public int getCategImgView() {
        return categImgView;
    }

    public String getCategText() {
        return categText;
    }
}
