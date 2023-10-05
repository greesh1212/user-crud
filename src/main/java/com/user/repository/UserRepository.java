package com.user.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByFirstNameAndLastName(String firstName, String lastName);

}