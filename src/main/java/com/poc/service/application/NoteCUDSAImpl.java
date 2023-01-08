package com.poc.service.application;

import com.poc.exception.ErrorsEnum;
import com.poc.exception.FunctionalException;
import com.poc.model.domain.Note;
import com.poc.model.dto.NoteDTO;
import com.poc.mapper.NoteMapper;
import com.poc.service.business.NoteCUDSM;
import com.poc.service.business.NoteRSM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NoteCUDSAImpl implements NoteCUDSA {

    private final NoteCUDSM noteCUDSM;
    private final NoteRSM noteRSM;
    private final NoteMapper noteMapper;

    @Override
    public NoteDTO createNote(NoteDTO noteDTO) {
        if (noteDTO == null) {
            throw new FunctionalException(ErrorsEnum.ERR_MCS_NOTE_OBJECT_EMPTY.getErrorMessage());
        }
        noteDTO.setId(UUID.randomUUID());
        noteDTO.setCreatedAt(Instant.now());
        noteDTO.setUpdatedAt(Instant.now());
        return noteMapper.toDTO(noteCUDSM.createOrUpdateNote(noteMapper.toDO(noteDTO)));
    }

    @Override
    public List<NoteDTO> saveAllNotes(List<NoteDTO> notes) {
        List<NoteDTO> newNotes = new ArrayList<>();
        notes.forEach(note -> {
            note.setId(UUID.randomUUID());
            note.setCreatedAt(Instant.now());
            note.setUpdatedAt(Instant.now());
            newNotes.add(note);
        });
        return noteMapper.toDTO(noteCUDSM.saveAllNotes(noteMapper.toDO(newNotes)));
    }

    @Override
    public NoteDTO updateNote(NoteDTO noteDTO) {
        if (noteDTO == null || noteDTO.getId() == null) {
            throw new FunctionalException(ErrorsEnum.ERR_MCS_NOTE_OBJECT_EMPTY.getErrorMessage());
        }
        Note noteFound = noteRSM.getNoteById(noteDTO.getId());
        noteFound.setTitle(noteDTO.getTitle());
        noteFound.setContent(noteDTO.getContent());
        noteFound.setUpdatedAt(Instant.now());
        return noteMapper.toDTO(noteCUDSM.createOrUpdateNote(noteFound));
    }

    @Override
    public void deleteNoteById(UUID id) {
        noteCUDSM.deleteNoteById(id);
    }
}
