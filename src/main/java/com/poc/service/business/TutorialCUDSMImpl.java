package com.poc.service.business;

import com.poc.model.domain.Tutorial;
import com.poc.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TutorialCUDSMImpl implements TutorialCUDSM {

    private final TutorialRepository tutorialRepository;
    private final TutorialRSM tutorialRSM;

    @Override
    public Tutorial createOrUpdateTutorial(Tutorial tutorial) {
        try {
            log.info("----- createOrUpdateTutorial");
            return tutorialRepository.save(tutorial);
        } catch (Exception e) {
            log.error("Error createOrUpdateTutorial : {} {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Tutorial> saveAllTutorials(List<Tutorial> tutorials) {
        try {
            log.info("----- saveAllTutorials");
            return tutorialRepository.saveAll(tutorials);
        } catch (Exception e) {
            log.error("Error saveAllTutorials : {} {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteTutorialById(UUID id) {
        try {
            log.info("----- deleteTutorialById : {}", id);
            Tutorial tutorialFound = tutorialRSM.getTutorialById(id);
            tutorialRepository.deleteById(tutorialFound.getId());
        } catch (Exception e) {
            log.error("Error deleteTutorialById : {} {}", e.getMessage(), e);
            throw e;
        }
    }

}
