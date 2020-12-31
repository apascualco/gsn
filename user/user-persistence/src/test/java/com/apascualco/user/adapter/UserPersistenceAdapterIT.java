package com.apascualco.user.adapter;

import com.apascualco.user.config.JPAConfig;
import com.apascualco.user.domain.User;
import com.apascualco.user.exception.UserValidation;
import com.apascualco.user.model.UserEntity;
import com.apascualco.user.port.UserPersistence;
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
public class UserPersistenceAdapterIT {

    @Autowired private UserPersistence userPersistence;

    @Test
    public void save_user_by_adapter() {
        final User presistedUser = userPersistence.save(
                User.builder()
                        .user("pascual")
                        .password("admin")
                        .name("alberto")
                        .surname("pa")
                        .build()
        );
        assertNotNull(presistedUser);
        assertNotNull(presistedUser.getUuid());
        final User user = userPersistence.getUserByUser("pascual");
        assertNotNull(user);
        assertNotNull(user.getUuid());
    }

    @Test
    public void user_save_with_password_null() {
        final DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> userPersistence.save(
                User.builder()
                        .user("admin")
                        .build()
                )
        );
        assertTrue(Objects.requireNonNull(dataIntegrityViolationException.getMessage()).contains("not-null property references a null or transient value : com.apascualco.user.model.UserEntity.password"));
    }

    @Test
    public void user_save_with_user_null() {
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> userPersistence.save(
                User.builder()
                        .password("admin")
                        .build()
                )
        );
        assertTrue(Objects.requireNonNull(dataIntegrityViolationException.getMessage()).contains("not-null property references a null or transient value : com.apascualco.user.model.UserEntity.user"));
    }


}
