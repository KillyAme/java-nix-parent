package com.alevel.java.nix.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("income")
public class Income extends Operation {

    @ManyToMany
    @JoinTable(
            name = "income_categories_of_operations",
            joinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private final Set<IncomeCategory> categories = new HashSet<>();

    public Income(Account account, Long amount) {
        super(account, amount);
    }

    public Set<IncomeCategory> getCategories() {
        return categories;
    }
}
