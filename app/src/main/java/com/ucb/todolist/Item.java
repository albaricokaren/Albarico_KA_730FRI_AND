package com.ucb.todolist;

public class Item {
    private int imageResId;
    private String text;
    private boolean checked;

    public Item(int imageResId, String text, boolean checked) {
        this.imageResId = imageResId;
        this.text = text;
        this.checked = checked;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setText(String text) {
        this.text = text;
    }
}
