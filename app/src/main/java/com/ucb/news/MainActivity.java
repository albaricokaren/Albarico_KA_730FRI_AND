package com.ucb.news;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements HeadlineFragment.OnHeadlineSelectedListener {

    private boolean isLandscape;
    private String[] contents = {
            "ABS-CBN News is one of the leading news networks in the Philippines.",
            "GMA News is recognized for its reliable news coverage and commitment to journalistic integrity in the Philippines.",
            "TV5 News delivers timely and accurate news coverage, focusing on current events, sports, and entertainment."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState == null) {
            // Add Headline Fragment
            HeadlineFragment headlineFragment = new HeadlineFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container_headline, headlineFragment, "headline_fragment");

            // Add Content Fragment if in landscape mode
            if (isLandscape) {
                ContentFragment contentFragment = new ContentFragment();
                transaction.replace(R.id.fragment_container_content, contentFragment, "content_fragment");
            }
            transaction.commit();
        }
    }

    @Override
    public void onHeadlineSelected(int position) {
        String content = contents[position];

        if (isLandscape) {
            // Try to find the existing ContentFragment by tag
            ContentFragment contentFragment = (ContentFragment) getSupportFragmentManager().findFragmentByTag("content_fragment");

            if (contentFragment != null) {
                // If fragment is already present, update its content
                contentFragment.updateContent(content);
            } else {
                // If fragment is not found, replace it with a new one
                ContentFragment newContentFragment = ContentFragment.newInstance(content);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_content, newContentFragment, "content_fragment");
                transaction.commit();
            }
        } else {
            // In portrait mode, replace the headline fragment with the content fragment
            ContentFragment newFragment = ContentFragment.newInstance(content);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_headline, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
