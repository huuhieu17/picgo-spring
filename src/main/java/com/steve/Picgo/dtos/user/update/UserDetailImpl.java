package com.steve.Picgo.dtos.user.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.steve.Picgo.entites.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)

public class UserDetailImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    Long id;

    String username;

    @JsonIgnore
    String password;

    boolean enabled;

    Collection<? extends GrantedAuthority> authorities;

    public static UserDetailImpl build(UserEntity user) {
        List<GrantedAuthority> authorities = Collections.emptyList();

        return new UserDetailImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // nếu có role thì add vào đây
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
