package com.alavel.java.nix.lesson28.hometask.entity;

import javax.persistence.*;
@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problemId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(name = "cost")
    private Long minCost;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Long getMinCost() {
        return minCost;
    }

    public void setMinCost(Long minCost) {
        this.minCost = minCost;
    }


}
