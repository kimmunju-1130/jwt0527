package com.example.jwt0527.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;
    @Column
    private String pw;
    private String name;
    private String email;
}
