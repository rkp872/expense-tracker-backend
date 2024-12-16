package com.expense.tracker.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.tracker.models.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Query to filter by category and date range
    Page<Expense> findByCategoryAndDateBetween(String category,LocalDate startDate,LocalDate endDate, Pageable pageable);

     // Query to filter by category
     Page<Expense> findByCategory(String category, Pageable pageable);

     // Query to filter by date range
     Page<Expense> findByDateBetween(LocalDate startDate,LocalDate endDate, Pageable pageable);

     // Get all expenses with pagenation
     Page<Expense> findAll(Pageable pageable);
}
