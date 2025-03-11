package com.mohit.blogwebsite.dto;


import java.util.List;

public record BlogsPageResponse(List<BlogDto> blogDtos,
                                Integer pageNum,
                                Integer pageSize,
                                long totalElem,
                                int totalPage,
                                boolean isLast) {





}