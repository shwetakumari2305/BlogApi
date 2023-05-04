package com.blogging.service;

import com.blogging.payload.PostDto;
import com.blogging.payload.PostResponse;

public interface PostService {

     PostDto createNewPost(PostDto postDto);

     PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

     PostDto getPostById(long id);

     PostDto updatePostById(long id, PostDto postDto);

     void deletePostById(long id);
}
