package com.poc.controller;

import com.poc.validation.NoteValidator;
import com.poc.model.dto.NoteDTO;
import com.poc.service.application.NoteCUDSA;
import com.poc.service.application.NoteRSA;
import com.poc.utils.HelpPage;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "notes")
public class NoteController {

    private final NoteCUDSA noteCUDSA;
    private final NoteRSA noteRSA;
    private final NoteValidator noteValidator;

    @InitBinder("noteDTO")
    protected void initNoteDTOBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(noteValidator);
    }

    @ApiOperation(value = "WS used to create note")
    @PostMapping
    public NoteDTO createNote(@RequestBody @Validated NoteDTO noteDTO) {
        return noteCUDSA.createNote(noteDTO);
    }

    @ApiOperation(value = "WS used to save list notes")
    @PostMapping("/saveAll")
    public List<NoteDTO> saveAllNotes(@RequestBody List<NoteDTO> notes) {
        return noteCUDSA.saveAllNotes(notes);
    }

    @ApiOperation(value = "WS used to update note")
    @PutMapping
    public NoteDTO updateNote(@RequestBody @Validated NoteDTO noteDTO) {
        return noteCUDSA.updateNote(noteDTO);
    }

    @ApiOperation(value = "WS used to get all users")
    @GetMapping
    public HelpPage<NoteDTO> getAllNotes(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "15", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return noteRSA.getAllNotesByTitle(title, pageable);
    }

    @ApiOperation(value = "WS used to get note by id")
    @GetMapping("/{id}")
    public NoteDTO getNoteById(@PathVariable("id") UUID id) {
        return noteRSA.getNoteById(id);
    }

    @ApiOperation(value = "WS used to delete note by id")
    @DeleteMapping("/{id}")
    public String deleteNoteById(@PathVariable("id") UUID id) {
        noteCUDSA.deleteNoteById(id);
        return "Note with id " + id + " deleted successfully !";
    }

}
