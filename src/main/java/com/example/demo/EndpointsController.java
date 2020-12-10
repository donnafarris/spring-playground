package com.example.demo;

import org.springframework.http.MediaType;
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

    @RequestMapping("/volume/{length}/{width}/{height}")
    public String getVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height){
        MathService math = new MathService();
        math.setLength(length);
        math.setWidth(width);
        math.setHeight(height);
        return math.calculateVolume();
    }

    @PostMapping(value = "/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getArea(@RequestParam Map<String, String> params){
        StringBuilder res = new StringBuilder();
        MathService math = new MathService();
        switch (params.get("type")) {
            case "circle":
                math.setRadius(params.get("radius"));
                res.append(String.format("Area of a circle with a radius of %s is %s", params.get("radius"), math.calculateArea("circle")));
                return res.toString();
            case "rectangle":
                math.setWidth(Integer.parseInt(params.get("width")));
                res.append(String.format("Area of a %sx%s rectangle is %s", params.get("width"), params.get("height"), math.calculateArea("rectangle")));
                return res.toString();
            default:
                res.append("Invalid");
                return res.toString();

        }
    }
}
