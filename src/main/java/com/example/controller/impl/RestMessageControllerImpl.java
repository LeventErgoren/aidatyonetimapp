package com.example.controller.impl;

import com.example.controller.IRestMessageController;
import com.example.dto.MessageRequest;
import com.example.entity.Message;
import com.example.service.IMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class RestMessageControllerImpl implements IRestMessageController {

    @Autowired
    IMessageService messageService;

    @PostMapping("/send")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void sendMessage(@Valid @RequestBody MessageRequest request) {
        messageService.sendMessage(request);
    }

    @GetMapping("/my-messages")
    @Override
    public List<Message> getMyMessages() {
        return messageService.getMyMessages();
    }
}
