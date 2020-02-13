package com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.model;

public class Radio_button_list {
    String id, title;

    public Radio_button_list(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
