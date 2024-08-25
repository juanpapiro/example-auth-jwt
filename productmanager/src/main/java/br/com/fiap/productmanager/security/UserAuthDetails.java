package br.com.fiap.productmanager.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthDetails implements UserDetails {

    private String login;
    private Collection<? extends GrantedAuthority> authorities;

    public UserAuthDetails(String login, String roles) {
        this.login = login;
        this.authorities = Optional.ofNullable(roles)
                .map(r -> r.split(","))
                .map(Stream::of)
                .map(s -> s.map(SimpleGrantedAuthority::new).toList())
                .orElse(null);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
