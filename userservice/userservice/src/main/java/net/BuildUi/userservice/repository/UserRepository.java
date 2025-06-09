package net.BuildUi.userservice.repository;

import net.BuildUi.userservice.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {
    Optional<UserInfo> findByUserId(String userId);
}
