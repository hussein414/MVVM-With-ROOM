package com.example.myapplication.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.listiner.ItemClickListener;
import com.example.myapplication.data.model.NoteEntity;
import com.example.myapplication.databinding.NoteItemBinding;
import com.example.myapplication.ui.adapter.ui.NoteViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    List<NoteEntity> noteEntities = new ArrayList<>();
    ItemClickListener listener;

    public void setNoteEntities(List<NoteEntity> noteEntities) {
        this.noteEntities = noteEntities;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
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
        holder.setClickItem(noteEntities.get(position), listener, position);
    }

    @Override
    public int getItemCount() {
        return noteEntities.size();
    }

}
