package com.projeto.app_financeiro.expense.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.app_financeiro.expense.entities.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, UUID>{
    List<ExpenseEntity> findByUserId(UUID userId);
}
