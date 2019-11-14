package com.example.przychodnia.repository;

import com.example.przychodnia.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select count(id) from Message m where opened = false")
    int newMessagesCount();
}
