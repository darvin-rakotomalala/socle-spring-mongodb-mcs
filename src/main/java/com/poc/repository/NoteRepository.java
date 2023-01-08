package com.poc.repository;

import com.poc.model.domain.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.UUID;

public interface NoteRepository extends MongoRepository<Note, UUID> {
    @Query("{ 'title' : { $regex: ?0 } }")
    Page<Note> findByTitleIgnoreCase(String title, Pageable pageable);
}
