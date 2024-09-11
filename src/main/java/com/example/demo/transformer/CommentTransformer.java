package com.example.demo.transformer;

import com.example.demo.dto.request.CommentRequest;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.model.Comment;

public class CommentTransformer {

    public static Comment CommentRequestToComment(CommentRequest commentRequest)
    {
        return Comment.builder()
                .rating(commentRequest.getRating())
                .content(commentRequest.getContent())
                .build();
    }

    public static CommentResponse CommentToCommentResponse(Comment comment)
    {
        return CommentResponse.builder()
                .content(comment.getContent())
                .rating(comment.getRating())
                .build();
    }
}
