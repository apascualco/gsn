package com.apascualco.user.data;

import com.apascualco.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByUser(final String user);

}
