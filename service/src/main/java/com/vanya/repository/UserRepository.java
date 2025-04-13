package com.vanya.repository;

import com.vanya.entity.Role;
import com.vanya.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.vanya.entity.QUser.user;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, QuerydslPredicateExecutor<User> {
    @Override
    @EntityGraph(value = "UserWithRole")
    List<User> findAll();

    @EntityGraph(value = "UserWithRole")  // Подгружаем связанные сущности
    Optional<User> findByUsername(String username);

    @EntityGraph(value = "UserWithRole")
    Optional<User> findById(Integer id);

    boolean existsByUsername(String username);
}
