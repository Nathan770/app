package com.example.app;

public class ItemData {
    private int imagePosition;
    private String xText;
    private String yText;
    private String score;

    public ItemData(int imagePosition, String score, String xText, String yText) {
        this.imagePosition = imagePosition;
        this.score = score;
        this.xText = xText;
        this.yText = yText;

    }


    public int getimagePosition() {
        return imagePosition;
    }

    public String getxText() {
        return xText;
    }

    public String getyText() {
        return yText;
    }

    public String getscore() {
        return score;
    }

    public void setimagePosition(int imagePosition) {
        this.imagePosition = imagePosition;
    }

    public void setxText(String xText) {
        this.xText = xText;
    }

    public void setyText(String yText) {
        this.yText = yText;
    }

    public void setscore(String score) {
        this.score = score;
    }

}
