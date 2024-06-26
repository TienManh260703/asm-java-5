package com.example.shopapp.common;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Random;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenCode {

    static int NUMBER_LENGTH = 10;
    static String USER = "UO_";
    static String COLOR = "CL0_";
    static String ORDER = "OD0_";
    static String PRODUCT = "P_";
    static String PRODUCT_DETAIL = "PD_";
    static String SIZE = "S_";
    static String STAFF = "ST_";
    static String CUSTOMER = "CT_";

    public static String generateUSER() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return USER + formattedNumber;
    }

    public static String generatePRODUCT() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return PRODUCT + formattedNumber;
    }

    public static String generateORDER() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return ORDER + formattedNumber;
    }

    public static String generateCOLOR() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return COLOR + formattedNumber;
    }

    public static String generateSIZE() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return SIZE + formattedNumber;
    }

    public static String generatePRODUCT_DETAIL() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return PRODUCT_DETAIL + formattedNumber;
    }

    public static String generateSTAFF() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return STAFF + formattedNumber;
    }
    public static String generateCUSTOMER() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return CUSTOMER + formattedNumber;
    }
}
