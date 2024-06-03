package com.projeto.app_financeiro.expense.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        UUID userId = expenseDTO.getUserId();
        Optional<UserEntity> userOptional = userService.findUserById(userId);
        
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        
        UserEntity user = userOptional.get();
        
        ExpenseEntity expense = new ExpenseEntity();
        expense.setAmount(expenseDTO.getAmount());
        expense.setType(expenseDTO.getType());
        expense.setCreatedAt(expenseDTO.getDate());
        expense.setUser(user);
        
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

    @PutMapping("/{id}")
    public ExpenseDTO updateExpense(@PathVariable UUID id, @RequestBody ExpenseDTO expenseDTO) {
        ExpenseEntity savedExpense = expenseService.updateExpense(id, expenseDTO.getAmount(), expenseDTO.getType());

        ExpenseDTO updatedDTO = new ExpenseDTO();
        updatedDTO.setId(savedExpense.getId());
        updatedDTO.setAmount(savedExpense.getAmount());
        updatedDTO.setType(savedExpense.getType());
        updatedDTO.setDate(savedExpense.getCreatedAt());
        updatedDTO.setUserId(savedExpense.getUser().getId());

        return updatedDTO;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        expenseService.delete(id);
    }
    

}

