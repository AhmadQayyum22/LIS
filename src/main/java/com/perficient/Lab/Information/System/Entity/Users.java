package com.perficient.Lab.Information.System.Entity;

import com.perficient.Lab.Information.System.Annotation.FieldsValueMatch;
import com.perficient.Lab.Information.System.Annotation.PasswordValidator;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;


//@Data
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email addresses do not match!"
        )
})
@Table(name = "users")
public class Users extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "Fname")
//    @NotBlank(message="First Name must not be blank")
    @Size(min=3, message="First Name must be at least 3 characters long")
    private String firstName;

    @Column(name = "Lname")
//    @NotBlank(message="Last Name must not be blank")
    @Size(min=3, message="Last Name must be at least 3 characters long")
    private String lastName;


    @Column(name = "user_name")
//    @NotBlank(message="User Name must not be blank")
    @Size(min=3, message="User Name must be at least 3 characters long")
    private String userName;

    @Column(name = "email")
    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;


    @Transient
    private String confirmEmail;

    @Column(name = "password")
//    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    @PasswordValidator
    private String password;

    @Transient
    private String confirmPassword;





    @Column(name = "Isapproved")
    private boolean isApproved;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role", referencedColumnName = "role_id",nullable = false)
    private Roles roles;

    public Users() {
    }

    public Users(int userId, String firstName, String lastName, String userName, String email, String confirmEmail, String password, String confirmPassword, boolean isApproved, Roles role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.isApproved = isApproved;
        this.roles = role;
    }
    public Users(int userId, String firstName, String lastName, String userName, String email, String password, boolean isApproved, Roles role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isApproved = isApproved;
        this.roles = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isApproved=" + isApproved +
                ", role=" + roles +
                '}';
    }
}
