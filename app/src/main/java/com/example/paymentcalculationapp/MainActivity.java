package com.example.paymentcalculationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String selected;
    Double hourly;
    Double daily;
    Double monthly;
    Double yearly;

    private EditText hourlyPayment;
    private EditText dailyPayment;
    private EditText monthlyPayment;
    private EditText yearlyPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hourlyPayment = findViewById(R.id.hourlyPayment);
        dailyPayment = findViewById(R.id.dailyPayment);
        monthlyPayment = findViewById(R.id.monthlyPayment);
        yearlyPayment = findViewById(R.id.yearlyPayment);

        hourlyPayment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    selected = "h";
                    hourlyPayment.setText("");
                }
            }
        });

        dailyPayment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    selected = "d";
                    dailyPayment.setText("");
                }
            }
        });

        monthlyPayment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    selected = "m";
                    monthlyPayment.setText("");
                }
            }
        });

        yearlyPayment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    selected = "y";
                    yearlyPayment.setText("");
                }
            }
        });
    }

    public void calculatePayment(View view) {
        switch (selected) {
            case "h":
                hourly = Double.parseDouble(hourlyPayment.getText().toString());
                daily = hourly * 8;
                monthly = daily * 21;
                yearly = monthly * 12;
                break;
            case "d":
                daily = Double.parseDouble(dailyPayment.getText().toString());
                hourly = daily / 8;
                monthly = daily * 21;
                yearly = monthly * 12;
                break;
            case "m":
                monthly = Double.parseDouble(monthlyPayment.getText().toString());
                daily = monthly / 21;
                hourly = daily / 8;
                yearly = monthly * 12;
                break;
            case "y":
                yearly = Double.parseDouble(yearlyPayment.getText().toString());
                monthly = yearly / 12;
                daily = monthly / 21;
                hourly = daily / 8;
                break;
            default:
                break;
        }

        hourlyPayment.setText(hourly.toString() + " Ft");
        dailyPayment.setText(daily.toString() + " Ft");
        monthlyPayment.setText(monthly.toString() + " Ft");
        yearlyPayment.setText(yearly.toString() + " Ft");

        hourlyPayment.clearFocus();
        dailyPayment.clearFocus();
        monthlyPayment.clearFocus();
        yearlyPayment.clearFocus();
    }
}