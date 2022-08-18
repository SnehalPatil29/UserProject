package org.example.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "blog",schema = "public")
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blog_id")
    private Integer blogId;

    @Column(name="blog_title")
    private String blogTitle;

    @Column(name="blog_body")
    private String blogBody;

    @Column(name="publish_by")
    private String publishBy;

    @Column(name="is_deleted")
    private String isDeleted;

    @Column(name = "blog_created_on")
    private Timestamp blogCreatedOn;

    @Column(name = "blog_deleted_on")
    private Timestamp blogDeletedOn;


    @ManyToOne
    @JoinColumn(name="user_blog_id", referencedColumnName = "user_id")
    UserEntity userEntity;

    public BlogEntity(){

    }

    public BlogEntity(String blogTitle, String blogBody, String isDeleted, Timestamp blogCreatedOn) {
        this.blogTitle = blogTitle;
        this.blogBody = blogBody;
        this.isDeleted = isDeleted;
        this.blogCreatedOn = blogCreatedOn;
    }

    public BlogEntity(Integer blogId, String blogTitle, String blogBody, String publishBy, String isDeleted,UserEntity user) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogBody = blogBody;
        this.publishBy = publishBy;
        this.isDeleted = isDeleted;
        this.userEntity = user;
    }

    public BlogEntity(String blogTitle, String blogBody, String publishBy, String isDeleted, Timestamp blogCreatedOn, Timestamp blogUpdatedOn, UserEntity userEntity) {
        this.blogTitle = blogTitle;
        this.blogBody = blogBody;
        this.publishBy = publishBy;
        this.isDeleted = isDeleted;
        this.blogCreatedOn = blogCreatedOn;
        this.blogDeletedOn = blogUpdatedOn;
        this.userEntity = userEntity;
    }

    public BlogEntity(String blogTitle, String blogBody, String publishBy, String isDeleted) {
        this.blogTitle = blogTitle;
        this.blogBody = blogBody;
        this.publishBy = publishBy;
        this.isDeleted = isDeleted;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogBody() {
        return blogBody;
    }

    public void setBlogBody(String blogBody) {
        this.blogBody = blogBody;
    }

    public String getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(String publishBy) {
        this.publishBy = publishBy;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getBlogCreatedOn() {
        return blogCreatedOn;
    }

    public void setBlogCreatedOn(Timestamp blogCreatedOn) {
        this.blogCreatedOn = blogCreatedOn;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Timestamp getBlogDeletedOn() {
        return blogDeletedOn;
    }

    public void setBlogDeletedOn(Timestamp blogDeletedOn) {
        this.blogDeletedOn = blogDeletedOn;
    }
}
