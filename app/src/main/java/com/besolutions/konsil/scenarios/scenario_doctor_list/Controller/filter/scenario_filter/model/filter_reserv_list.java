package com.besolutions.konsil.scenarios.scenario_doctor_list.Controller.filter.scenario_filter.model;

public class filter_reserv_list {
    String id,resev_txt;
    Boolean status=false;

    public filter_reserv_list(Boolean status)
    {
        this.status=status;
    }

    public filter_reserv_list(String id, String resev_txt) {
        this.id = id;
        this.resev_txt = resev_txt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResev_txt() {
        return resev_txt;
    }

    public void setResev_txt(String resev_txt) {
        this.resev_txt = resev_txt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
