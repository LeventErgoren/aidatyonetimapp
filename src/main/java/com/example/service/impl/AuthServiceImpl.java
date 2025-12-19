package com.example.service.impl;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.dto.RegisterRequest;
import com.example.entity.EvSakini;
import com.example.entity.Kullanici;
import com.example.enums.Role;
import com.example.exception.AuthFailException;
import com.example.exception.BusinessException;
import com.example.jwt.JwtService;
import com.example.repository.EvSakiniRepository;
import com.example.repository.KullaniciRepository;
import com.example.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EvSakiniRepository residentRepository;

    @Autowired
    KullaniciRepository userRepository;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Override
    @Transactional // Önemli: Ya hepsi kaydolur ya hiçbiri
    public AuthResponse register(RegisterRequest request) {

        // 1. Bu kullanıcı adı daha önce alınmış mı?
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BusinessException("Bu kullanıcı adı zaten kullanılıyor.");
        }

        // 2. Kullanıcı (Auth) nesnesini oluştur
        Kullanici user = new Kullanici();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Şifreyi şifrele!
        user.setRole(Role.SAKIN); // Varsayılan olarak SAKIN rolü veriyoruz

        // 3. Ev Sakini (Profil) nesnesini oluştur
        EvSakini resident = new EvSakini();
        resident.setAd(request.getAd());
        resident.setSoyad(request.getSoyad());
        resident.setTelefon(request.getTelefon());
        resident.setOwner(false); // Varsayılan: Kiracı (Admin sonra düzeltebilir)

        // 4. İlişkiyi Kur (User -> Resident bağlama)
        resident.setKullanici(user);

        // 5. Kaydet (CascadeType.ALL sayesinde user da residents tablosuna kaydolur)
        residentRepository.save(resident);

        // 6. Token Üret (Kullanıcı kayıt olunca direkt giriş yapmış sayılsın)
        String accessToken = jwtService.generateToken(user);

        return new AuthResponse(accessToken);
    }

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

