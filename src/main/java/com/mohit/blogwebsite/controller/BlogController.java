package com.mohit.blogwebsite.controller;

import com.mohit.blogwebsite.dto.BlogDto;
import com.mohit.blogwebsite.dto.BlogsPageResponse;
import com.mohit.blogwebsite.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // ✅ Get a single blog by ID
    @GetMapping(value = "/blog/{id}",produces = "application/json")
    public BlogDto getById(@PathVariable(name = "id") String id) {
        return blogService.getById(id);
    }

    // Get paginated blogs
    @GetMapping("/blog")
    public BlogsPageResponse getAll(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return blogService.getAllPageResponse(pageNum, pageSize);
    }

    // ✅ Create a new blog
    @PostMapping("/blog")
    public BlogDto createBlog(@RequestBody BlogDto blogDto) {
        return blogService.createBlog(blogDto);
    }

    // ✅ Update an existing blog
    @PutMapping("/blog")
    public BlogDto updateBlog( @RequestBody BlogDto blogDto) {

        return blogService.updateBlog(blogDto);
    }

    // ✅ Delete a blog by ID
    @DeleteMapping("/blog/{id}")
    public String deleteBlog(@PathVariable String id) {
        blogService.deleteBlog(id);
        return "Blog with ID " + id + " deleted successfully.";
    }
}
