package com.challenge.albo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "creator")
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_creator")
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "creator_type")
    private CreatorType type;

    @ManyToMany(mappedBy = "creators")
    Set<CharacterDO> characters;

    public Creator() {
    }

    public Creator(String name, CreatorType type) {
        this.name = name;
        this.type = type;
    }

    public Creator(String name, String type) {
        this.name = name;
        this.type = CreatorType.fromString(type);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Creator) {
            Creator other = (Creator) obj;
            return this.name.equals(other.getName()) && this.type.equals(other.getType());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final long prime = 31L;
        long result = 1L;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return (int) result;
    }
}
