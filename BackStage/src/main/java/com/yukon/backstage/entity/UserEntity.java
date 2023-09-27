package com.yukon.backstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yukon.backstage.enums.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "This field shouldn't be empty")
    String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "This field shouldn't be empty")
    String lastName;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "email", unique = true)
    @NotEmpty(message = "This field shouldn't be empty")
    @Email
    String email;

    @JsonIgnore
    @Column(name = "password")
    @NotEmpty(message = "This field shouldn't be empty")
    String password;

    @Column(name = "phone_number", length = 10)
    String phoneNumber;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    UserRole userRole;

    @Column(name = "blocked", nullable = false)
    Boolean blocked;

    @CreationTimestamp
    @Column(name = "created_at")
    Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    Instant modifiedAt;

    public UserEntity(){

    }
    public UserEntity(Long id, String firstName, String lastName, String middleName, String email, String password, String phoneNumber, UserRole userRole, Boolean blocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.blocked = blocked;
    }

    public UserEntity(Long id, String firstName, String lastName, String middleName, String email, String password, String phoneNumber, UserRole userRole, Boolean blocked, Instant createdAt, Instant modifiedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.blocked = blocked;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
