package de.arnav.studl.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    public MyUserDetailService(UserJpaRepository userJpaRepository){
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userJpaRepository.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException(email);
        }

        return new UserPrincipal(user);
    }
}
