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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "character_creator", joinColumns = @JoinColumn(name = "pk_character"), inverseJoinColumns = @JoinColumn(name = "pk_creator"))
    private Set<Creator> creators;

    @ManyToMany(mappedBy = "characters", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Comic> comics;

    public CharacterDO() {
    }

    public CharacterDO(String name) {
        this.name = name;
        this.created = LocalDateTime.now();
        this.lastSync = LocalDateTime.now();
    }
}
