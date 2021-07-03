package com.example.paymentcalculationapp.payment_calculation;

import com.example.paymentcalculationapp.exception.NoSuchPaymentTypeException;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Map;

public class PaymentCalculatorTest extends TestCase {

    public void testCalculateFromHourlyPaymentWithValidInput() throws NoSuchPaymentTypeException {
        final Map<PaymentType, Double> testPayments = PaymentCalculator.calculateOtherPayments(PaymentType.HOURLY, 1000.0);
        assertEquals(testPayments.get(PaymentType.DAILY), 8000.0);
        assertEquals(testPayments.get(PaymentType.MONTHLY), 168000.0);
        assertEquals(testPayments.get(PaymentType.YEARLY), 2016000.0);
    }

    public void testCalculateFromDailyPaymentWithValidInput() throws NoSuchPaymentTypeException {
        final Map<PaymentType, Double> testPayments = PaymentCalculator.calculateOtherPayments(PaymentType.DAILY, 8000.0);
        assertEquals(testPayments.get(PaymentType.HOURLY), 1000.0);
        assertEquals(testPayments.get(PaymentType.MONTHLY), 168000.0);
        assertEquals(testPayments.get(PaymentType.YEARLY), 2016000.0);
    }

    public void testCalculateFromMonthlyPaymentWithValidInput() throws NoSuchPaymentTypeException {
        final Map<PaymentType, Double> testPayments = PaymentCalculator.calculateOtherPayments(PaymentType.MONTHLY, 168000.0);
        assertEquals(testPayments.get(PaymentType.DAILY), 8000.0);
        assertEquals(testPayments.get(PaymentType.HOURLY), 1000.0);
        assertEquals(testPayments.get(PaymentType.YEARLY), 2016000.0);
    }

    public void testCalculateFromYearlyPaymentWithValidInput() throws NoSuchPaymentTypeException {
        final Map<PaymentType, Double> testPayments = PaymentCalculator.calculateOtherPayments(PaymentType.YEARLY, 2016000.0);
        assertEquals(testPayments.get(PaymentType.DAILY), 8000.0);
        assertEquals(testPayments.get(PaymentType.MONTHLY), 168000.0);
        assertEquals(testPayments.get(PaymentType.HOURLY), 1000.0);
    }



}