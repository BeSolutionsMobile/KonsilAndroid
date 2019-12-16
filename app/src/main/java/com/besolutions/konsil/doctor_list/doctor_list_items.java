package com.besolutions.konsil.doctor_list;

public class doctor_list_items {
    String id,name,degree,img;
    int rate;

    public doctor_list_items(String id, String name, String degree, String img, int rate) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.img = img;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
