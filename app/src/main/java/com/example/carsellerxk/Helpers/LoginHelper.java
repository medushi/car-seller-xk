package com.example.carsellerxk.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;

public class LoginHelper {

    static final String LOGGED_USER_EMAIL = "logged_in_email";
    static final String LOGGED_STATUS = "logged_in_status";

    public static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setCurrentUserLoggedInData(Context ctx, String email) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(LOGGED_USER_EMAIL, email);
        editor.commit();
    }

    public static String getLoggedInEmailUser(Context ctx) {
        return getSharedPreferences(ctx).getString(LOGGED_USER_EMAIL, "");
    }

    public static void setCurrentUserLoggedInStatus(Context ctx, boolean status) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(LOGGED_STATUS, status);
        editor.commit();
    }

    public static boolean getUserLoggedInStatus(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(LOGGED_STATUS, false);
    }

    public static void clearLoggedInEmailAddress(Context ctx) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(LOGGED_USER_EMAIL);
        editor.remove(LOGGED_STATUS);
        editor.commit();
    }

    public static boolean areFieldsValidated(String email, String password) {
        return !email.trim().isEmpty() && !password.trim().isEmpty();
    }

    public static boolean areFieldsValidated(String firstName,String lastName,String password,String email,String city){
        return !firstName.trim().isEmpty() && !lastName.trim().isEmpty() && !password.trim().isEmpty() &&
                email.trim().isEmpty() && !city.trim().isEmpty();
    }
}
