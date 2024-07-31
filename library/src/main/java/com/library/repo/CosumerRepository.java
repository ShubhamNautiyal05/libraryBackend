package com.library.repo;

import com.library.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CosumerRepository extends JpaRepository<Consumer, Long> {


    @Query("select c from Consumer c where c.userName = ?1")
    Optional<Consumer> findByUserName(String userName);
}
