package com.poc.service.application;

import com.poc.model.dto.NoteDTO;
import com.poc.mapper.NoteMapper;
import com.poc.service.business.NoteRSM;
import com.poc.utils.HelpPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NoteRSAImpl implements NoteRSA {

    private final NoteRSM noteRSM;
    private final NoteMapper noteMapper;

    @Override
    public NoteDTO getNoteById(UUID id) {
        return noteMapper.toDTO(noteRSM.getNoteById(id));
    }

    @Override
    public HelpPage<NoteDTO> getAllNotesByTitle(String title, Pageable pageable) {
        return noteMapper.toDTO(noteRSM.getAllNotesByTitle(title, pageable), pageable);
    }
}
