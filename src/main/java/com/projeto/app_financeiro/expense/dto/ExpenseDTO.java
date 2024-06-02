package com.projeto.app_financeiro.expense.dto;

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
public class ExpenseDTO {
    private UUID id;
    private Double amount;
    private String type;
    private LocalDateTime date;
    private Long userId;
}
