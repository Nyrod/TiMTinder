package com.tim.tinder.repositories;

import com.tim.tinder.entities.Message;
import org.springframework.data.repository.CrudRepository;


public interface MessageRepository extends CrudRepository<Message, Long> {
}
