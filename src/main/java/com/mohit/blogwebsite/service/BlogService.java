package com.mohit.blogwebsite.service;

import com.mohit.blogwebsite.dto.BlogDto;
import com.mohit.blogwebsite.dto.BlogsPageResponse;
import com.mohit.blogwebsite.entity.Blog;
import com.mohit.blogwebsite.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService {
    private BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository){
        this.blogRepository=blogRepository;
    }
    @Cacheable(value = "blogs", key = "#id")
    public BlogDto getById(String id) {
        System.out.println("with id "+id);
        Optional<Blog> blog=blogRepository.findById(id);
        if(blog==null) return  null;

        return convertToDto(blog.get());

    }


    private BlogDto convertToDto( Blog blog) {
        return new BlogDto(blog.getId(), blog.getTitle(), blog.getContent(), blog.getAuthor(), blog.getCreatedAt());
    }



    @Cacheable(value = "blogsPage", key = "'page_' + #page + '_size_' + #size")
    public BlogsPageResponse getAllPageResponse(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());
        Page<Blog> blogPage = blogRepository.findAll(pageable);

        List<BlogDto> blogDtos = blogPage.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new BlogsPageResponse(blogDtos, pageNum, pageSize,
                                     blogPage.getTotalElements(),
                                     blogPage.getTotalPages(),
                                     blogPage.isLast());
    }




    @Caching(evict = {
            @CacheEvict(value = "blogsPage", allEntries = true),  // Clears paginated blogs cache
            @CacheEvict(value = "blogs", key = "#id")  // Removes specific blog cache
    })
    public void deleteBlog(String id) {
        blogRepository.deleteById(id);
    }




    @Caching(evict = {
            @CacheEvict(value = "blogsPage", allEntries = true),  // Clears paginated blogs cache

    }
    ,put = {
            @CachePut(value = "blogs", key = "#blogDto.id")
    }
    )

    public BlogDto updateBlog(BlogDto blogDto) {
        Blog blog=blogRepository.findById(blogDto.getId()).get();
        if(blog==null){
            return  createBlog(blogDto);
        }
        if(blogDto.getAuthor()!=null) blog.setAuthor(blogDto.getAuthor());
        if(blogDto.getTitle()!=null) blog.setTitle(blogDto.getTitle());
        if(blogDto.getContent()!=null)blog.setContent(blogDto.getContent());

        blog=blogRepository.save(blog);
        return convertToDto(blog);
    }
    @CacheEvict(value = "blogsPage", allEntries = true)
    public BlogDto createBlog(BlogDto blogDto){
        Blog blog=convertToEntity(blogDto);
        blog=blogRepository.save(blog);
        return convertToDto(blog);
    }
    private Blog convertToEntity(BlogDto blogDto) {
        return  Blog.builder().
                title(blogDto.getTitle()).
                content(blogDto.getContent()).
                author(blogDto.getAuthor()).
                createdAt(Instant.now())
                .build();
    }


}
