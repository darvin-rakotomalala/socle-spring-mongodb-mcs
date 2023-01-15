package com.poc.service.application;

import com.poc.exception.ErrorsEnum;
import com.poc.exception.FunctionalException;
import com.poc.model.dto.TutorialDTO;
import com.poc.mapper.TutorialMapper;
import com.poc.service.business.TutorialCUDSM;
import com.poc.service.business.TutorialRSM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TutorialCUDSAImpl implements TutorialCUDSA {

    private final TutorialCUDSM tutorialCUDSM;
    private final TutorialRSM tutorialRSM;
    private final TutorialMapper tutorialMapper;

    @Override
    public TutorialDTO createTutorial(TutorialDTO tutorialDTO) {
        if (tutorialDTO == null) {
            throw new FunctionalException(ErrorsEnum.ERR_MCS_TUTORIAL_OBJECT_EMPTY.getErrorMessage());
        }
        tutorialDTO.setId(UUID.randomUUID());
        tutorialDTO.setCreatedAt(Instant.now());
        tutorialDTO.setUpdatedAt(Instant.now());
        return tutorialMapper.toDTO(tutorialCUDSM.createOrUpdateTutorial(tutorialMapper.toDO(tutorialDTO)));
    }

    @Override
    public List<TutorialDTO> saveAllTutorials(List<TutorialDTO> tutorials) {
        List<TutorialDTO> newTutorials = new ArrayList<>();
        tutorials.forEach(tutorial -> {
            tutorial.setId(UUID.randomUUID());
            tutorial.setCreatedAt(Instant.now());
            tutorial.setUpdatedAt(Instant.now());
            newTutorials.add(tutorial);
        });
        return tutorialMapper.toDTO(tutorialCUDSM.saveAllTutorials(tutorialMapper.toDO(newTutorials)));
    }

    @Override
    public TutorialDTO updateTutorial(TutorialDTO tutorialDTO) {
        if (tutorialDTO == null || tutorialDTO.getId() == null) {
            throw new FunctionalException(ErrorsEnum.ERR_MCS_TUTORIAL_OBJECT_EMPTY.getErrorMessage());
        }
        TutorialDTO tutorialFound = tutorialMapper.toDTO(tutorialRSM.getTutorialById(tutorialDTO.getId()));
        tutorialFound.setTitle(tutorialDTO.getTitle());
        tutorialFound.setDescription(tutorialDTO.getDescription());
        tutorialFound.setLevel(tutorialDTO.getLevel());
        tutorialFound.setPublished(tutorialDTO.isPublished());
        tutorialFound.setUpdatedAt(Instant.now());
        return tutorialMapper.toDTO(tutorialCUDSM.createOrUpdateTutorial(tutorialMapper.toDO(tutorialFound)));
    }

    @Override
    public void deleteTutorialById(UUID id) {
        tutorialCUDSM.deleteTutorialById(id);
    }
}
