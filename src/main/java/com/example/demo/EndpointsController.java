package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/calculate")
//    @ResponseBody
    public String calculate(@RequestParam(required = false, defaultValue = "add") String operation, @RequestParam String x, @RequestParam String y){
        String math = "+";
        String z = "";
        if ("add".equals(operation)) {
            math = "+";
            z = Integer.toString(Integer.parseInt(x) + Integer.parseInt(y));
        } else if ("subtract".equals(operation)) {
            math = "-";
            z = Integer.toString(Integer.parseInt(x) - Integer.parseInt(y));
        } else if ("multiply".equals(operation)) {
            math = "*";
            z = Integer.toString(Integer.parseInt(x) * Integer.parseInt(y));
        } else if ("divide".equals(operation)) {
            math = "/";
            z = Integer.toString(Integer.parseInt(x) / Integer.parseInt(y));
        }
        return String.format("%s %s %s = %s", x, math, y, z);
    }

    @PostMapping("/sum")
    public String sum(@RequestParam MultiValueMap<String, String> queryString){
        int sum = 0;
        List<String> valStrings = (queryString.values().stream().flatMap(Collection::stream).collect(Collectors.toList()));
        List<Integer> valInts = (valStrings.stream().map(Integer::parseInt).collect(Collectors.toList()));
        int[] primValInts = (valInts.stream().mapToInt(Integer::intValue).toArray());
        for (int primValInt : primValInts) {
            sum += primValInt;
        }
        return Arrays.stream(primValInts).mapToObj(String::valueOf).collect(Collectors.joining(" + ")) + " = " + sum;
    }
}
