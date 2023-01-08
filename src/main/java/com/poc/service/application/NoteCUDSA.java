package com.poc.service.application;

import com.poc.model.dto.NoteDTO;

import java.util.List;
import java.util.UUID;

public interface NoteCUDSA {
    public NoteDTO createNote(NoteDTO noteDTO);
    public List<NoteDTO> saveAllNotes(List<NoteDTO> notes);
    public NoteDTO updateNote(NoteDTO noteDTO);
    public void deleteNoteById(UUID id);
}
