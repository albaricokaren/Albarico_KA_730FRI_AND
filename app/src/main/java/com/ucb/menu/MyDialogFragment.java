package com.ucb.menu;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;

public class MyDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Karen Albarico")
                .setMessage("4th year college student")
                .setPositiveButton("Positive", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to AboutUsFragment (Option 1)
                        AboutUsFragment aboutUsFragment = new AboutUsFragment();
                        FragmentManager fragmentManager = getParentFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, aboutUsFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                })
                .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Go back to Option 3 (More Options)
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            activity.onOptionsItemSelected(activity.findViewById(R.id.option3));
                        }
                    }
                });
        return builder.create();
    }
}

