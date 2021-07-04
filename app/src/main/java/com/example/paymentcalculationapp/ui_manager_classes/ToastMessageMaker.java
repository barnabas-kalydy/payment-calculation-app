package com.example.paymentcalculationapp.ui_manager_classes;

import android.widget.Toast;

import static com.example.paymentcalculationapp.setup_and_global_variables.GlobalVariables.CONTEXT;

public class ToastMessageMaker {
    private ToastMessageMaker() {}

    public static void toastMessage(String message) {
        Toast.makeText(CONTEXT, message, Toast.LENGTH_SHORT).show();
    }
}
