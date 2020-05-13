package com.codeup.models;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = ("INT(11) UNSIGNED"))
    private long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 50, nullable = false)
    private String body;

    @Column(nullable = false)
    private String postedAt;

    public Posts(){
    }

    //Many to one annotation to User model
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    //One to many annotation to RecipeImages model
    @Column(nullable = false)
    private String postImageUrl;

    public Posts(String title, String body, String postedAt, User user, String postImageUrl) {
        this.title = title;
        this.body = body;
        this.postedAt = postedAt;
        this.user = user;
        this.postImageUrl = postImageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }
}






