package com.example.przychodnia.repository;

import com.example.przychodnia.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
