package com.besolutions.konsil.NetworkLayer;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.besolutions.konsil.local_data.saved_data;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @desc Java Api Calls Contains all the Project Calls
 */

public class Apicalls {

    private APIRouter apiRouter;

    public Apicalls(Context context, NetworkInterface networkInterface) {

        apiRouter = new APIRouter(context, networkInterface);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func User Login
     */

    public void loginUser(final String email, final String pass, final String mob_token) {

        apiRouter.performRequest(Apiclient.LOGIN_USER.getURL(), Apiclient.LOGIN_USER.getParams(), Arrays.asList(email, pass, mob_token), Request.Method.POST, 0);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func GET ALL SPECIALITIES
     */

    public void get_all_specialities() throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.ALL_SPECIALITIES.getURL(), Request.Method.GET, Apiclient.ALL_SPECIALITIES.getParams(), null, null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func User Registration
     */

    public void insertUser(final String name, final String phone, final String email, final String password, final String platform, final String image_url, final String lang, final String mobile_token) {

        apiRouter.performRequest(Apiclient.INSERT_USER.getURL(), Apiclient.INSERT_USER.getParams(), Arrays.asList(name, phone, email, password, platform, image_url, lang, mobile_token), Request.Method.POST, 2);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func GET ALL DOCTOR SPECIALIY
     */

    public void doctor_speciality(final int id) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.ALL_DOCS_SPECI.getURL(), Request.Method.POST, Apiclient.ALL_DOCS_SPECI.getParams(), Arrays.asList("" + id), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Accept Offer
     */

    public void doctor_details(final String doctor_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.DOCTORS_DETAILS.getURL(), Request.Method.POST, Apiclient.DOCTORS_DETAILS.getParams(), Arrays.asList(doctor_id), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

//----------------------------------------------------------------------------------------------

    /**
     * @func FILTER
     */

    public void FILTER(final String speciality_id, final List<Integer> degree_id, final String rate) {

        try {


            apiRouter.makeAdvancedRequest_array(Apiclient.FILTER.getURL(), Request.Method.POST, speciality_id, degree_id, rate, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------


    /**
     * @func ADD CONSULTATION
     */

    public void add_consultation(final String title, final String details, final String doc_id) throws JSONException {

        apiRouter.makeAdvancedRequest_addconsultation(Apiclient.ADD_CONSULTATION.getURL(), Request.Method.POST, title, details, doc_id, null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Send Message
     */

    public void send_msg(final String consultation_id, final String message) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.SEND_MSG.getURL(), Request.Method.POST, Apiclient.SEND_MSG.getParams(), Arrays.asList(consultation_id, message), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Appointments
     */

    public void appointment(String doctor_id, String date) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.APPOIENMENTS.getURL(), Request.Method.POST, Apiclient.APPOIENMENTS.getParams(), Arrays.asList(doctor_id, date), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func UPDATE_USER_INFO
     */

    public void update_personal_info(String name, String phone, String email, String password, String image_url, String patient_history) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.UPDATE_USER_INFO.getURL(), Request.Method.POST, Apiclient.UPDATE_USER_INFO.getParams(), Arrays.asList(name, phone, email, password, image_url, patient_history), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Get Complaint Type
     */

    public void get_complaint_type() throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.GET_COMPLAINT_TYPE.getURL(), Request.Method.GET, Apiclient.ALL_SPECIALITIES.getParams(), null, null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Make Complaint
     */

    public void make_complaint(String type_id, String message, String consultation_id) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.MAKE_COMPLAINT.getURL(), Request.Method.POST, Apiclient.MAKE_COMPLAINT.getParams(), Arrays.asList(type_id, message, consultation_id), null);

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func MY_CONSULTATIONS
     */

    public void my_consultations() throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.MY_CONSULTATIONS.getURL(), Request.Method.GET, Apiclient.MY_CONSULTATIONS.getParams(), null, null);

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func CONFIRM_CONSULTATION
     */

    public void confirm_consultation(String consultation_id, String payment_status, String promo_code_id) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.CONFIRM_CONSULTATION.getURL(), Request.Method.POST, Apiclient.CONFIRM_CONSULTATION.getParams(), Arrays.asList(consultation_id, payment_status, promo_code_id), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func RESERVE_CONVERSATION
     */

    public void reserve_conversation(String doctor_id, String appointment_id) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.RESERVE_CONVERSATION.getURL(), Request.Method.POST, Apiclient.RESERVE_CONVERSATION.getParams(), Arrays.asList(doctor_id, appointment_id), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Confirm Conversation
     */

    public void confirm_conversation(final String consultation_id, final String payment_status, String consultation_promo) throws JSONException {
        apiRouter.makeAdvancedRequest(Apiclient.CONFIRM_CONVERSATION.getURL(), Request.Method.POST, Apiclient.CONFIRM_CONVERSATION.getParams(), Arrays.asList(consultation_id, payment_status, consultation_promo), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func FAQ
     */

    public void faq() throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.FAQ.getURL(), Request.Method.GET, Apiclient.FAQ.getParams(), null, null);
    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func GET MESSAGES
     */

    public void get_all_msg(final String consultation_id) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.GET_ALL_MSG.getURL(), Request.Method.POST, Apiclient.GET_ALL_MSG.getParams(), Arrays.asList(consultation_id), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Download Files
     */

    public void download_file(final String consultation_id) throws JSONException {
        apiRouter.makeAdvancedRequest(Apiclient.DOWNLOAD_REPORT.getURL(), Request.Method.POST, Apiclient.DOWNLOAD_REPORT.getParams(), Arrays.asList(consultation_id), null);
    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func CONSULTATION_FILES
     */

    public void consultation_files(final String consultation_id) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.CONSULTATION_FILES.getURL(), Request.Method.POST, Apiclient.CONSULTATION_FILES.getParams(), Arrays.asList(consultation_id), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func CHANGE LANGUAGE
     */

    public void change_lan(Context context) throws JSONException {

        String language = new saved_data().get_lan(context);

        if (language.equals("en")) {
            language = "en";  //IF LANGUAGE IS ENGLISH
        } else if (language.equals("de")) {
            language = "de";  //IF LANGUAGE IS GERMANY
        } else if (language.equals("ar")) {
            language = "ar";  //IF LANGUAGE IS ARABIC
        }


        apiRouter.makeAdvancedRequest(Apiclient.CHANGE_LANG.getURL(), Request.Method.POST, Apiclient.CHANGE_LANG.getParams(), Arrays.asList(language), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Get Offer Data
     */

    public void uplaod_consultation() throws JSONException {

        apiRouter.makeAdvancedRequest_uplaodconsultation_files(Apiclient.UPLOAD_CONSULTATION_FILES.getURL(), Request.Method.POST);

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func CHECK OUT
     */

    public void check_out(String amount) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.CHECK_OUT.getURL(), Request.Method.POST, Apiclient.CHECK_OUT.getParams(), Arrays.asList(amount), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Get Orders Data
     */

    public void Get_order_data() {


    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Submit Offer
     */

    public void Submit_Offer(final String star_id, final String order_id, final String expected_delivery_time, final String offer_value) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.SUBMIT_OFFER.getURL(), Request.Method.POST, Apiclient.SUBMIT_OFFER.getParams(), Arrays.asList(star_id, order_id, expected_delivery_time, offer_value), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Get Offer Data
     */

    public void Get_myOrder() {


    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Set Rate
     */

    public void set_rate(String order_id, String rate, String note_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.SET_RATE.getURL(), Request.Method.POST, Apiclient.SET_RATE.getParams(), Arrays.asList(order_id, rate, note_id), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Set Complaint
     */

    public void set_complaint(String order_id, String complaint_type_id, String complaint) {

//        try {
//            apiRouter.makeAdvancedRequest(Apiclient.SET_COMPLAINT.getURL(), Request.Method.POST,Apiclient.SET_COMPLAINT.getParams(),Arrays.asList(order_id,complaint_type_id,complaint),null);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Cancel Order
     */

    public void cancel_order(String order_id) {


    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Get notifcation Data
     */

    public void Get_notifcation_data() {

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Reject Offer
     */

    public void change_password(final String password, final String password_confirmation) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_PASS.getURL(), Request.Method.PATCH, Apiclient.CHANGE_PASS.getParams(), Arrays.asList(password, password_confirmation), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Reject Offer
     */

    public void add_phone(final String phone) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.ADD_PHONE.getURL(), Request.Method.PATCH, Apiclient.ADD_PHONE.getParams(), Collections.singletonList(phone), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Reject Offer
     */

    public void change_photo(final String image) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_PHOTO.getURL(), Request.Method.PATCH, Apiclient.CHANGE_PHOTO.getParams(), Collections.singletonList(image), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Get Offer Data
     */


    public void get_balance() {


    }


    //----------------------------------------------------------------------------------------------

    public void bill_amount(final String order_id, final String bill_amount) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.BILL_AMOUNT.getURL(), Request.Method.PATCH, Apiclient.BILL_AMOUNT.getParams(), Arrays.asList(order_id, bill_amount), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func GET PROMO CODE
     */

    public void promoCode(final String promo_code) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.PROMO_CODE.getURL(), Request.Method.POST, Apiclient.PROMO_CODE.getParams(), Arrays.asList(promo_code), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------


}
