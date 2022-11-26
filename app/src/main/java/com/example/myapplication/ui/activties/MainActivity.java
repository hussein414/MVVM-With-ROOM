package com.example.myapplication.ui.activties;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.utils.Constance;
import com.example.myapplication.ui.adapter.NoteAdapter;
import com.example.myapplication.ui.viewmodel.NoteViewModel;
import com.example.myapplication.data.model.NoteEntity;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    NoteViewModel noteViewModel;
    private ActivityMainBinding binding;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bindViews();
    }

    private void bindViews() {
        noteAdapter = new NoteAdapter();
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNote().observe(this, noteEntities -> {
            Toast.makeText(MainActivity.this, "changed", Toast.LENGTH_SHORT).show();
            noteAdapter.setNoteEntities(noteEntities);
        });
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(noteAdapter);
        binding.adding.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(intent, Constance.ADD_NOTE_REQUEST);
        });
        setDeleteItem();
        binding.deleteAll.setOnClickListener(v -> {
            noteViewModel.deleteAll();
            Toast.makeText(this, "all notes deleted", Toast.LENGTH_SHORT).show();
        });

        //itemClickListener
        noteAdapter.setOnItemClickListener(noteEntity -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            intent.putExtra(Constance.TITLE_EXTRA, noteEntity.getTitle());
            intent.putExtra(Constance.DESCRIPTION_EXTRA, noteEntity.getDescription());
            intent.putExtra(Constance.PRIORITY_EXTRA, noteEntity.getPerperties());
            intent.putExtra(Constance.ID_EXTRA, noteEntity.getId());
            startActivityForResult(intent, Constance.EDIT_NOTE_REQUEST);

        });
    }

    private void setDeleteItem() {
        //delete item
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "note delete", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constance.ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            savedNote(data);
        } else if (requestCode == Constance.EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            updateNote(data);
        } else {
            Toast.makeText(MainActivity.this, "note not saved", Toast.LENGTH_SHORT).show();

        }
    }

    private void savedNote(@NonNull Intent data) {
        String title = data.getStringExtra(Constance.TITLE_EXTRA);
        String description = data.getStringExtra(Constance.DESCRIPTION_EXTRA);
        int priority = data.getIntExtra(Constance.PRIORITY_EXTRA, 1);
        NoteEntity noteEntity = new NoteEntity(title, description, priority);
        noteViewModel.insert(noteEntity);
        Toast.makeText(MainActivity.this, "note saved", Toast.LENGTH_SHORT).show();
    }

    private void updateNote(Intent data) {
        int id = data.getIntExtra(Constance.ID_EXTRA, -1);
        if (id == -1) {
            Toast.makeText(this, "note cant be update", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = data.getStringExtra(Constance.TITLE_EXTRA);
        String description = data.getStringExtra(Constance.DESCRIPTION_EXTRA);
        int priority = data.getIntExtra(Constance.PRIORITY_EXTRA, 1);
        NoteEntity noteEntity = new NoteEntity(title, description, priority);
        noteEntity.setId(id);
        noteViewModel.update(noteEntity);
        Toast.makeText(this, "note  updated", Toast.LENGTH_SHORT).show();
    }
}