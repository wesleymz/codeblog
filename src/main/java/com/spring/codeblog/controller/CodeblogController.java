package com.spring.codeblog.controller;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CodeblogController {

    @Autowired
    private CodeblogService service;

    @GetMapping(value = "/posts")
    public ModelAndView getPosts() {
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = service.findAll();
        mv.addObject("posts", posts);
        return mv;
    }

    @GetMapping(value = "/posts/{id}")
    public ModelAndView getPostDetails(@PathVariable long id) {
        ModelAndView mv = new ModelAndView("postDetails");
        Post post = service.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @GetMapping(value = "/newpost")
    public String getPostForm() {
        return "postForm";
    }

    @PostMapping(value = "/newpost")
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "redirect:/newpost";
        }

        post.setData(LocalDate.now());
        service.save(post);

        return "redirect:/posts";
    }
}
