package com.example.przychodnia.service;

import com.example.przychodnia.entity.Message;
import com.example.przychodnia.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findById(String id) {
        return messageRepository.findById(Long.parseLong(id)).orElse(null);
    }
}
