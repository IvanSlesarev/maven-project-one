package com.vanya.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.vanya.entity.QUser;
import com.vanya.entity.Role;
import com.vanya.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static com.vanya.entity.QUser.user;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

   public List<User> findAllByUserName(Session session, String userName) {
        return new JPAQuery<User>(session)
                .select(QUser.user)
                .from(QUser.user)
                .where(user.username.eq(userName))
                .fetch();
    }

    List<User> findAllByRole(Session session, Role role) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.role.eq(role))
                .fetch();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
