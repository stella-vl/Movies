package bg.smg.model;

import lombok.Data;

public class Movie {
    private String title;
    private int year;
    private int length;
    private char inColor;
    private String studioName;
    private int producerC;


    @Data

    public Movie() {
        title = "";

    }
}
