package com.example.paymentcalculationapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentcalculationapp.exception.NoSuchPaymentTypeException;
import com.example.paymentcalculationapp.payment_calculation.PaymentCalculator;
import com.example.paymentcalculationapp.payment_calculation.PaymentType;
import com.example.paymentcalculationapp.ui_manager_classes.DecimalSeparatorMaker;
import com.example.paymentcalculationapp.ui_manager_classes.EventListenerSetter;

import java.util.HashMap;
import java.util.Map;

import static com.example.paymentcalculationapp.payment_calculation.PaymentType.DAILY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.HOURLY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.MONTHLY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.YEARLY;

public class MainActivity extends AppCompatActivity {

    PaymentType paymentType;
    Double paymentAmount;
    Map<PaymentType, Double> payments;

    Map<PaymentType, EditText> uiElementsByPaymentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 2 lines of setup that needed for the android app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting the views and the calculate button from activity_main.xml
        uiElementsByPaymentType = new HashMap<>();
        uiElementsByPaymentType.put(HOURLY, findViewById(R.id.hourlyPayment));
        uiElementsByPaymentType.put(DAILY, findViewById(R.id.dailyPayment));
        uiElementsByPaymentType.put(MONTHLY, findViewById(R.id.monthlyPayment));
        uiElementsByPaymentType.put(YEARLY, findViewById(R.id.yearlyPayment));

        // getting the button ui element
        Button calculateButton = findViewById(R.id.calculateButton);

        // setting default value to payment type
        paymentType = HOURLY;

        for (Map.Entry<PaymentType, EditText> paymentUiEntry : uiElementsByPaymentType.entrySet()) {
            // set onFocusChangeEventListeners to input fields
            paymentUiEntry.getValue().setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    paymentType = paymentUiEntry.getKey();
                    paymentUiEntry.getValue().setText("");
                }
            });

            // setup textChangedEventListeners to all input fields
            EventListenerSetter.setupTextChangedListener(paymentUiEntry.getValue(), MainActivity.this);
        }

        // setup calculate button behavior when clicked
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.this.readUserInputByPaymentType(paymentType);
                    payments = PaymentCalculator.calculateOtherPayments(paymentType, paymentAmount);
                    MainActivity.this.printNewValuesToUserInterface();
                } catch (NullPointerException e) {
                    Toast.makeText(MainActivity.this, "No new number entered!", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "No number entered!", Toast.LENGTH_SHORT).show();
                } catch (NoSuchPaymentTypeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @param paymentType type of payment(hourly, daily, monthly, yearly)
     *                    get and store the value from ui to specified payment type
     */
    private void readUserInputByPaymentType(PaymentType paymentType) {
        paymentAmount = Double.parseDouble(
                DecimalSeparatorMaker.removeDecimalSeparators(
                        uiElementsByPaymentType.get(paymentType).getText().toString()));
    }

    @SuppressLint("DefaultLocale")
    private void printNewValuesToUserInterface() {
        for (Map.Entry<PaymentType, EditText> paymentUiEntry : uiElementsByPaymentType.entrySet()) {
            paymentUiEntry.getValue().setText(String.format("%.2f", payments.get(paymentUiEntry.getKey())));
            paymentUiEntry.getValue().clearFocus();
        }
    }
}