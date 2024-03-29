package com.javatechie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.dto.Order;
import com.javatechie.service.OrderFulfillmentService;

@RestController
@RequestMapping("/orders")
public class OrderFulfillmentController {

    @Autowired
    private OrderFulfillmentService service; // Autowiring the OrderFulfillmentService bean

    // Endpoint to process an order
    @PostMapping
    public ResponseEntity<Order> processOrder(@RequestBody Order order) throws InterruptedException {
        service.processOrder(order); // Process the order synchronously

        // Initiate asynchronous tasks for order fulfillment steps
        service.notifyUser(order); // Notify the user asynchronously
        service.assignVendor(order); // Assign a vendor asynchronously
        service.packaging(order); // Packaging the order asynchronously
        service.assignDeliveryPartner(order); // Assign a delivery partner asynchronously
        service.assignTrailerAndDispatch(order); // Assign a trailer and dispatch asynchronously

        // Respond with a successful HTTP status and the processed order
        return ResponseEntity.ok(order);
    }
}
