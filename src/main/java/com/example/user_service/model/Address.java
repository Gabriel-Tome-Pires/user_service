package com.example.user_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @OneToMany(mappedBy = "address")
    @JsonIgnore
    List<User> users;

    public Address(String state, String city, String street) {
        this.state = state;
        this.city = city;
        this.street = street;
    }
}
