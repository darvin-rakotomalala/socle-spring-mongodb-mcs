package com.poc.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class NoteDTO {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private UUID id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Instant createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Instant updatedAt;
}
