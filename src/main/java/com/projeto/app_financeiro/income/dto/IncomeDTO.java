package com.projeto.app_financeiro.income.dto;

import java.time.LocalDateTime;
import java.util.UUID;


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
    private LocalDateTime date;
    private UUID userId;
}
