package com.poc.service.application;

import com.poc.model.dto.NoteDTO;
import com.poc.utils.HelpPage;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface NoteRSA {
    public NoteDTO getNoteById(UUID id);
    public HelpPage<NoteDTO> getAllNotesByTitle(String title, Pageable pageable);
}
