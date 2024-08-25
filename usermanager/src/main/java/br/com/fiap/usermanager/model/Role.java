package br.com.fiap.usermanager.model;

import br.com.fiap.usermanager.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_ROLES")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private RoleType roleName;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
