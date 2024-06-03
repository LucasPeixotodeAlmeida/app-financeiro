package com.projeto.app_financeiro.income.controllers;

import java.util.List;
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

import com.projeto.app_financeiro.income.dto.IncomeDTO;
import com.projeto.app_financeiro.income.entities.IncomeEntity;
import com.projeto.app_financeiro.income.services.IncomeService;
import com.projeto.app_financeiro.user.entities.UserEntity;
import com.projeto.app_financeiro.user.services.UserService;



@RestController
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public IncomeDTO addIncome(@RequestBody IncomeDTO incomeDTO) {
        
        IncomeEntity income = new IncomeEntity();
        income.setAmount(incomeDTO.getAmount());
        income.setCreatedAt(incomeDTO.getDate());
        
        // Buscar o usuário do banco de dados
        UserEntity user = userService.findUserById(incomeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + incomeDTO.getUserId()));
        income.setUser(user);
    
        IncomeEntity savedIncome = incomeService.addIncome(income);
    
        incomeDTO.setId(savedIncome.getId());
        return incomeDTO;
    }
    

    @GetMapping("/user/{userId}")
    public List<IncomeDTO> getIncomesByUser(@PathVariable UUID userId) {
        List<IncomeEntity> incomes = incomeService.getIncomesByUserId(userId);
        List<IncomeDTO> incomeDTOs = incomes.stream().map(income -> {
            IncomeDTO dto = new IncomeDTO();
            dto.setId(income.getId());
            dto.setAmount(income.getAmount());
            dto.setDate(income.getCreatedAt());
            dto.setUserId(income.getUser().getId());
            return dto;
        }).collect(Collectors.toList());

        return incomeDTOs;
    }

    @PutMapping("{id}")
    public IncomeDTO updateIncome(@PathVariable UUID id, @RequestBody IncomeDTO incomeDTO){
        IncomeEntity savedExpense = incomeService.updateIncome(id, incomeDTO.getAmount());

        IncomeDTO updatedDTO = new IncomeDTO();
        updatedDTO.setId(savedExpense.getId());
        updatedDTO.setAmount(savedExpense.getAmount());
        updatedDTO.setDate(savedExpense.getCreatedAt());
        updatedDTO.setUserId(savedExpense.getUser().getId());

        return updatedDTO;
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id){
        incomeService.delete(id);
    }
}
