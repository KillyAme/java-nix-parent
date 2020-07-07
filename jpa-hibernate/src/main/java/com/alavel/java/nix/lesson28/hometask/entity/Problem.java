package com.alavel.java.nix.lesson28.hometask.entity;

import javax.persistence.*;
@Entity
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_id", nullable = false)
    private City from;

    @ManyToOne
    @JoinColumn(name = "to_id", nullable = false)
    private City to;

    @OneToOne(mappedBy = "problem" )
    private Solution solution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }


}
