package com.example.carsellerxk.Helpers;

import com.example.carsellerxk.Models.TestModel;

import java.util.HashMap;

public class TestingPurposeHelpers {
    public static String _currentUser;

    public static String get_currentUser() {
        return _currentUser;
    }

    public static void set_currentUser(String _currentUser) {
        TestingPurposeHelpers._currentUser = _currentUser;
    }

    public static void insertTestData(TestModel testmodel){

    }
}
