package com.projeto.app_financeiro.expense.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.app_financeiro.expense.dto.ExpenseDTO;
import com.projeto.app_financeiro.expense.entities.ExpenseEntity;
import com.projeto.app_financeiro.expense.services.ExpenseService;
import com.projeto.app_financeiro.user.entities.UserEntity;
import com.projeto.app_financeiro.user.services.UserService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ExpenseDTO addExpense(@RequestBody ExpenseDTO expenseDTO) {
        // Fetch the complete user entity from the user service using the ID
        UUID userId = expenseDTO.getUserId();
        Optional<UserEntity> userOptional = userService.findUserById(userId);
        
        // If the user is not found, throw an exception or handle it accordingly
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        
        UserEntity user = userOptional.get();
        
        // Create the expense entity with the fetched user object
        ExpenseEntity expense = new ExpenseEntity();
        expense.setAmount(expenseDTO.getAmount());
        expense.setType(expenseDTO.getType());
        expense.setCreatedAt(expenseDTO.getDate());
        expense.setUser(user);
        
        // Proceed with saving the expense entity and returning the DTO
        ExpenseEntity savedExpense = expenseService.addExpense(expense);
        expenseDTO.setId(savedExpense.getId());
        return expenseDTO;
    }
    

    @GetMapping("/user/{userId}")
    public List<ExpenseDTO> getExpensesByUser(@PathVariable UUID userId) {
        List<ExpenseEntity> expenses = expenseService.getExpensesByUserId(userId);
        List<ExpenseDTO> expenseDTOs = expenses.stream().map(expense -> {
            ExpenseDTO dto = new ExpenseDTO();
            dto.setId(expense.getId());
            dto.setAmount(expense.getAmount());
            dto.setType(expense.getType());
            dto.setDate(expense.getCreatedAt());
            dto.setUserId(expense.getUser().getId());
            return dto;
        }).collect(Collectors.toList());

        return expenseDTOs;
    }

}

