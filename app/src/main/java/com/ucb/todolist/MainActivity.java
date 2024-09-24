package com.ucb.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new CustomAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click if needed
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        private final int[] images = {R.drawable.karen_13}; // Add your images
        private final List<String> texts = new ArrayList<>(); // Use List for dynamic updates

        {
            // Initialize with sample data
            texts.add("Item 1");
            texts.add("Item 2");
            texts.add("Item 3");
        }

        @Override
        public int getCount() {
            return texts.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }

            ImageView itemImage = convertView.findViewById(R.id.itemImage);
            TextView itemText = convertView.findViewById(R.id.itemText);
            CheckBox itemCheckBox = convertView.findViewById(R.id.itemCheckBox);

            itemImage.setImageResource(images[0]); // Set your image here
            itemText.setText(texts.get(position));

            itemImage.setOnTouchListener(new View.OnTouchListener() {
                private long lastTapTime = 0;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        long currentTime = System.currentTimeMillis();
                        if (currentTime - lastTapTime < 500) { // Double-tap detected
                            showOptionsDialog(position);
                        }
                        lastTapTime = currentTime;
                    }
                    return true;
                }
            });

            return convertView;
        }

        private void showOptionsDialog(final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Choose an action")
                    .setItems(new String[]{"Edit", "Delete"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                // Edit action
                                showEditTextDialog(position);
                            } else {
                                // Delete action
                                deleteItem(position);
                            }
                        }
                    });
            builder.show();
        }

        private void showEditTextDialog(final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final EditText input = new EditText(MainActivity.this);
            input.setText(texts.get(position));
            builder.setTitle("Edit Text")
                    .setView(input)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            texts.set(position, input.getText().toString());
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Cancel", null);
            builder.show();
        }

        private void deleteItem(int position) {
            texts.remove(position);
            notifyDataSetChanged();
        }
    }
}