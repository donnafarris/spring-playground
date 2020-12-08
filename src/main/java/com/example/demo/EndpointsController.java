package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class EndpointsController {

    @GetMapping("/")
    public String getIndex() {
        return "GET to index route";
    }

    @GetMapping("/tasks")
    public String getTasks(){
        return "These are tasks";
    }

    @PostMapping("/tasks")
    public String createTask() {
        return "You just POSTed to /tasks";
    }

    @GetMapping("/pi")
    public String getPi(){
        return "3.141592653589793";
    }
}
