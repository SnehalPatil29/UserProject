package org.example.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name= "user", schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastname;

    @Column(name="email")
    private String email;

    @Column(name="contact")
    private Integer contact;

    @Column(name="city")
    private String city;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "user_created_on")
    private Timestamp createdOn;

    @Column(name = "user_updated_on")
    private Timestamp updatedOn;

    @Column(name = "password")
    private String password;



    @OneToMany(cascade = CascadeType.ALL)
    private Set<BlogEntity> blogEntities;

    public UserEntity(){

    }

    public UserEntity(Integer userId,String firstName, String lastname, String email, Integer contact, String city,String isDeleted) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.contact = contact;
        this.city = city;
        this.isDeleted = isDeleted;
    }

    public UserEntity(String firstName, String lastname, String email, Integer contact, String city) {

        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.contact = contact;
        this.city = city;
    }

    public UserEntity(String firstName, String lastname, String email, Integer contact, String city, Timestamp createdOn, Timestamp updatedOn) {

        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.contact = contact;
        this.city = city;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public UserEntity(String firstName, String lastname, String email, Integer contact, String city, Timestamp createdOn, Timestamp updatedOn, Set<BlogEntity> blogEntities) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.contact = contact;
        this.city = city;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.blogEntities = blogEntities;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<BlogEntity> getBlogEntities() {
        return blogEntities;
    }

    public void setBlogEntities(Set<BlogEntity> blogEntities) {
        this.blogEntities = blogEntities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
