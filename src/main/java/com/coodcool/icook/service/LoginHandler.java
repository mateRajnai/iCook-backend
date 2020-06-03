package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.User;
import com.coodcool.icook.model.UserCredentials;
import com.coodcool.icook.security.JwtTokenServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LoginHandler {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenServices jwtTokenServices;
    private final UserRepository userRepository;

    public Map<Object, Object> handleLogin(UserCredentials data) {
        String username = data.getUsername();
        // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, data.getPassword())
        );
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Optional<User> user = userRepository.findByUserName(username);
        String id = user.map(value -> value.getId().toString()).orElse(null);

        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("roles", roles);
        model.put("id", id);

        return model;
    }

    public Cookie generateCookie(Map<Object, Object> userData) {
        String token = jwtTokenServices.createToken(userData.get("username").toString(),
                (List<String>) userData.get("roles"),
                userData.get("id").toString());
        return jwtTokenServices.createJwtCookie(token);
    }

}
