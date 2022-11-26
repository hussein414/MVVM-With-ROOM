package com.example.myapplication.ui.adapter.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.listiner.ItemClickListener;
import com.example.myapplication.data.model.NoteEntity;
import com.example.myapplication.databinding.NoteItemBinding;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private NoteItemBinding binding;

    public NoteViewHolder(NoteItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

   public void setDataNote(NoteEntity noteEntity) {
        binding.title.setText(noteEntity.getTitle());
        binding.description.setText(noteEntity.getDescription());
        binding.priority.setText(String.valueOf(noteEntity.getDescription()));
    }

    public void setClickItem(NoteEntity noteEntity, ItemClickListener listener, int pos) {
        binding.parent.setOnClickListener(v -> {
            if (listener != null && pos != RecyclerView.NO_POSITION) {
                listener.OnClickListener(noteEntity);
            }

        });
    }
}

