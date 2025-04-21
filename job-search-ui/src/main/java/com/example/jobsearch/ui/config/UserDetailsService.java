package com.example.jobsearch.ui.config;

import com.example.jobsearch.dal.entity.User;
import com.example.jobsearch.dal.entity.UsersStatus;
import com.example.jobsearch.dal.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user.getStatus() == UsersStatus.BLOCKED) {
            throw new DisabledException("Аккаунт заблокирован!");
        }
        else
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().toString())
            );
    }
}