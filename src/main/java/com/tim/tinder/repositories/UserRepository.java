package com.tim.tinder.repositories;

import com.tim.tinder.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends CrudRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("update User u set u.lon = :lon, u.lat = :lat where u.idUser = :idUser")
    void updateLocalization(@Param("idUser")Long idUser, @Param("lon")Double lon, @Param("lat")Double lat);

    User findByLogin(String login);
}
