package com.example.przychodnia.service;

import com.example.przychodnia.entity.Message;
import com.example.przychodnia.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findById(String id) {
        return messageRepository.findById(Long.parseLong(id)).orElse(null);
    }

    public int newMessagesCount() {
        return messageRepository.newMessagesCount();
    }
}
