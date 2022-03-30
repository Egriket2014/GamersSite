//package com.gamerssite.gamerssite.config;
//
//import com.gamerssite.gamerssite.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class UserDetailService implements UserDetailsService {
//
//    private final UserRepositoryImpl userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = userRepository.findByLogin(login).orElseThrow(() ->
//                new UsernameNotFoundException("User " + login + " does`t not exist"));
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        user.getRoleList().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                grantedAuthorities);
//    }
//
//
//}
