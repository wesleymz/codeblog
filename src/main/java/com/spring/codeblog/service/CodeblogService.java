package com.spring.codeblog.service;

import com.spring.codeblog.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CodeblogService {

    List<Post> findAll();

    Post findById(long id);

    Post save(Post post);
}
