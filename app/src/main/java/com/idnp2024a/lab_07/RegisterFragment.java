package com.idnp2024a.lab_07;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterFragment extends Fragment {
    private EditText editTextAuthor, editTextTitle, editTextTechnique, editTextCategory, editTextDescription, editTextYear;
    private Button buttonSave;

    public RegisterFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        editTextAuthor = view.findViewById(R.id.editTextAuthor);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextTechnique = view.findViewById(R.id.editTextTechnique);
        editTextCategory = view.findViewById(R.id.editTextCategory);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextYear = view.findViewById(R.id.editTextYear);
        buttonSave = view.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        return view;
    }
    private void saveData() {
        String author = editTextAuthor.getText().toString();
        String title = editTextTitle.getText().toString();
        String technique = editTextTechnique.getText().toString();
        String category = editTextCategory.getText().toString();
        String description = editTextDescription.getText().toString();
        String year = editTextYear.getText().toString();

        String data = author + "\n" + title + "\n" + technique + "\n" + category + "\n" + description + "\n" + year;

        FileOutputStream fos = null;
        try {
            fos = getActivity().openFileOutput("painting.txt", getActivity().MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ViewFragment())
                .commit();
    }
}