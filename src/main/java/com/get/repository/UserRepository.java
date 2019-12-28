package com.get.repository;


import com.get.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update User set name=:name where email=:email")
    int setName(@Param("name") String name, @Param("email") String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User set introduction=:introduction where email=:email")
    int setIntroduction(@Param("introduction") String introduction, @Param("email") String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User set password=:password where email=:email")
    int setNewPassword(@Param("password") String password, @Param("email") String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User set outDate=:outDate, validateCode=:validateCode where email=:email")
    int setOutDateAndValidateCode(@Param("outDate") String outDate, @Param("validateCode") String validateCode, @Param("email") String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User set outDate=:outDate where email=:email")
    int setOutDate(@Param("outDate") String outDate, @Param("email") String email);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User set profilePicture=:profilePicture where id=:id")
    int setProfilePicture(@Param("profilePicture") String profilePicture, @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User set backgroundPicture=:backgroundPicture where id=:id")
    int setBackgroundPicture(@Param("backgroundPicture") String backgroundPicture, @Param("id") Long id);



}
