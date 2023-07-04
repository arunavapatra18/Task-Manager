package dev.arunava.taskmanager.Task.Manager.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public record Task (
    @Id
    @GeneratedValue
    Long id,
    String title,
    String description,
    Status status,
    Priority priority,
    LocalDateTime createDate,
    LocalDateTime updateDate
) {

}
