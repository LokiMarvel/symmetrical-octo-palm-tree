package org.wines.authentications.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users",schema = "public")
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,updatable = false,nullable = false)
    private String userid;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Id
    @Column(unique = true,updatable = false,nullable = false)
    private String email;

    @OneToMany(mappedBy = "email",fetch = FetchType.EAGER)
    private List<Authority> authorities;

    @Column
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> permissions = new LinkedList<>();
        if (this.authorities == null || this.authorities.isEmpty())
            return Collections.emptyList();
        authorities.forEach(x -> permissions.add(new SimpleGrantedAuthority(x.getUsers_role())));
        return permissions;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
