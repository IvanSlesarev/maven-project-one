package com.vanya.repository;

import com.vanya.entity.Role;
import com.vanya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vanya.entity.QUser.user;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, QuerydslPredicateExecutor<User> {
    List<User> findAllByUsername(String username);
    List<User> findAllByRole(Role role);
}
