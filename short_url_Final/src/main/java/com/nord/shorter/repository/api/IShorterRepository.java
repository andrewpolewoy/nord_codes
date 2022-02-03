package com.nord.shorter.repository.api;

import com.nord.shorter.model.Shorter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShorterRepository extends CrudRepository<Shorter, Long> {
    Shorter findByHash(String hash);
    List<Shorter> findAll();
    @Query("SELECT u.shorters FROM User u WHERE u.id=:id")
    List<Shorter> findAllByUser(@Param("id") Long id);
}
