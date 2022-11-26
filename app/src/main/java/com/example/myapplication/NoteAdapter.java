package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.NoteItemBinding;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    List<NoteEntity> noteEntities = new ArrayList<>();

    public void setNoteEntities(List<NoteEntity> noteEntities) {
        this.noteEntities = noteEntities;
        notifyDataSetChanged();
    }

    public NoteEntity getNoteAt(int position) {
        return noteEntities.get(position);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(NoteItemBinding.inflate(LayoutInflater
                        .from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setDataNote(noteEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return noteEntities.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        private NoteItemBinding binding;

        public NoteViewHolder(NoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setDataNote(NoteEntity noteEntity) {
            binding.title.setText(noteEntity.getTitle());
            binding.description.setText(noteEntity.getDescription());
            binding.priority.setText(String.valueOf(noteEntity.getDescription()));
        }
    }
}
