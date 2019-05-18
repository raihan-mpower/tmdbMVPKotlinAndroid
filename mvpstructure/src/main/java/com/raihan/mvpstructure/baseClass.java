package com.raihan.mvpstructure;

public class baseClass {
    private static final baseClass ourInstance = new baseClass();

    public static baseClass getInstance() {
        return ourInstance;
    }

    private baseClass() {
    }
}
