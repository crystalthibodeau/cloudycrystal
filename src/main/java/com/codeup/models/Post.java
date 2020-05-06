package com.codeup.models;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = ("INT(11) UNSIGNED"))
    private long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 50, nullable = false)
    private String body;

    @Column(nullable = false)
    String postedAt;

    public Post(){
    }

    //Many to one annotation to User model
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

//    @ManyToMany
//    @JoinTable(
//
//            name = "recipes_categories",
//            joinColumns = {@JoinColumn(name = "recipe_id")},
//            inverseJoinColumns = {@JoinColumn(name = "category_id")}
//    )
//    private List<Categories> categories;



    //Many to many annotation to User model for favorites
//    @ManyToMany(mappedBy = "favorites")
//    private List<User> favoritedBy;

    //One to many annotation to RecipeImages model
    @Column(nullable = false)
    private String postImageUrl;

    //One to many annotation to Comments model
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
//    private List<Comments> comments;

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

//    public List<User> getFavoritedBy() {
//        return favoritedBy;
//    }

//    public void setFavoritedBy(List<User> favoritedBy) {
//        this.favoritedBy = favoritedBy;
//    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

//    public List<Categories> getCategories() {
//        return categories;
//    }

//    public void setCategories(List<Categories> categories) {
//        this.categories = categories;
//    }

//    public List<Comments> getComments() {
//        return comments;
//    }

//    public void setComments(List<Comments> comments) {
//        this.comments = comments;
//    }

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
}






