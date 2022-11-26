package com.example.myapplication.ui.activties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.utils.Constance;
import com.example.myapplication.databinding.ActivityAddNoteBinding;

import java.util.Objects;

public class AddNoteActivity extends AppCompatActivity {

    private ActivityAddNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bindViews();
    }

    private void bindViews() {
        binding.numberPriority.setMinValue(1);
        binding.numberPriority.setMinValue(10);
        binding.buttonAdd.setOnClickListener(v -> {
            saveNote();
        });
        setUpdateNote();
    }


    private void saveNote() {
        String title = Objects.requireNonNull(binding.inputTitle.getText()).toString();
        String description = Objects.requireNonNull(binding.inputDescriptio.getText()).toString();
        int priority = binding.numberPriority.getValue();
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(Constance.TITLE_EXTRA, title);
        data.putExtra(Constance.DESCRIPTION_EXTRA, description);
        data.putExtra(Constance.PRIORITY_EXTRA, priority);
        int id = getIntent().getIntExtra(Constance.ID_EXTRA, -1);
        if (id != -1) {
            data.putExtra(Constance.ID_EXTRA, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }


    private void setUpdateNote() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constance.ID_EXTRA)) {
            binding.inputTitle.setText(intent.getStringExtra(Constance.TITLE_EXTRA));
            binding.inputDescriptio.setText(intent.getStringExtra(Constance.DESCRIPTION_EXTRA));
            binding.numberPriority.setValue(intent.getIntExtra(Constance.PRIORITY_EXTRA, 1));
        } else {
            Toast.makeText(this, "not changed", Toast.LENGTH_SHORT).show();
        }
    }

}