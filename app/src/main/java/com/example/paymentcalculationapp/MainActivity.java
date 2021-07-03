package com.example.paymentcalculationapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentcalculationapp.exception.NoSuchPaymentTypeException;
import com.example.paymentcalculationapp.payment_calculation.PaymentCalculator;
import com.example.paymentcalculationapp.payment_calculation.PaymentType;

import java.util.Map;

import static com.example.paymentcalculationapp.payment_calculation.PaymentType.DAILY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.HOURLY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.MONTHLY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.YEARLY;

public class MainActivity extends AppCompatActivity {
    PaymentType paymentType;
    Double paymentAmount;
    Map<PaymentType, Double> payments;

    private EditText hourlyPayment;
    private EditText dailyPayment;
    private EditText monthlyPayment;
    private EditText yearlyPayment;

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
        Button calculateButton = findViewById(R.id.calculateButton);

        // setting default value to payment type
        paymentType = HOURLY;


        hourlyPayment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                paymentType = HOURLY;
                hourlyPayment.setText("");
            }
        });

        dailyPayment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                paymentType = PaymentType.DAILY;
                dailyPayment.setText("");
            }
        });

        monthlyPayment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                paymentType = PaymentType.MONTHLY;
                monthlyPayment.setText("");
            }
        });

        yearlyPayment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                paymentType = PaymentType.YEARLY;
                yearlyPayment.setText("");
            }
        });

        // setup calculate button behavior when clicked
        calculateButton.setOnClickListener(v -> {
            try {
                getPaymentAmountFromUi(paymentType);
                payments = PaymentCalculator.calculateOtherPayments(paymentType, paymentAmount);
                printNewValuesToUserInterface();
            } catch (NullPointerException e) {
                Toast.makeText(MainActivity.this, "No new number entered!", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "No number entered!", Toast.LENGTH_SHORT).show();
            } catch (NoSuchPaymentTypeException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @param paymentType type of payment(hourly, daily, monthly, yearly)
     * get and store the value from ui to specified payment type
     */
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

    @SuppressLint("DefaultLocale")
    private void printNewValuesToUserInterface() {
        hourlyPayment.setText(String.format("%.2f", payments.get(HOURLY)));
        dailyPayment.setText(String.format("%.2f", payments.get(DAILY)));
        monthlyPayment.setText(String.format("%.2f", payments.get(MONTHLY)));
        yearlyPayment.setText(String.format("%.2f", payments.get(YEARLY)));

        hourlyPayment.clearFocus();
        dailyPayment.clearFocus();
        monthlyPayment.clearFocus();
        yearlyPayment.clearFocus();
    }
}