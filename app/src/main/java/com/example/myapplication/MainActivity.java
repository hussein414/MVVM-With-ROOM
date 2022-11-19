package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteViewModel=new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNote().observe(this, noteEntities ->
                Toast.makeText(MainActivity.this, "changed", Toast.LENGTH_SHORT).show());
    }
}