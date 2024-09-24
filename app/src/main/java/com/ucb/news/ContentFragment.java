package com.ucb.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {

    private static final String ARG_CONTENT = "content";

    public static ContentFragment newInstance(String content) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView contentTextView = view.findViewById(R.id.contentTextView);
        if (getArguments() != null) {
            String content = getArguments().getString(ARG_CONTENT);
            contentTextView.setText(content);
        }
    }

    public void updateContent(String content) {
        // Ensure the view is non-null before updating the content
        if (getView() != null) {
            TextView contentTextView = getView().findViewById(R.id.contentTextView);
            if (contentTextView != null) {
                contentTextView.setText(content);
            }
        }
    }
}
