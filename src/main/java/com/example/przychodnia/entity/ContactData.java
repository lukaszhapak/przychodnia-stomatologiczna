package com.example.przychodnia.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class ContactData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;
    private String email;
    private String address;
    private LocalTime open;
    private LocalTime close;
}
