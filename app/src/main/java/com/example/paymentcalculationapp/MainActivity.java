package com.example.paymentcalculationapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

        hourlyPayment.addTextChangedListener(new TextWatcher() {
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
                    hourlyPayment.setText("");
                    Toast.makeText(MainActivity.this, "Please don't enter 0 as first number!", Toast.LENGTH_SHORT).show();
                }
                String withSeparators = hourlyPayment.getText().toString();
                String withoutSeparators = removeDecimalSeparators(withSeparators);
                if (!placeDecimalSeparators(withoutSeparators).equals(withSeparators)) {
                    hourlyPayment.setText(placeDecimalSeparators(withoutSeparators));
                    hourlyPayment.setSelection(hourlyPayment.getText().length());
                }
            }
        });


        dailyPayment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                paymentType = PaymentType.DAILY;
                dailyPayment.setText("");
            }
        });

        dailyPayment.addTextChangedListener(new TextWatcher() {
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
                    dailyPayment.setText("");
                    Toast.makeText(MainActivity.this, "Please don't enter 0 as first number!", Toast.LENGTH_SHORT).show();
                }
                String withSeparators = dailyPayment.getText().toString();
                String withoutSeparators = removeDecimalSeparators(withSeparators);
                if (!placeDecimalSeparators(withoutSeparators).equals(withSeparators)) {
                    dailyPayment.setText(placeDecimalSeparators(withoutSeparators));
                    dailyPayment.setSelection(dailyPayment.getText().length());
                }
            }
        });

        monthlyPayment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                paymentType = PaymentType.MONTHLY;
                monthlyPayment.setText("");
            }
        });

        monthlyPayment.addTextChangedListener(new TextWatcher() {
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
                    monthlyPayment.setText("");
                    Toast.makeText(MainActivity.this, "Please don't enter 0 as first number!", Toast.LENGTH_SHORT).show();
                }
                String withSeparators = monthlyPayment.getText().toString();
                String withoutSeparators = removeDecimalSeparators(withSeparators);
                if (!placeDecimalSeparators(withoutSeparators).equals(withSeparators)) {
                    monthlyPayment.setText(placeDecimalSeparators(withoutSeparators));
                    monthlyPayment.setSelection(monthlyPayment.getText().length());
                }
            }
        });

        yearlyPayment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                paymentType = PaymentType.YEARLY;
                yearlyPayment.setText("");
            }
        });

        yearlyPayment.addTextChangedListener(new TextWatcher() {
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
                    yearlyPayment.setText("");
                    Toast.makeText(MainActivity.this, "Please don't enter 0 as first number!", Toast.LENGTH_SHORT).show();
                }
                String withSeparators = yearlyPayment.getText().toString();
                String withoutSeparators = removeDecimalSeparators(withSeparators);
                if (!placeDecimalSeparators(withoutSeparators).equals(withSeparators)) {
                    yearlyPayment.setText(placeDecimalSeparators(withoutSeparators));
                    yearlyPayment.setSelection(yearlyPayment.getText().length());
                }
            }
        });

        // setup calculate button behavior when clicked
        calculateButton.setOnClickListener(v -> {
            try {
                readUserInputByPaymentType(paymentType);
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
     *                    get and store the value from ui to specified payment type
     */
    private void readUserInputByPaymentType(PaymentType paymentType) {
        switch (paymentType) {
            case HOURLY:
                paymentAmount = Double.parseDouble(removeDecimalSeparators(hourlyPayment.getText().toString()));
                break;
            case DAILY:
                paymentAmount = Double.parseDouble(removeDecimalSeparators(dailyPayment.getText().toString()));
                break;
            case MONTHLY:
                paymentAmount = Double.parseDouble(removeDecimalSeparators(monthlyPayment.getText().toString()));
                break;
            case YEARLY:
                paymentAmount = Double.parseDouble(removeDecimalSeparators(yearlyPayment.getText().toString()));
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

    private String placeDecimalSeparators(String inputStringNumber) {
        String reversedString = new StringBuilder(inputStringNumber).reverse().toString();
        StringBuilder stringNumberWithDecimalPoints = new StringBuilder();
        int counter = 1;
        for (int i = 0; i < reversedString.length(); i++) {
            stringNumberWithDecimalPoints.append(reversedString.charAt(i));
            if (counter % 3 == 0 && i != reversedString.length() - 1)
                stringNumberWithDecimalPoints.append(",");
            if (reversedString.charAt(i) == '.') {
            }
            counter++;
        }
        return stringNumberWithDecimalPoints.reverse().toString();
    }

    private String removeDecimalSeparators(String inputStringNumberWithDecimalSeparators) {
        return inputStringNumberWithDecimalSeparators.replaceAll(",", "");
    }
}