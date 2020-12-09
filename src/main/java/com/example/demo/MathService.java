package com.example.demo;

public class MathService {
    private int x;
    private int y;
    private String operation = "add";
    private Integer[] n;
    private int length;
    private int width;
    private int height;

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setOperation(String operation){
        this.operation = operation;
    }

    public void setN(Integer[] n){
        this.n = n;
    }


    public String performOperation(){
        switch (operation) {
            case "subtract":
                return String.format("%s - %s = %s", x, y, x-y);
            case "multiply":
                return String.format("%s * %s = %s", x, y, x*y);
            case "divide":
                return String.format("%s / %s = %s", x, y, x/y);
            default:
                return String.format("%s + %s = %s", x, y, x+y);
        }
    }

    public String performSummation(){
        int sum = 0;
        StringBuilder res = new StringBuilder();
        for (int i : n) {
            res.append(i).append(" + ");
            sum += i;
        }
        return res.substring(0, res.length() - 2) + "= " + sum;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getOperation() {
        return this.operation;
    }

    public Integer[] getN() {
        return this.n;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String calculateVolume() {
        int volume = this.length * this.width * this.height;
        return String.format("The volume of a %sx%sx%s rectangle is %s", length, width, height, volume);
    }
}
