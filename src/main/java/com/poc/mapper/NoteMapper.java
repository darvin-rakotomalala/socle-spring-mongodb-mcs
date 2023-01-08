package com.poc.mapper;

import com.poc.model.domain.Note;
import com.poc.model.dto.NoteDTO;
import org.mapstruct.Mapper;

@Mapper
public interface NoteMapper extends DtoMapper<NoteDTO, Note> {

}
