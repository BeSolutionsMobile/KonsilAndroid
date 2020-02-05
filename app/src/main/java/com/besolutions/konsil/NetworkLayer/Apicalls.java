package com.besolutions.konsil.NetworkLayer;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONException;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @desc Java Api Calls Contains all the Project Calls
 */

public class Apicalls {

    private APIRouter apiRouter;

    public Apicalls(Context context, NetworkInterface networkInterface) {

        apiRouter = new APIRouter(context, networkInterface);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func User Login
     */

    public void loginUser(final String email, final String pass,final String mob_token) {

        apiRouter.performRequest(Apiclient.LOGIN_USER.getURL(),Apiclient.LOGIN_USER.getParams(), Arrays.asList(email,pass,mob_token), Request.Method.POST,0);

    }



    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Investor Login
     */

    public void loginInvestor(final String email, final String pass) {

        apiRouter.performRequest(Apiclient.LOGIN_INVESTOR.getURL(),Apiclient.LOGIN_INVESTOR.getParams(), Arrays.asList(email,pass), Request.Method.POST,1);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func User Registration
     */

    public void insertUser(final String name, final String phone, final String email, final String password,final String platform,final String image_url,final String lang,final String mobile_token) {

        apiRouter.performRequest(Apiclient.INSERT_USER.getURL(),Apiclient.INSERT_USER.getParams(), Arrays.asList(name,phone,email,password,platform,image_url,lang,mobile_token), Request.Method.POST,2);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Investor Registration
     */

    public void insertInvestor(final String Name, final String Email, final String Password, final String Age, final String Gender, final String Work, final String Mobile, final String Images) {

     //   apiRouter.performRequest(Apiclient.INSERT_INVESTOR.getURL(),Apiclient.INSERT_INVESTOR.getParams(), Arrays.asList(Name,Email,Password,Age,Gender,Work,Mobile,Images), Request.Method.POST,3);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Accept Offer
     */

    public void AcceptOffer(final String offer_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.ACCEPT_OFFER.getURL(), Request.Method.POST,Apiclient.ACCEPT_OFFER.getParams(), Arrays.asList(offer_id),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

//----------------------------------------------------------------------------------------------

    /**
     *
     * @func Reject Offer
     */

    public void RejectOffer(final String offer_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.REJECT_OFFER.getURL(), Request.Method.POST,Apiclient.REJECT_OFFER.getParams(), Arrays.asList(offer_id),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Retrieve Investors
     */

    public void selectInvestors(final String id_member) {

//        apiRouter.performRequest(Apiclient.SELECT_INVESTORS.getURL(),Apiclient.SELECT_INVESTORS.getParams(), Collections.singletonList(id_member), Request.Method.POST,5);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Retrieve Products
     */

    public void selectProducts(final String id_member) {

        apiRouter.performRequest(Apiclient.SELECT_PRODUCTS.getURL(),Apiclient.SELECT_PRODUCTS.getParams(), Collections.singletonList(id_member), Request.Method.POST,6);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Retrieve ADV1
     */

    public void selectAdv1() {

        apiRouter.performRequest(Apiclient.SELECT_ADV_1.getURL(),Apiclient.SELECT_ADV_1.getParams(),null, Request.Method.POST,16);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Retrieve ADV2
     */

    public void selectAdv2() {

    //    apiRouter.performRequest(Apiclient.SELECT_ADV_2.getURL(),Apiclient.SELECT_ADV_2.getParams(),null, Request.Method.POST,17);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Retrieve ADV3
     */

    public void selectAdv3() {

       // apiRouter.performRequest(Apiclient.SELECT_ADV_3.getURL(),Apiclient.SELECT_ADV_3.getParams(),null, Request.Method.POST,18);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Retrieve ADV4
     */

    public void selectAdv4() {

        //apiRouter.performRequest(Apiclient.SELECT_ADV_4.getURL(),Apiclient.SELECT_ADV_4.getParams(),null, Request.Method.POST,19);

    }


    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Retrieve User Requests
     */

    public void selectMyRequests(final String id_user) {

      //  apiRouter.performRequest(Apiclient.SELECT_MY_REQUESTS.getURL(),Apiclient.SELECT_MY_REQUESTS.getParams(), Collections.singletonList(id_user), Request.Method.POST,7);

    }


    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Update User Data
     */

    public void updateUser(final String id, final String Name, final String Email, final String Password, final String Age, final String Gender, final String Work, final String Mobile, final String Images, final String Identification, final String Account_statement) {

        apiRouter.performRequest(Apiclient.INSERT_USER.getURL(),Apiclient.INSERT_USER.getParams(), Arrays.asList(id,Name,Email,Password,Age,Gender,Work,Mobile,Images,Identification,Account_statement), Request.Method.POST,8);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Update Investor Data
     */

    public void updateInvestor(final String id, final String Name, final String Email, final String Password, final String Age, final String Gender, final String Work, final String Mobile, final String Images) {

       // apiRouter.performRequest(Apiclient.INSERT_INVESTOR.getURL(),Apiclient.INSERT_INVESTOR.getParams(), Arrays.asList(id,Name,Email,Password,Age,Gender,Work,Mobile,Images), Request.Method.POST,9);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Update Investor Data
     */

    public void Insert_Order(final String order_descripition, final String image, final String distance, final String duration, final String promo_code, final String delivery_time, final String order_from_location,
                             final String order_to_location,String client_location_lat,String client_location_long,String order_location_lat,String order_location_long) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.INSERT_ORDER.getURL(), Request.Method.POST,Apiclient.INSERT_ORDER.getParams(),Arrays.asList(order_descripition,image,distance,duration,promo_code,delivery_time,
                    order_from_location,order_to_location,client_location_lat,client_location_long,order_location_lat,order_location_long),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Retrieve User Requests
     */

    public void selectMyRequestsInvestor(final String id_investor) {

        apiRouter.performRequest(Apiclient.SELECT_MY_REQUESTS_INVESTOR.getURL(),Apiclient.SELECT_MY_REQUESTS_INVESTOR.getParams(), Collections.singletonList(id_investor), Request.Method.POST,11);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Retrieve Products
     */

    public void selectUser(final String id_user) {

      //  apiRouter.performRequest(Apiclient.SELECT_USER.getURL(),Apiclient.SELECT_USER.getParams(), Collections.singletonList(id_user), Request.Method.POST,12);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Retrieve Products
     */

    public void selectInstallments(final String id_user) {

        apiRouter.performRequest(Apiclient.SELECT_MY_INSTALLMENTS.getURL(),Apiclient.SELECT_MY_INSTALLMENTS.getParams(), Collections.singletonList(id_user), Request.Method.POST,13);

    }


    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Update Investor Data
     */

    public void updateInstallment(final String id, final String status) {

        apiRouter.performRequest(Apiclient.UPDATE_INSTALLMENT.getURL(),Apiclient.UPDATE_INSTALLMENT.getParams(), Arrays.asList(id,status), Request.Method.POST,14);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Update Investor Data
     */

    public void updateRate(final String number_rate, final String number_star, final String id_user) {

        apiRouter.performRequest(Apiclient.UPDATE_RATE.getURL(),Apiclient.UPDATE_RATE.getParams(), Arrays.asList(number_rate,number_star,id_user), Request.Method.POST,15);

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Get Offer Data
     */

    public void Get_data(String id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.OFFERS.getURL()+id, Request.Method.GET,Apiclient.OFFERS.getParams(),null,null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Get Orders Data
     */

    public void Get_order_data() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.ORDERS.getURL(), Request.Method.GET,Apiclient.ORDERS.getParams(),null,null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Submit Offer
     */

    public void Submit_Offer(final String star_id,final String order_id,final String expected_delivery_time,final String offer_value) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.SUBMIT_OFFER.getURL(), Request.Method.POST,Apiclient.SUBMIT_OFFER.getParams(),Arrays.asList(star_id,order_id,expected_delivery_time,offer_value),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Get Offer Data
     */

    public void Get_myOrder() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.MY_ORDERS.getURL(), Request.Method.GET,Apiclient.MY_ORDERS.getParams(),null,null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Set Rate
     */

    public void set_rate(String order_id,String rate,String note_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.SET_RATE.getURL(), Request.Method.POST,Apiclient.SET_RATE.getParams(),Arrays.asList(order_id,rate,note_id),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Set Complaint
     */

    public void set_complaint(String order_id,String complaint_type_id,String complaint) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.SET_COMPLAINT.getURL(), Request.Method.POST,Apiclient.SET_COMPLAINT.getParams(),Arrays.asList(order_id,complaint_type_id,complaint),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Cancel Order
     */

    public void cancel_order(String order_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CANCEL_ORDER.getURL(), Request.Method.PATCH,Apiclient.CANCEL_ORDER.getParams(),Arrays.asList(order_id
            ),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Get notifcation Data
     */

    public void Get_notifcation_data() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.NOTIFCATION.getURL(), Request.Method.GET,Apiclient.NOTIFCATION.getParams(),null,null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Reject Offer
     */

    public void change_password (final String password,final String password_confirmation) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_PASS.getURL(), Request.Method.PATCH,Apiclient.CHANGE_PASS.getParams(), Arrays.asList(password,password_confirmation),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Reject Offer
     */

    public void add_phone (final String phone) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.ADD_PHONE.getURL(), Request.Method.PATCH,Apiclient.ADD_PHONE.getParams(),Collections.singletonList(phone),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Reject Offer
     */

    public void change_photo (final String image) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_PHOTO.getURL(), Request.Method.PATCH,Apiclient.CHANGE_PHOTO.getParams(),Collections.singletonList(image),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Get Offer Data
     */


    public void get_balance() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.GET_BALANCE.getURL(), Request.Method.GET,Apiclient.GET_BALANCE.getParams(),null,null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    public void bill_amount(final String order_id,final String bill_amount) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.BILL_AMOUNT.getURL(), Request.Method.PATCH,Apiclient.BILL_AMOUNT.getParams(),Arrays.asList(order_id,bill_amount),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func GET PROMO CODE
     */

    public void promoCode(final String promo_code) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.ADD_PROMO_CODE.getURL(), Request.Method.POST,Apiclient.ADD_PROMO_CODE.getParams(),Arrays.asList(promo_code),null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------





}
