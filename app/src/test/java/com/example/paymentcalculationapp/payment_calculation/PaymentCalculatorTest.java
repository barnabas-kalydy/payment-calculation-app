package com.example.paymentcalculationapp.payment_calculation;

import com.example.paymentcalculationapp.exception.NoSuchPaymentTypeException;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCalculatorTest {

    @Test
    public void testCalculateFromHourlyPaymentWithValidInput() throws NoSuchPaymentTypeException {
        final Map<PaymentType, Double> testPayments = PaymentCalculator.calculateOtherPayments(PaymentType.HOURLY, 1000.0);
        assertEquals(testPayments.get(PaymentType.DAILY), new Double(8000.0));
        assertEquals(testPayments.get(PaymentType.MONTHLY), new Double(168000.0));
        assertEquals(testPayments.get(PaymentType.YEARLY), new Double(2016000.0));
    }

    @Test
    public void testCalculateFromDailyPaymentWithValidInput() throws NoSuchPaymentTypeException {
        final Map<PaymentType, Double> testPayments = PaymentCalculator.calculateOtherPayments(PaymentType.DAILY, 8000.0);
        assertEquals(testPayments.get(PaymentType.HOURLY), new Double(1000.0));
        assertEquals(testPayments.get(PaymentType.MONTHLY),new Double(168000.0));
        assertEquals(testPayments.get(PaymentType.YEARLY), new Double(2016000.0));
    }

    @Test
    public void testCalculateFromMonthlyPaymentWithValidInput() throws NoSuchPaymentTypeException {
        final Map<PaymentType, Double> testPayments = PaymentCalculator.calculateOtherPayments(PaymentType.MONTHLY, 168000.0);
        assertEquals(testPayments.get(PaymentType.DAILY), new Double(8000.0));
        assertEquals(testPayments.get(PaymentType.HOURLY), new Double(1000.0));
        assertEquals(testPayments.get(PaymentType.YEARLY), new Double(2016000.0));
    }

    @Test
    public void testCalculateFromYearlyPaymentWithValidInput() throws NoSuchPaymentTypeException {
        final Map<PaymentType, Double> testPayments = PaymentCalculator.calculateOtherPayments(PaymentType.YEARLY, 2016000.0);
        assertEquals(testPayments.get(PaymentType.DAILY), new Double(8000.0));
        assertEquals(testPayments.get(PaymentType.MONTHLY), new Double(168000.0));
        assertEquals(testPayments.get(PaymentType.HOURLY), new Double(1000.0));
    }



}