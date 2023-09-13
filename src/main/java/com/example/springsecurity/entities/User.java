package com.example.springsecurity.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(unique=true)
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST , fetch=FetchType.EAGER)
    private List<Role> roleList;
    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       //boucler sur notre liste de roles ci-dessus
        //créer une liste contenant plusieurs SimpleGrantedAuthority
        //retourner cette liste de SimplegrantedAuthority
        Collection<GrantedAuthority> authorities= new ArrayList<>() ;

            for (Role role: this.roleList) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return this.roleList.stream().map(role-> new SimpleGrantedAuthority(role.getName())).toList();


    }


}
