package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//Clase 2
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repo.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not exists", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();


        switch (user.getRol()) {
            case "ADMIN":
                roles.add(new SimpleGrantedAuthority("ADMIN"));
                roles.add(new SimpleGrantedAuthority("USER_READ"));
                roles.add(new SimpleGrantedAuthority("USER_WRITE"));
                roles.add(new SimpleGrantedAuthority("USER_DELETE"));
                break;
            case "MANAGER":
                roles.add(new SimpleGrantedAuthority("USER_READ"));
                roles.add(new SimpleGrantedAuthority("PACIENTE_READ"));
                break;
            case "USER":
                roles.add(new SimpleGrantedAuthority("PACIENTE_READ"));
                break;
        }

        roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRol()));
        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(),
                true, true, true, roles);

        return ud;
    }
}
