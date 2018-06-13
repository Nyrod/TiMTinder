package com.tim.tinder.repositories;

import com.tim.tinder.entities.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Token findByToken(String token);

    Token findByIdUser(Long idUser);
}
