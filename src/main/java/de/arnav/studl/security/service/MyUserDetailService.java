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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userJpaRepository.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipal(user);
    }
}
