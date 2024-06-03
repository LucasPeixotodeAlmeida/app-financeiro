package com.projeto.app_financeiro.income.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.app_financeiro.income.entities.IncomeEntity;
import com.projeto.app_financeiro.income.repositories.IncomeRepository;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public IncomeEntity addIncome(IncomeEntity income) {
        return incomeRepository.save(income);
    }

    public List<IncomeEntity> getIncomesByUserId(UUID userId) {
        return incomeRepository.findByUserId(userId);
    }

    public IncomeEntity updateIncome(UUID id, Double amount) {
        IncomeEntity existingIncome = incomeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Income not found with id: " + id));

        existingIncome.setAmount(amount);

        return incomeRepository.save(existingIncome);
    }

    public void delete(UUID id){
        incomeRepository.deleteById(id);
    }
}
