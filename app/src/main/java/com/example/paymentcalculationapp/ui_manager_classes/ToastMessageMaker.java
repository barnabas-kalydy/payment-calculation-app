package com.example.paymentcalculationapp.ui_manager_classes;

import android.content.Context;
import android.widget.Toast;

public class ToastMessageMaker {
    private ToastMessageMaker() {}

    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
