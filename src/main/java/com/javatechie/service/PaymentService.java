package com.javatechie.service;

import org.springframework.stereotype.Service;

import com.javatechie.dto.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

    // Method to process payment for an order
    public void processPayment(Order order) throws InterruptedException {
        // Log initiation of payment for the order
        log.info("Initiating payment for order " + order.getProductId());

        // Simulate payment processing delay
        Thread.sleep(2000L);

        // Log completion of payment for the order
        log.info("Completed payment for order " + order.getProductId());
    }
}
