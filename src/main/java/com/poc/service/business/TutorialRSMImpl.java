package com.poc.service.business;

import com.poc.exception.ErrorsEnum;
import com.poc.exception.FunctionalException;
import com.poc.model.domain.Tutorial;
import com.poc.repository.TutorialRepository;
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
public class TutorialRSMImpl implements TutorialRSM {

    private final TutorialRepository tutorialRepository;

    @Override
    public Tutorial getTutorialById(UUID id) {
        try {
            log.info("----- getTutorialById : {}", id);
            Optional<Tutorial> optionalTutorial = tutorialRepository.findById(id);
            if (optionalTutorial.isEmpty()) {
                throw new FunctionalException(ErrorsEnum.ERR_MCS_TUTORIAL_NOT_FOUND.getErrorMessage());
            }
            return optionalTutorial.get();
        } catch (Exception e) {
            log.error("Error getTutorialById : {} {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Page<Tutorial> getAllTutorialsByTitle(String title, Pageable pageable) {
        try {
            log.info("----- getAllTutorialsByTitle : {}", title);
            if (StringUtils.isBlank(title)) {
                return tutorialRepository.findAll(pageable);
            } else {
                return tutorialRepository.findByTitleIgnoreCase(title, pageable);
            }
        } catch (Exception e) {
            log.error("Error getAllTutorialsByTitle : {} {}", e.getMessage(), e);
            throw e;
        }
    }

}
