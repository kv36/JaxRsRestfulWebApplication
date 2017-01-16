package org.kartheek.mygithubproject.BeanParam;

import javax.ws.rs.QueryParam;

/**
 * Created by kartheekvadlamani on 1/8/17.
 */
public class BeanParamClass {

    private @QueryParam("year")int year;
    private @QueryParam("start")int start;
    private @QueryParam("size")int size;

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
