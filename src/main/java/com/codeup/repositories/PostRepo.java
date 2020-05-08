package com.codeup.repositories;

import com.codeup.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Posts, Long> {
}
