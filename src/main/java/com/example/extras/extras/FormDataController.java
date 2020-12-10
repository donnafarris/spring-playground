package com.example.extras.extras;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public class FormDataController {

    //    POST /people HTTP/1.1
//    Host: example.com
//    Content-Type: application/x-www-form-urlencoded
//
//    first_name=Dwayne&last_name=Johnson
    @PostMapping("/individual-example")
    public String getIndividualParams(@RequestParam String from, @RequestParam("q") String query) {
        return String.format("q:%s from:%s", query, from);
    }

    @PostMapping("/string-example")
    public String getRawString(@RequestBody String rawBody) {
        return rawBody;
    }

    @PostMapping(value = "/map-example", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getMapParams(@RequestParam Map<String, String> formData) {
        return formData.toString();
    }

//    @PostMapping(value = "/object-example", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String getObjectParams(Search search) {
//        return search.toString();
//    } //access request body as an object

    @PostMapping("/posts/{postId}/comments")
    public String createComment(@PathVariable int postId, @RequestParam Map<String, String> params) {
        return String.format(
                "postId:%d notify:%s content:%s author:%s",
                postId,
                params.get("notify"),
                params.get("content"),
                params.get("author")
        );
    }
}