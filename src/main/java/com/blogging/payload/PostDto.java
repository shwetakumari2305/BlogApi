package com.blogging.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @NotNull
    @NotEmpty
    @Size(min = 6,message = "Post title should have minimum 6 characters")
    private String title;
    @NotEmpty
    @NotNull
    @Size(min = 10, message = "Post description should have minimum 10 characters")
    private String description;
    @NotNull
    @NotEmpty(message = "Post content must not be empty")
    private String content;
}
