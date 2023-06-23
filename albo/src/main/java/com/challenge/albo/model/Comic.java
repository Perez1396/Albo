package com.challenge.albo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "comic")
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_comic")
    private Long id;
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "character_comic", joinColumns = @JoinColumn(name = "pk_comic"), inverseJoinColumns = @JoinColumn(name = "pk_character"))
    Set<CharacterDO> characters;

    public Comic() {
    }

    public Comic(String name) {
        this.name = name;
    }
}
