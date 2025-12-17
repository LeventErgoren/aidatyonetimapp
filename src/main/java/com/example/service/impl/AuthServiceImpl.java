package com.example.service.impl;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.exception.AuthFailException;
import com.example.jwt.JwtService;
import com.example.model.Kullanici;
import com.example.repository.KullaniciRepository;
import com.example.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    KullaniciRepository userRepository;

    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtService jwtService;

    @Override
    @Transactional(readOnly = true)
    public AuthResponse authenticate(AuthRequest request) {

        try {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(auth);

        } catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new AuthFailException("Kullanıcı adı veya şifre yanlış");
        }

        Kullanici kullanici = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new AuthFailException("Kullanıcı adı veya şifre yanlış")
                );

        String accessToken = jwtService.generateToken(kullanici);

        return new AuthResponse(accessToken);

    }
}

