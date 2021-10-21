package com.mycompany;

import com.mycompany.product.Product;
import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired private UserRepository repo;
    @Autowired private TestEntityManager entityManager;

    // add new object to datasource users
    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("mail@mail.com");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("test123");
        user.setPassword(encodedPassword);
        user.setFirstName("Jan");
        user.setLastName("Kowalski");

        User savedUser = repo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());

        Assertions.assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

    // display in console all users attributes from datasource products
    @Test
    public void testListAll(){
        List<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user: users){
            System.out.println(user.toString());
        }
    }

    // check if there is a user with the specific email in datasource users
    @Test
    public void testFindUserByEmail(){
        String email = "testowy@test.com";
        User user = repo.findByEmail(email);

        Assertions.assertThat(user).isNotNull();
    }

    // check if there is a user with the specific id in datasource users and display in console
    @Test
    public void testGetId(){
        long userId = 1;
        Optional<User> optionalUser = repo.findById(userId);

        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    // modify first name of specific object in datasource users
    @Test
    public void testUpdate(){
        long userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setFirstName("Kamil");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getFirstName()).isEqualTo("Kamil");

    }

    // delete specific object from datasource users
    @Test
    public void testDelete(){
        long userId = 5;
        User user = repo.findById(userId).get();
        repo.delete(user);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }

}
