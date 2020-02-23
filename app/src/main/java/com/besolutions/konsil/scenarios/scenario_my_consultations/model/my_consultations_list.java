package com.besolutions.konsil.scenarios.scenario_my_consultations.model;

public class my_consultations_list {
    String name, desc, price, status, img, id, type, doc_id;

    public my_consultations_list(String name, String desc, String price, String status, String img, String id, String type, String doc_id) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.status = status;
        this.img = img;
        this.id = id;
        this.doc_id = doc_id;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }
}
