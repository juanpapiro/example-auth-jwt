package br.com.fiap.usermanager.dto;

import br.com.fiap.usermanager.enums.RoleType;
import br.com.fiap.usermanager.enums.UserRole;

import java.util.List;

public record UserRecord(String login, String password, List<RoleType> roles) {
}