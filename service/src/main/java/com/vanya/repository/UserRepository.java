package com.vanya.repository;

import com.vanya.entity.Role;
import com.vanya.entity.User;
import jakarta.persistence.EntityManager;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.core.types.dsl.EntityPathBase;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vanya.entity.QUser.user;

@Repository
public class UserRepository extends RepositoryBase<Integer,User> {

    @PersistenceContext
    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        super(User.class);
        this.entityManager = entityManager;
    }

    public List<User> findAllByUserName(String userName) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(user.username.eq(userName))
                .fetch();
    }

    public List<User> findAllByRole(Role role) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(user.role.eq(role))
                .fetch();
    }
}
