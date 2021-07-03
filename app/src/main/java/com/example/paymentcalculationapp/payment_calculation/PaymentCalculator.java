package com.example.paymentcalculationapp.payment_calculation;

import com.example.paymentcalculationapp.exception.NoSuchPaymentTypeException;

import java.util.HashMap;
import java.util.Map;

import static com.example.paymentcalculationapp.payment_calculation.PaymentType.DAILY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.HOURLY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.MONTHLY;
import static com.example.paymentcalculationapp.payment_calculation.PaymentType.YEARLY;

/**
 * This is a singleton class that calculates any type(hourly, daily, monthly, yearly) and amount to every
 * other type.
 */
public class PaymentCalculator {

    /**
     * private constructor to don't let the class to be instantiated
     */
    private PaymentCalculator() {

    }

    public static Map<PaymentType, Double> calculateOtherPayments(PaymentType typeToCalculateFrom, Double amount)
            throws NoSuchPaymentTypeException {
        Map<PaymentType, Double> calculatedPayments = new HashMap<>();
        calculatedPayments.put(typeToCalculateFrom, amount);
        int WORKING_DAYS_PER_MONTH = 21;
        int WORKING_HOURS_PER_DAY = 8;
        int MONTHS_PER_YEAR = 12;
        switch (typeToCalculateFrom) {
            case HOURLY:
                calculatedPayments.put(DAILY, calculatedPayments.get(HOURLY) * WORKING_HOURS_PER_DAY);
                calculatedPayments.put(MONTHLY, calculatedPayments.get(DAILY) * WORKING_DAYS_PER_MONTH);
                calculatedPayments.put(YEARLY, calculatedPayments.get(MONTHLY) * MONTHS_PER_YEAR);
                break;
            case DAILY:
                calculatedPayments.put(HOURLY, calculatedPayments.get(DAILY) / WORKING_HOURS_PER_DAY);
                calculatedPayments.put(MONTHLY, calculatedPayments.get(DAILY) * WORKING_DAYS_PER_MONTH);
                calculatedPayments.put(YEARLY, calculatedPayments.get(MONTHLY) * MONTHS_PER_YEAR);
                break;
            case MONTHLY:
                calculatedPayments.put(DAILY, calculatedPayments.get(MONTHLY) / WORKING_DAYS_PER_MONTH);
                calculatedPayments.put(HOURLY, calculatedPayments.get(DAILY) / WORKING_HOURS_PER_DAY);
                calculatedPayments.put(YEARLY, calculatedPayments.get(MONTHLY) * MONTHS_PER_YEAR);
                break;
            case YEARLY:
                calculatedPayments.put(MONTHLY, calculatedPayments.get(YEARLY) / MONTHS_PER_YEAR);
                calculatedPayments.put(DAILY, calculatedPayments.get(MONTHLY) / WORKING_DAYS_PER_MONTH);
                calculatedPayments.put(HOURLY, calculatedPayments.get(DAILY) / WORKING_HOURS_PER_DAY);
                break;
            default:
                throw new NoSuchPaymentTypeException();
        }
        return calculatedPayments;
    }
}
