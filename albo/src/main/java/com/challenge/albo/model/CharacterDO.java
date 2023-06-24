package com.challenge.albo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity(name = "personaje")
public class CharacterDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_character")
    private Long id;
    private String name;
    @Column(name = "dt_created")
    private LocalDateTime created;
    @Column(name = "dt_last_sync")
    private LocalDateTime lastSync;


    public CharacterDO() {
    }

    public CharacterDO(String name) {
        this.name = name;
        this.created = LocalDateTime.now();
        this.lastSync = LocalDateTime.now();
    }
}
