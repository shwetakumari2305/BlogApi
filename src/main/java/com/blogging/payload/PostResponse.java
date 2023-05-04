package com.blogging.payload;

import lombok.Data;

import java.util.List;
@Data
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totatElements;
    private int totalPages;
    private boolean isLast;

}
