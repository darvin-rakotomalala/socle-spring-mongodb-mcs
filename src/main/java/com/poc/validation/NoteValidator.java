package com.poc.validation;

import com.poc.exception.ErrorsEnum;
import com.poc.model.dto.NoteDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NoteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NoteDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NoteDTO noteDTO = (NoteDTO) target;
        if (StringUtils.isEmpty(noteDTO.getTitle())) {
            errors.rejectValue("title", "title.value.empty", ErrorsEnum.ERR_MCS_NOTE_TITLE_EMPTY.getErrorMessage());
        }
    }

}
