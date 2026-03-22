package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


   @Transactional
   @Modifying
    @Query(value = "drop table user" , nativeQuery = true)
    public void dropTableBySql();
}
