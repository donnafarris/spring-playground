package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PathVariableController {

    @GetMapping("/individual-example/{q}/{from}") // matches /individual-example/foo/bar
    public String getIndividualParams(@PathVariable String from, @PathVariable("q") String query) {
        return String.format("q:%s from:%s", query, from);
    }

    @GetMapping("/tasks/{taskId}/comments/{commentId}")
    public String getCommentsForTask(@PathVariable int taskId, @PathVariable int commentId) {
        return String.format("taskId is %d; commentId is %d", taskId, commentId);
    }

//    @GetMapping("/test/tasks/{taskId}/comments/{commentId}")
//    public String getCommentsForTask(@PathVariable Map pathVariables) {
//        return pathVariables.toString(); // {taskId=46, commentId=35}
//    }

    @GetMapping("/test/tasks/{taskId}/comments/{commentId}")
    public String getCommentsForTask(TaskIds ids) {
        return String.format("taskId is %d; commentId is %s", ids.getTaskId(), ids.getCommentId());
    }

}