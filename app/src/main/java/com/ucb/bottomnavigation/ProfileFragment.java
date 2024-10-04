package com.ucb.bottomnavigation;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private ImageView profileImage;
    private EditText editTextName, editTextLastName;
    private RadioGroup radioGroupGender;
    private Button buttonEdit, buttonSave;

    private boolean isEditing = false;

    // Sample user data (replace with actual data retrieval logic)
    private String userName = "John";
    private String userLastName = "Doe";
    private String userGender = "Male"; // Can be "Male" or "Female"

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImage = view.findViewById(R.id.profileImage);
        editTextName = view.findViewById(R.id.editTextName);
        editTextLastName = view.findViewById(R.id.editTextLastName);
        radioGroupGender = view.findViewById(R.id.radioGroupGender);
        buttonEdit = view.findViewById(R.id.buttonEdit);
        buttonSave = view.findViewById(R.id.buttonSave);

        // Set initial values for the profile
        editTextName.setText(userName);
        editTextLastName.setText(userLastName);
        if (userGender.equals("Male")) {
            radioGroupGender.check(R.id.radioMale);
        } else {
            radioGroupGender.check(R.id.radioFemale);
        }

        buttonEdit.setOnClickListener(v -> {
            isEditing = true;
            enableEditing(true);
        });

        buttonSave.setOnClickListener(v -> {
            // Save the updated data (for now just disable editing)
            String name = editTextName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            int selectedId = radioGroupGender.getCheckedRadioButtonId();
            String gender = (selectedId == R.id.radioMale) ? "Male" : "Female";

            // Here you can add logic to save data, e.g., to a database or SharedPreferences
            userName = name;
            userLastName = lastName;
            userGender = gender;

            // Display updated information or save it as needed
            Toast.makeText(getActivity(), "Profile Updated!", Toast.LENGTH_SHORT).show();

            // For now, just disable editing
            isEditing = false;
            enableEditing(false);
        });

        enableEditing(false); // Start in read-only mode

        return view;
    }

    private void enableEditing(boolean enable) {
        editTextName.setEnabled(enable);
        editTextLastName.setEnabled(enable);
        radioGroupGender.setEnabled(enable);
        buttonSave.setVisibility(enable ? View.VISIBLE : View.GONE);
        buttonEdit.setVisibility(enable ? View.GONE : View.VISIBLE);
    }
}
