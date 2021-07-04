package com.example.paymentcalculationapp.ui_manager_classes;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paymentcalculationapp.MainActivity;
import com.example.paymentcalculationapp.payment_calculation.PaymentType;

import java.util.Map;

import static com.example.paymentcalculationapp.payment_calculation.PaymentType.HOURLY;

public class EventListenerSetter {

    public static void setupTextChangedListener(EditText uiElement, Context context) {
        uiElement.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // if user enters zero to first number the program deletes it and notifies the user
                if (s.toString().equals("0")) {
                    uiElement.setText("");
                    Toast.makeText(context, "Please don't enter 0 as first number!", Toast.LENGTH_SHORT).show();
                }
                String withSeparators = uiElement.getText().toString();
                String withoutSeparators = DecimalSeparatorMaker.removeDecimalSeparators(withSeparators);
                if (!DecimalSeparatorMaker.placeDecimalSeparators(withoutSeparators).equals(withSeparators)) {
                    uiElement.setText(DecimalSeparatorMaker.placeDecimalSeparators(withoutSeparators));
                    uiElement.setSelection(uiElement.getText().length());
                }
            }
        });
    }

}
