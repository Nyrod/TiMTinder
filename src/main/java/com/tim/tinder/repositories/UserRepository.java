package com.tim.tinder.repositories;

import com.tim.tinder.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
}
