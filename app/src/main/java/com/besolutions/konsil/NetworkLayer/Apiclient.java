package com.besolutions.konsil.NetworkLayer;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Apiclient {

    /**
     * @API
     *
     * ---> 1) URL OF API METHOD
     *
     * ---> 2) ARRAY OF PARAMETERS KEYS
     *
     */

    LOGIN_USER("auth/login", Arrays.asList("email", "password","mobile_token")),
    LOGIN_INVESTOR("login_investor?", Arrays.asList("email", "pass")),
    INSERT_USER("auth/register", Arrays.asList("name","phone","email","password","platform","image_url","lang","mobile_token")),
    //INSERT_INVESTOR("insert_investor?", Arrays.asList("Name","Email","Password","Age","Gender","Work","Mobile","Images")),
    INSERT_ORDER("store-order", Arrays.asList("order_description","image","distance","duration","promo_code","delivery_time","order_from_location"
            ,"order_to_location","client_location_lat","client_location_long","order_location_lat", "order_location_long")),
    ACCEPT_OFFER("accept-offer", Arrays.asList("offer_id")),
    REJECT_OFFER("reject-offer",Arrays.asList("offer_id")),
    SUBMIT_OFFER("submit-offer", Arrays.asList("star_id","order_id","expected_delivery_time","offer_value")),
    SET_RATE("rate", Arrays.asList("order_id","rate","note_id")),
    SET_COMPLAINT("make-complaint", Arrays.asList("order_id","complaint_type_id","complaint")),
    CANCEL_ORDER("cancel-order", Arrays.asList("order_id")),
    SELECT_MY_REQUESTS_INVESTOR("selecte_my_request_invistor?", Collections.singletonList("id_investor")),
    SELECT_PRODUCTS("selecte_product_bymember?", Collections.singletonList("id_member")),
    SELECT_MY_INSTALLMENTS("selecte_my_installment?", Collections.singletonList("id_user")),
    SELECT_ADV_1("select_adv_1", null),
    NOTIFCATION("my-notifications", null),
    MY_ORDERS("my-orders", null),
    ORDERS("show-orders", null),
    OFFERS("order-offers/", null),
    SWITCH("switch-user", null),
    UPDATE_RATE("update_rate?", Arrays.asList("number_rate","number_star","id_user")),
    UPDATE_INSTALLMENT("update_installment?", Arrays.asList("id","status")),
    UPDATE_USER("update_user?", Arrays.asList("id","Name","Email","Password","Age","Gender","Work","Mobile","Images","Identification","Account_statement")),
    GET_BALANCE("get-balance", null),
    ADD_PHONE("add-phone", Collections.singletonList("phone")),
    CHANGE_PASS("change-password", Arrays.asList("password","password_confirmation")),
    CHANGE_PHOTO("update-personal-image", Collections.singletonList("image_url")),
    ADD_PROMO_CODE("add-promocode", Collections.singletonList("promo_code")),
    BILL_AMOUNT("finish-order", Arrays.asList("order_id","bill_amount")),
    UPDATE_INVESTOR("update_investor?", Arrays.asList("id","Name","Email","Password","Age","Gender","Work","Mobile","Images"));



    //--------------------------------------

    /**
     * @BASE_URL
     */

    String BASE_URL = "https://www.konsilmed.be4maps.com/api/";

    private final String URL;
    private final List<String> params;


    Apiclient(String URL, List<String> params) {

        this.URL = URL;
        this.params = params;
    }

    public String getURL() {
        return BASE_URL + URL;
    }

    public List<String> getParams() {
        return params;
    }
}
