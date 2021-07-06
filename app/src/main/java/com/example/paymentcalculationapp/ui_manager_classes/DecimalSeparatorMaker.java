package com.example.paymentcalculationapp.ui_manager_classes;

public class DecimalSeparatorMaker {

    public static String placeDecimalSeparators(String inputStringNumber) {
        StringBuilder sb = new StringBuilder(inputStringNumber);
        for (int i = inputStringNumber.length(); i > 0; i-=3) {
            sb.insert(i, " ");
        }
        return sb.toString();
    }

    public static String removeDecimalSeparators(String inputStringNumberWithDecimalSeparators) {
        return inputStringNumberWithDecimalSeparators.replaceAll(" ", "");
    }

}
