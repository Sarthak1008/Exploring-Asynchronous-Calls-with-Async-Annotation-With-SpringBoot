package com.javatechie.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.javatechie.dto.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderFulfillmentService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    /* All Required process */
    /*
     * 1. Inventory service check order availability
     * 2. Process payment for order
     * 3. Notify to the user
     * 3. Assign to vendor
     * 4. packaging
     * 5. assign delivery partner
     * 6. assign trailer
     * 7. dispatch product
     **/

    // Method to process an order including inventory check and payment processing
    public Order processOrder(Order order) throws InterruptedException {
        // Generate a unique tracking ID for the order
        order.setTrackingId(UUID.randomUUID().toString());
        // Check product availability in inventory
        if (inventoryService.checkProductAvailability(order.getProductId())) {
            // Process payment for the order
            paymentService.processPayment(order);
        } else {
            throw new RuntimeException("Technical issue please retry");
        }
        return order;
    }

    // Asynchronous method to notify the user about the order
    @Async("asyncTaskExecutor")
    public void notifyUser(Order order) throws InterruptedException {
        Thread.sleep(4000L); // Simulate notification delay
        log.info("Notified to the user " + Thread.currentThread().getName());
    }

    // Asynchronous method to assign the order to a vendor
    @Async("asyncTaskExecutor")
    public void assignVendor(Order order) throws InterruptedException {
        Thread.sleep(5000L); // Simulate assignment delay
        log.info("Assign order to vendor " + Thread.currentThread().getName());
    }

    // Asynchronous method to package the order
    @Async("asyncTaskExecutor")
    public void packaging(Order order) throws InterruptedException {
        Thread.sleep(2000L); // Simulate packaging delay
        log.info("Order packaging completed " + Thread.currentThread().getName());
    }

    // Asynchronous method to assign a delivery partner for the order
    @Async("asyncTaskExecutor")
    public void assignDeliveryPartner(Order order) throws InterruptedException {
        Thread.sleep(10000L); // Simulate delivery partner assignment delay
        log.info("Delivery partner assigned " + Thread.currentThread().getName());
    }

    // Asynchronous method to assign a trailer and dispatch the order
    @Async("asyncTaskExecutor")
    public void assignTrailerAndDispatch(Order order) throws InterruptedException {
        Thread.sleep(3000L); // Simulate trailer assignment and dispatch delay
        log.info("Trailer assigned and Order dispatched " + Thread.currentThread().getName());
    }
}
