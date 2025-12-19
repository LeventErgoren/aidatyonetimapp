package com.example.service.impl;

import com.example.dto.MessageRequest;
import com.example.entity.Kullanici;
import com.example.entity.Message;
import com.example.exception.BusinessException;
import com.example.repository.KullaniciRepository;
import com.example.repository.MessageRepository;
import com.example.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    KullaniciRepository userRepository;

    @Override
    @Transactional
    public void sendMessage(MessageRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Kullanici sender = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("Admin bulunamadı"));

        Message msg = new Message();
        msg.setTitle(request.getTitle());
        msg.setContent(request.getContent());
        msg.setTargetAudience(request.getTargetAudience());
        msg.setSentDate(LocalDateTime.now());
        msg.setSender(sender);

        messageRepository.save(msg);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getMyMessages() {
        // Basitlik için tüm mesajları döndürüyoruz.
        // İstenirse burada "Sadece benim bloğuma atılan mesajlar" filtresi yapılabilir.
        return messageRepository.findAll();
    }
}
