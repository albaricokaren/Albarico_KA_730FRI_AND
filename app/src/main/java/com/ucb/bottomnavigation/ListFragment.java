package com.ucb.bottomnavigation;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private ListView listView;
    private CustomAdapter adapter;
    private Button deleteButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.listView);
        deleteButton = view.findViewById(R.id.deleteButton);

        adapter = new CustomAdapter();
        listView.setAdapter(adapter);

        deleteButton.setOnClickListener(v -> adapter.deleteCheckedItems());

        return view;
    }

    private class CustomAdapter extends BaseAdapter {
        private final List<Item> items = new ArrayList<>();

        {
            items.add(new Item(R.drawable.karen_13, "University", false));
            items.add(new Item(R.drawable.karen_13, "Cebu", false));
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

            ImageView itemImage = convertView.findViewById(R.id.itemImage);
            TextView itemText = convertView.findViewById(R.id.itemText);
            CheckBox itemCheckBox = convertView.findViewById(R.id.itemCheckBox);

            Item item = items.get(position);

            itemImage.setImageResource(item.getImageResId());
            itemText.setText(item.getText());
            itemCheckBox.setChecked(item.isChecked());

            itemCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> item.setChecked(isChecked));

            itemImage.setOnClickListener(v -> showOptionsDialog(position));

            return convertView;
        }

        private void showOptionsDialog(final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose an action")
                    .setItems(new String[]{"Edit", "Delete"}, (dialog, which) -> {
                        if (which == 0) {
                            showEditTextDialog(position);
                        } else {
                            deleteItem(position);
                        }
                    }).show();
        }

        private void showEditTextDialog(final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final EditText input = new EditText(getActivity());
            input.setText(items.get(position).getText());
            builder.setTitle("Edit Text")
                    .setView(input)
                    .setPositiveButton("OK", (dialog, which) -> {
                        items.get(position).setText(input.getText().toString());
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }

        private void deleteItem(int position) {
            items.remove(position);
            notifyDataSetChanged();
        }

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
}
