package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/math")
public class EndpointsController {

    @GetMapping("/pi")
    public String getPi(){
        return "3.141592653589793";
    }

    @GetMapping("/calculate")
//    @ResponseBody
    public String calculate(@RequestParam(required = false, defaultValue = "add") String operation, @RequestParam String x, @RequestParam String y){
          MathService math = new MathService();
          math.setOperation(operation);
          math.setX(Integer.parseInt(x));
          math.setY(Integer.parseInt(y));
          return math.performOperation();
    }

    @PostMapping("/sum")
    public String sum(@RequestParam(name="n") Integer[] n){
        MathService math = new MathService();
        math.setN(n);
        return math.performSummation();
    }
}
