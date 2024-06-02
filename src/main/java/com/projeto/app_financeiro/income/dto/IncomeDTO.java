package com.projeto.app_financeiro.income.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDTO {
    
    private UUID id;
    private Double amount;
    @CreationTimestamp
    private LocalDateTime date;
    private UUID userId;
}
