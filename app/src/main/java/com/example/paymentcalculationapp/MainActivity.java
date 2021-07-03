package com.example.paymentcalculationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        // setting default value to payment type
        paymentType = HOURLY;

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
                Map<PaymentType, Double> payments;
                try {
                    getPaymentAmountFromUi(paymentType);
                    payments = PaymentCalculator.calculateOtherPayments(paymentType, paymentAmount);

                    hourlyPayment.setText(String.format("%.2f", payments.get(HOURLY)));
                    dailyPayment.setText(String.format("%.2f", payments.get(DAILY)));
                    monthlyPayment.setText(String.format("%.2f", payments.get(MONTHLY)));
                    yearlyPayment.setText(String.format("%.2f", payments.get(YEARLY)));

                    hourlyPayment.clearFocus();
                    dailyPayment.clearFocus();
                    monthlyPayment.clearFocus();
                    yearlyPayment.clearFocus();
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