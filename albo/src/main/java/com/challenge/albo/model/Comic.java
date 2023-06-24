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

    public Comic() {
    }

    public Comic(String name) {
        this.name = name;
    }
}
