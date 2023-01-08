package com.poc.service.business;

import com.poc.model.domain.Note;
import com.poc.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoteCUDSMImpl implements NoteCUDSM {

    private final NoteRepository noteRepository;
    private final NoteRSM noteRSM;

    @Override
    public Note createOrUpdateNote(Note note) {
        try {
            log.info("----- createOrUpdateNote");
            return noteRepository.save(note);
        } catch (Exception e) {
            log.error("Error createOrUpdateNote : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Note> saveAllNotes(List<Note> notes) {
        try {
            log.info("----- saveAllNotes");
            return noteRepository.saveAll(notes);
        } catch (Exception e) {
            log.error("Error saveAllNotes : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteNoteById(UUID id) {
        try {
            log.info("----- deleteNoteById : {}", id);
            Note noteFound = noteRSM.getNoteById(id);
            noteRepository.deleteById(noteFound.getId());
        } catch (Exception e) {
            log.error("Error deleteNoteById : {}", e.getMessage());
            throw e;
        }
    }
}
