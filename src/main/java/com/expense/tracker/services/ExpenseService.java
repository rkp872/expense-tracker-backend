package com.expense.tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.models.Expense;
import com.expense.tracker.repositories.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id){
        return expenseRepository.findById(id);
    }

    public Expense addExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense expenseDetails){
        Expense expense=expenseRepository.findById(id).orElseThrow(()->new RuntimeException("Expense not found with id: "+id));

        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        expense.setDate(expenseDetails.getDate());
        expense.setDescription(expenseDetails.getDescription());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id){
        Expense expense=expenseRepository.findById(id).orElseThrow(()->new RuntimeException("Expense not found with id : "+id));

        expenseRepository.delete(expense);
    }
}
