package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentsController {

    @GetMapping("/") // <-- will match /posts/34/comments
    public String[] getComments(@PathVariable int postId) {
        String str1 = String.format("Comments on Post %s:", postId);
        String[] comments = {str1};
        return comments;
    }

    @GetMapping("/{commentId}") // <-- will match /posts/34/comments
    public String getComment(@PathVariable int postId, @PathVariable int commentId) {
        String str1 = String.format("Comment %s on Post %s", commentId, postId);
        return str1;
    }

}
