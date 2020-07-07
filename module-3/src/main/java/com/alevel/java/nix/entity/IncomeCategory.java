package com.alevel.java.nix.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "income_categories")
public class IncomeCategory extends OperationCategory {

    @ManyToMany(mappedBy = "categories")
    private final List<Income> operations = new ArrayList<>();

    public List<Income> getOperations() {
        return operations;
    }
}
