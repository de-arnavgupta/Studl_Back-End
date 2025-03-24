package de.arnav.studl.security.service;

import de.arnav.studl.exception.UserNotFoundException;
import de.arnav.studl.model.User;
import de.arnav.studl.repository.UserJpaRepository;
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
        User user = userJpaRepository.findByUserEmail(email).orElseThrow(() -> new UserNotFoundException());

        return new UserPrincipal(user);
    }
}
