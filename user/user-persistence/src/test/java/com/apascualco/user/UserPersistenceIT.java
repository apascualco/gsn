package com.apascualco.user;

import com.apascualco.user.config.JPAConfig;
import com.apascualco.user.data.UserRepository;
import com.apascualco.user.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JPAConfig.class)
public class UserPersistenceIT {

    @Autowired private UserRepository userRepository;

    @Test
    public void user_save_test() {
        final UserEntity userEntity = userRepository.save(
                UserEntity.builder()
                        .user("admin")
                        .password("admin")
                        .build()
        );
        assertNotNull(userEntity, "UserEntity shouldn't be null");
        assertNotNull(userEntity.getUuid(), "Id generated shouldn't be null");
        assertEquals("admin", userEntity.getUser());
        assertEquals("admin", userEntity.getPassword());
    }

    @Test
    public void user_save_with_password_null() {
        final DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(
                        UserEntity.builder()
                                .user("admin")
                                .build()
                )
        );
        assertTrue(Objects.requireNonNull(dataIntegrityViolationException.getMessage()).contains("not-null property references a null or transient value : com.apascualco.user.model.UserEntity.password"));
    }

    @Test
    public void user_save_with_user_null() {
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(
                UserEntity.builder()
                        .password("admin")
                        .build()
                )
        );
        assertTrue(Objects.requireNonNull(dataIntegrityViolationException.getMessage()).contains("not-null property references a null or transient value : com.apascualco.user.model.UserEntity.user"));
    }

}
