package com.signupandlogin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.signupandlogin.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);

}
