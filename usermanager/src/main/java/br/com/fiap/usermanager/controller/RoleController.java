package br.com.fiap.usermanager.controller;

import br.com.fiap.usermanager.enums.RoleType;
import br.com.fiap.usermanager.model.Role;
import br.com.fiap.usermanager.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/roles")
    public ResponseEntity<List<RoleType>> findAll() {
        List<Role> roles = roleRepository.findAll();
        return Optional.of(roles)
                .map(r -> r.stream().map(Role::getRoleName).toList())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
