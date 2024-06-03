package com.projeto.app_financeiro.expense.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.app_financeiro.expense.entities.ExpenseEntity;
import com.projeto.app_financeiro.expense.enums.ExpenseEnum;
import com.projeto.app_financeiro.expense.repositories.ExpenseRepository;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;


    public ExpenseEntity addExpense(ExpenseEntity expense) {
        return expenseRepository.save(expense);
    }

    public List<ExpenseEntity> getExpensesByUserId(UUID userId) {
        List<ExpenseEntity> expenses = expenseRepository.findByUserId(userId);
        if (expenses.isEmpty()) {
            throw new RuntimeException("Expenses not found for user id: " + userId);
        }
        return expenses;
    }

    public ExpenseEntity updateExpense(UUID id, Double amount, ExpenseEnum type) {
        ExpenseEntity existingExpense = expenseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        // Update only the fields that are allowed to be changed
        existingExpense.setAmount(amount);
        existingExpense.setType(type);

        return expenseRepository.save(existingExpense);
    }

    public void delete(UUID id){
        expenseRepository.deleteById(id);
    }
}
