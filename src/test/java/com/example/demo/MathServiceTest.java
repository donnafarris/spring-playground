package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathServiceTest {

    @Test
    void setX() {
        MathService math = new MathService();
        math.setX(2);
        assertEquals(2, math.getX());
    }

    @Test
    void setY() {
        MathService math = new MathService();
        math.setY(3);
        assertEquals(3, math.getY());
    }

    @Test
    void setOperation() {
        MathService math = new MathService();
        math.setOperation("subtract");
        assertEquals("subtract", math.getOperation());
    }

    @Test
    void setN() {
        MathService math = new MathService();
        Integer[] arr = new Integer[]{1, 2, 3};
        math.setN(arr);
        assertEquals(arr, math.getN());
    }

    @Test
    void performOperation() {
        MathService math = new MathService();
        math.setOperation("subtract");
        math.setX(150);
        math.setY(30);
        assertEquals("150 - 30 = 120", math.performOperation());
    }

    @Test
    void performSummation() {
        MathService math = new MathService();
        Integer[] arr = new Integer[]{1, 2, 3};
        math.setN(arr);
        assertEquals("1 + 2 + 3 = 6", math.performSummation());
    }
}