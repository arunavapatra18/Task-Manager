package dev.arunava.taskmanager.Task.Manager.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

public record User (
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    UUID id,
    String fullName,
    String username,
    String email,
    String password
) {

}
