package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(EndpointsController.class)
class EndpointsControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void getIndex() {
    }

    @Test
    void getTasks() {
    }

    @Test
    void createTask() {
    }

    @Test
    public void testMathPi() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((content().string("3.141592653589793")));
    }

    @Test
    public void testMathCalculateAdd() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((content().string("4 + 6 = 10")));
    }

    @Test
    public void testMathCalculateMultiply() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=4&y=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((content().string("4 * 6 = 24")));
    }

    @Test
    public void testMathCalculateSubtract() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=4&y=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((content().string("4 - 6 = -2")));
    }

    @Test
    public void testMathCalculateDivide() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=30&y=5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((content().string("30 / 5 = 6")));
    }

    @Test
    public void testMathCalculateDefault() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?x=30&y=5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((content().string("30 + 5 = 35")));
    }

    @Test
    public void testMathSum() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/math/sum?n=4&n=5&n=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((content().string("4 + 5 + 6 = 15")));
    }

    @Test
    public void testCalculateVolume60() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/volume/3/4/5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((content().string("The volume of a 3x4x5 rectangle is 60")));
    }

    @Test
    public void testCalculateVolume336() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/volume/6/7/8");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((content().string("The volume of a 6x7x8 rectangle is 336")));
    }
}