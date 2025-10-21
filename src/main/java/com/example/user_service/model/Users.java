package com.example.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    @Timestamp
    @NotNull
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Users(Address address, LocalDateTime createdAt, String email, String name) {
        this.address = address;
        this.createdAt = createdAt;
        this.email = email;
        this.name = name;
    }
}
