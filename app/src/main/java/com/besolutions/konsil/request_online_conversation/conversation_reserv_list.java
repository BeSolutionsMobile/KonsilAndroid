package com.besolutions.konsil.request_online_conversation;

public class conversation_reserv_list {
    String id,resev_txt;

    public conversation_reserv_list(String id, String resev_txt) {
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
}
