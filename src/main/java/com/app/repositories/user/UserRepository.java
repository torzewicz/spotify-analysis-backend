package com.app.repositories.user;

import com.app.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query("select u.verified from User u where username = :username")
    boolean checkEmailVerificationByUsername(@Param("username") String username);

    @Query("select u.verificationCode from User u where email = :email")
    Integer checkVerificationCodeByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("update User u set u.verified = true where u.email like :email")
    void verifyUserByEmail(@Param("email") String email);
}