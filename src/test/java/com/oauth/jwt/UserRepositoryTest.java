package com.oauth.jwt;

import com.oauth.jwt.model.User;
import com.oauth.jwt.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void whenFindByEmailThenReturnUser() {
        // given
        User will = new User();
        will.setName("Willandher Goyo");
        will.setEmail("willandherg@gmail.com");
        will.setPassword("Will22");
        entityManager.persist(will);
        entityManager.flush();
        // when
        Optional<User> found = userRepository.findByEmail(will.getEmail());

        // then
        assertThat(
                found.get(),
                is(will)
        );
    }

}
