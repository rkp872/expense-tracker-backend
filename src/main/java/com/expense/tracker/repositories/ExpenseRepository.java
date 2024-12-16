package com.expense.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.tracker.models.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
