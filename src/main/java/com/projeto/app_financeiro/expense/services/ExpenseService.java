package com.projeto.app_financeiro.expense.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.app_financeiro.expense.entities.ExpenseEntity;
import com.projeto.app_financeiro.expense.repositories.ExpenseRepository;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseEntity addExpense(ExpenseEntity expense) {
        return expenseRepository.save(expense);
    }

    public List<ExpenseEntity> getExpensesByUserId(UUID userId){
        return expenseRepository.findByUserId(userId);
    }
}
