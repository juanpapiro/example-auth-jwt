package br.com.fiap.usermanager.controller;


import br.com.fiap.usermanager.dto.AuthUserRecord;
import br.com.fiap.usermanager.dto.UserRecord;
import br.com.fiap.usermanager.model.Role;
import br.com.fiap.usermanager.model.User;
import br.com.fiap.usermanager.model.UserLogin;
import br.com.fiap.usermanager.repository.RoleRepository;
import br.com.fiap.usermanager.repository.UserRepository;
import br.com.fiap.usermanager.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auth")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRecord data){
        if(this.userRepository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        List<Role> roles = roleRepository.findAll();
        roles = roles.stream()
                .filter(role -> data.roles().stream().anyMatch(r -> r.equals(role.getRoleName())))
                .toList();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserLogin newUser = new UserLogin(data.login(), encryptedPassword, roles);
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthUserRecord data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserLogin) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

}
