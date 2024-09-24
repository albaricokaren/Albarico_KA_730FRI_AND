package com.ucb.todolist;

public class Item {
    private final int imageResId; // Image resource ID
    private final String text;    // Text for the item
    private boolean checked;      // Checked status

    // Constructor
    public Item(int imageResId, String text, boolean checked) {
        this.imageResId = imageResId;
        this.text = text;
        this.checked = checked;
    }

    // Getter methods
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
}
