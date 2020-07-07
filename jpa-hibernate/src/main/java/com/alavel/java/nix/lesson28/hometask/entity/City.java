package com.alavel.java.nix.lesson28.hometask.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "from")
    private List<Connection> out = new ArrayList<>();

    @OneToMany(mappedBy = "to")
    private List<Connection> in =new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Connection> getOut() {
        return out;
    }

    public void setOut(List<Connection> out) {
        this.out = out;
    }

    public List<Connection> getIn() {
        return in;
    }

    public void setIn(List<Connection> in) {
        this.in = in;
    }


}
