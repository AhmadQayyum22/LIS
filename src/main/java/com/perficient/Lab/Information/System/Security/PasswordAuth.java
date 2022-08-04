package com.perficient.Lab.Information.System.Security;

import com.perficient.Lab.Information.System.Controller.UsersController;
import com.perficient.Lab.Information.System.Entity.Roles;
import com.perficient.Lab.Information.System.Entity.Users;
import com.perficient.Lab.Information.System.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PasswordAuth implements AuthenticationProvider{
    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private UsersController userCont;

    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String userName = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Users user = userRepo.findByuserName(userName);
        if(null != user && user.getUserId() > 0 && pwd.equals(user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user.getUserName(), pwd, getGrantedAuthorities(user.getRoles()));
        }
        else{
            throw new BadCredentialsException("Invalid credentials!");
        }
    }


    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ roles.getRoleName()));
        return grantedAuthorities;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
