package com.projeto.app_financeiro.income.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.app_financeiro.income.entities.IncomeEntity;

public interface IncomeRepository extends JpaRepository<IncomeEntity, UUID>{
    List<IncomeEntity> findByUserId(UUID userId);
}
