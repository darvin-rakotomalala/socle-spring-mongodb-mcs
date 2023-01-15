package com.poc.service.business;

import com.poc.model.domain.Tutorial;

import java.util.List;
import java.util.UUID;

public interface TutorialCUDSM {
    public Tutorial createOrUpdateTutorial(Tutorial note);
    public List<Tutorial> saveAllTutorials(List<Tutorial> notes);
    public void deleteTutorialById(UUID id);
}
