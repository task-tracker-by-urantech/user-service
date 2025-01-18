package com.urantech.userservice.repository;

import com.urantech.userservice.model.entity.user.User;
import com.urantech.userservice.model.rest.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new com.urantech.userservice.model.rest.UserResponse(u.email, count(t)) " +
            "from User u left join u.tasks t " +
            "where u.enabled = true " +
            "group by u.email")
    List<UserResponse> findAllEnabledUsersWithTasksCount();
}
