package br.com.fiap.usermanager.repository;

import br.com.fiap.usermanager.model.User;
import br.com.fiap.usermanager.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserLogin, UUID> {

    UserDetails findByLogin(String login);

}
