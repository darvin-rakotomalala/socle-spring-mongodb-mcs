package com.poc.model.domain;

import com.poc.model.common.AuditModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notes")
public class Note extends AuditModel {
    @Id
    private UUID id;
    private String title;
    private String content;
}
