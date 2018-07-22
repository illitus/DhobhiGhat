package com.example.sarve.dhobhighat;

/**
 * Created by sarve on 23-04-2018.
 */

public final class ContactContract {
    private ContactContract(){}

    public static class ContactEntry
    {
        public static final String TABLE_NAME = "contact";
        public static final String EMAIL = "email";
        public static final String NAME = "name";
        public static final String PASS = "pass";
    }
}
