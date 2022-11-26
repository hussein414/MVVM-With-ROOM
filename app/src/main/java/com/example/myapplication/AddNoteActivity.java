package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
        binding.close.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
        binding.buttonAdd.setOnClickListener(v -> {
            saveNote();
        });
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
        setResult(RESULT_OK, data);
        finish();
    }
}