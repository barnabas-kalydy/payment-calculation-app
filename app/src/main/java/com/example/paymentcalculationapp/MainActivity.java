package com.example.paymentcalculationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.paymentcalculationapp.exception.NoSuchPaymentTypeException;
import com.example.paymentcalculationapp.payment_calculation.PaymentCalculator;
import com.example.paymentcalculationapp.payment_calculation.PaymentType;

import java.util.Map;

import static com.example.paymentcalculationapp.payment_calculation.PaymentType.DAILY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.HOURLY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.MONTHLY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.YEARLY;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    PaymentType paymentType;
    Double paymentAmount;

    private EditText hourlyPayment;
    private EditText dailyPayment;
    private EditText monthlyPayment;
    private EditText yearlyPayment;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 2 lines of setup that needed for the app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting the views and the calculate button from activity_main.xml
        hourlyPayment = findViewById(R.id.hourlyPayment);
        dailyPayment = findViewById(R.id.dailyPayment);
        monthlyPayment = findViewById(R.id.monthlyPayment);
        yearlyPayment = findViewById(R.id.yearlyPayment);
        calculateButton = findViewById(R.id.calculateButton);

        hourlyPayment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    paymentType = HOURLY;
                    hourlyPayment.setText("");
                }
            }
        });

        dailyPayment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    paymentType = PaymentType.DAILY;
                    dailyPayment.setText("");
                }
            }
        });

        monthlyPayment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    paymentType = PaymentType.MONTHLY;
                    monthlyPayment.setText("");
                }
            }
        });

        yearlyPayment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    paymentType = PaymentType.YEARLY;
                    yearlyPayment.setText("");
                }
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPaymentAmountFromUi(paymentType);
                Map<PaymentType, Double> payments = null;
                try {
                    payments = PaymentCalculator.calculateOtherPayments(paymentType, paymentAmount);
                } catch (NoSuchPaymentTypeException e) {
                    e.printStackTrace();
                }

                hourlyPayment.setText(payments.get(HOURLY) + " Ft");
                dailyPayment.setText(payments.get(DAILY) + " Ft");
                monthlyPayment.setText(payments.get(MONTHLY) + " Ft");
                yearlyPayment.setText(payments.get(YEARLY) + " Ft");

                hourlyPayment.clearFocus();
                dailyPayment.clearFocus();
                monthlyPayment.clearFocus();
                yearlyPayment.clearFocus();
            }
        });
    }

    private void getPaymentAmountFromUi(PaymentType paymentType) {
        switch (paymentType) {
            case HOURLY:
                paymentAmount = Double.parseDouble(hourlyPayment.getText().toString());
                break;
            case DAILY:
                paymentAmount = Double.parseDouble(dailyPayment.getText().toString());
                break;
            case MONTHLY:
                paymentAmount = Double.parseDouble(monthlyPayment.getText().toString());
                break;
            case YEARLY:
                paymentAmount = Double.parseDouble(yearlyPayment.getText().toString());
                break;
            default:
                break;
        }
    }
}