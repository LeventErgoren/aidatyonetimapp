package com.example.controller;

import com.example.dto.MessageRequest;
import com.example.entity.Message;
import java.util.List;

public interface IRestMessageController {
    void sendMessage(MessageRequest request);
    List<Message> getMyMessages();
}
