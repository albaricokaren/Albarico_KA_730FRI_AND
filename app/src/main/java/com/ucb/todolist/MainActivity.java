package com.ucb.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    private GestureDetector gestureDetector;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        deleteButton = findViewById(R.id.deleteButton);

        adapter = new CustomAdapter();
        listView.setAdapter(adapter);

        gestureDetector = new GestureDetector(this, new GestureListener());

        // Handle touch event for listView
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        // Delete all checked items on button click
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.deleteCheckedItems();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click if needed
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        private final int[] images = {R.drawable.karen_13}; // Add your images
        private final List<Item> items = new ArrayList<>(); // List for dynamic updates

        {
            // Initialize with sample data
            items.add(new Item(R.drawable.karen_13, "University", false));
            items.add(new Item(R.drawable.karen_13, " Cebu", false));
            items.add(new Item(R.drawable.karen_13, "Campus", false));
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }

            ImageView itemImage = convertView.findViewById(R.id.itemImage);
            TextView itemText = convertView.findViewById(R.id.itemText);
            CheckBox itemCheckBox = convertView.findViewById(R.id.itemCheckBox);

            final Item item = items.get(position);

            itemImage.setImageResource(item.getImageResId());
            itemText.setText(item.getText());
            itemCheckBox.setChecked(item.isChecked());

            itemCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                item.setChecked(isChecked);
            });

            itemImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return gestureDetector.onTouchEvent(event);
                }
            });

            return convertView;
        }

        // Method to show the options dialog for edit/delete
        private void showOptionsDialog(final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Choose an action")
                    .setItems(new String[]{"Edit", "Delete"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                showEditTextDialog(position);
                            } else {
                                deleteItem(position);
                            }
                        }
                    });
            builder.show();
        }

        private void showEditTextDialog(final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final EditText input = new EditText(MainActivity.this);
            input.setText(items.get(position).getText());
            builder.setTitle("Edit Text")
                    .setView(input)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            items.get(position).setText(input.getText().toString());
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Cancel", null);
            builder.show();
        }

        private void deleteItem(int position) {
            items.remove(position);
            notifyDataSetChanged();
        }

        // Delete all checked items
        public void deleteCheckedItems() {
            List<Item> toRemove = new ArrayList<>();
            for (Item item : items) {
                if (item.isChecked()) {
                    toRemove.add(item);
                }
            }
            items.removeAll(toRemove);
            notifyDataSetChanged();
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            int position = listView.pointToPosition((int) e.getX(), (int) e.getY());
            if (position != ListView.INVALID_POSITION) {
                adapter.showOptionsDialog(position);
            }
            return true;
        }
    }
}
