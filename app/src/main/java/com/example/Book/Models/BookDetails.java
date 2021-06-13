package com.example.Book.Models;

import java.util.Vector;

public class BookDetails {
    private int rLikeButton;
    private int rfaceButton;
    private int rinstaButton;
    private int rtweetButton;
    private int rshareButton;
    private String rimageView;
    private String rBookNameTextView;
    private String rAuthorNameTextView;

    public BookDetails(int rLikeButton, int rfaceButton, int rinstaButton, int rtweetButton, int rshareButton, String rimageView, String rBookNameTextView, String rAuthorNameTextView) {
        this.rLikeButton = rLikeButton;
        this.rfaceButton = rfaceButton;
        this.rinstaButton = rinstaButton;
        this.rtweetButton = rtweetButton;
        this.rshareButton = rshareButton;
        this.rimageView = rimageView;
        this.rBookNameTextView = rBookNameTextView;
        this.rAuthorNameTextView = rAuthorNameTextView;
    }

    public int getrLikeButton() {
        return rLikeButton;
    }

    public int getRfaceButton() {
        return rfaceButton;
    }

    public int getRinstaButton() {
        return rinstaButton;
    }

    public int getRtweetButton() {
        return rtweetButton;
    }

    public int getRshareButton() {
        return rshareButton;
    }

    public String getRimageView() {
        return rimageView;
    }

    public String getrBookNameTextView() {
        return rBookNameTextView;
    }

    public String getrAuthorNameTextView() {
        return rAuthorNameTextView;
    }
}
