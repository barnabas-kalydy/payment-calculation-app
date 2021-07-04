package com.example.paymentcalculationapp.ui_manager_classes;

public class DecimalSeparatorMaker {

    public static String placeDecimalSeparators(String inputStringNumber) {
        String reversedString = new StringBuilder(inputStringNumber).reverse().toString();
        StringBuilder stringNumberWithDecimalPoints = new StringBuilder();
        int counter = 1;
        for (int i = 0; i < reversedString.length(); i++) {
            stringNumberWithDecimalPoints.append(reversedString.charAt(i));
            if (counter % 3 == 0 && i != reversedString.length() - 1)
                stringNumberWithDecimalPoints.append(",");
            counter++;
        }
        return stringNumberWithDecimalPoints.reverse().toString();
    }

    public static String removeDecimalSeparators(String inputStringNumberWithDecimalSeparators) {
        return inputStringNumberWithDecimalSeparators.replaceAll(",", "");
    }

}
