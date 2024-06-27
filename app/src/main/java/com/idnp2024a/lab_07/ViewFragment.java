package com.idnp2024a.lab_07;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewFragment extends Fragment {
    private TextView textViewAuthor, textViewTitle, textViewTechnique, textViewCategory, textViewDescription, textViewYear;
    private Button buttonEdit;

    public ViewFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        textViewAuthor = view.findViewById(R.id.textViewAuthor);
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTechnique = view.findViewById(R.id.textViewTechnique);
        textViewCategory = view.findViewById(R.id.textViewCategory);
        textViewDescription = view.findViewById(R.id.textViewDescription);
        textViewYear = view.findViewById(R.id.textViewYear);
        buttonEdit = view.findViewById(R.id.buttonEdit);
        loadData();
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new RegisterFragment())
                        .commit();
            }
        });
        return view;
    }
    private void loadData() {
        FileInputStream fis = null;
        try {
            fis = getActivity().openFileInput("painting.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String[] data = sb.toString().split("\n");
            if (data.length == 6) {
                textViewAuthor.setText("Autor: " + data[0]);
                textViewTitle.setText("Título: " + data[1]);
                textViewTechnique.setText("Técnica: " + data[2]);
                textViewCategory.setText("Categoría: " + data[3]);
                textViewDescription.setText("Descripción: " + data[4]);
                textViewYear.setText("Año: " + data[5]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}