package com.mycompany.user;

import javax.persistence.*;

//creating table 'users' for database 'inventorydb'
@Entity
@Table(name = "users")
public class User {
    // insert the columns below in the table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // insert username's email, varchar(45), NOT NULL, UNIQUE Constraint
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    // insert password, varchar(64), NOT NULL
    @Column(nullable = false, length = 64)
    private String password;

    // insert first name, varchar(20), NOT NULL, default= first_name
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    // insert last name, varchar(20), NOT NULL, default= last_name
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;


    // Getters + setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
