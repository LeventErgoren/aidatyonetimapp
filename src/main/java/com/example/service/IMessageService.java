package com.example.service;

import com.example.dto.MessageRequest;
import com.example.entity.Message;
import java.util.List;

public interface IMessageService {
    void sendMessage(MessageRequest request); // Admin
    List<Message> getMyMessages();
}
