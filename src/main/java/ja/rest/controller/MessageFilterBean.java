package ja.rest.controller;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {

    @QueryParam(value = "year")
    private int year;
    @QueryParam(value = "start")
    private int start;
    @QueryParam(value = "number")
    private int number;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}