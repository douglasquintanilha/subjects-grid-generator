package br.com.unirio.constants;

public enum ColorConstants {

    APPROVED_COLOR("#12ed42"),
    REPROVED_COLOR("#f27676");

    private String color;


    ColorConstants(String color) {
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
}
