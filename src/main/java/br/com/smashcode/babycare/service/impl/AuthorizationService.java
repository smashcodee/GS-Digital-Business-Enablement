package br.com.smashcode.babycare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.smashcode.babycare.repository.IUserAccountRepository;

public class AuthorizationService implements UserDetailsService {

    @Autowired
    private IUserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var opt = userAccountRepository.findByEmail(email);
        if(!opt.isPresent()) { 
            throw new UsernameNotFoundException("conta inexistente.");
        }
        return opt.get();
    }
    
}
