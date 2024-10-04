package com.ucb.bottomnavigation;


public class Item {
    private int imageResId;
    private String text;
    private boolean isChecked;

    public Item(int imageResId, String text, boolean isChecked) {
        this.imageResId = imageResId;
        this.text = text;
        this.isChecked = isChecked;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setText(String text) {
        this.text = text;
    }
}
