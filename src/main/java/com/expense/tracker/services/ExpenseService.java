package com.expense.tracker.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.expense.tracker.models.Expense;
import com.expense.tracker.repositories.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses(String category,LocalDate startDate, LocalDate endDate,int page,int size){

        Pageable pageable=PageRequest.of(page, size);
        //Apply filters and return the result
        if(category!=null && startDate!=null && endDate!=null){
            return expenseRepository.findByCategoryAndDateBetween(category, startDate, endDate, pageable).getContent();
        }
        else if(category !=null){
            return expenseRepository.findByCategory(category, pageable).getContent();
        }
        else if(startDate!=null && endDate!=null){
            return expenseRepository.findByDateBetween(startDate, endDate, pageable).getContent();
        }

        // If no filter is provided
        return expenseRepository.findAll(pageable).getContent();
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
