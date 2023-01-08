package com.poc.service.business;

import com.poc.model.domain.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface NoteRSM {
    public Note getNoteById(UUID id);
    public Page<Note> getAllNotesByTitle(String title, Pageable pageable);
}
