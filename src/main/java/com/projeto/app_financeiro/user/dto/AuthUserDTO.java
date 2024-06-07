package com.projeto.app_financeiro.user.dto;

import java.util.UUID;

public record AuthUserDTO(String login, UUID password) {
    
}
