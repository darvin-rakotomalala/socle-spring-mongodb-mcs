package com.poc.service.business;

import com.poc.exception.ErrorsEnum;
import com.poc.exception.FunctionalException;
import com.poc.model.domain.Note;
import com.poc.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoteRSMImpl implements NoteRSM {

    private final NoteRepository noteRepository;

    @Override
    public Note getNoteById(UUID id) {
        try {
            log.info("----- getNoteById : {}", id);
            Optional<Note> optionalNote = noteRepository.findById(id);
            if (optionalNote.isEmpty()) {
                throw new FunctionalException(ErrorsEnum.ERR_MCS_NOTE_NOT_FOUND.getErrorMessage());
            }
            return optionalNote.get();
        } catch (Exception e) {
            log.error("Error getNoteById : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<Note> getAllNotesByTitle(String title, Pageable pageable) {
        try {
            log.info("----- getAllNotesByTitle : {}", title);
            if (StringUtils.isBlank(title)) {
                return noteRepository.findAll(pageable);
            } else {
                return noteRepository.findByTitleIgnoreCase(title, pageable);
            }
        } catch (Exception e) {
            log.error("Error getAllNotesByTitle : {}", e.getMessage());
            throw e;
        }
    }

}
