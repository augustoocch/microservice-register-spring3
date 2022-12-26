package com.universityW3.controller;

import com.universityW3.model.Order;
import com.universityW3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/find-order/{order}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Order> findOrder(
            @PathVariable(name = "order", required = true) long orderId) {
        if (orderService.findOrder(orderId) != null) {
            Optional <Order> order = orderService.findOrder(orderId);
            return new ResponseEntity("Order: " + order + " found", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Order not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/new-order")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Order> newOrder(@RequestBody Order order) {
        if (orderService.findOrder(order.getId()) != null) {
            return new ResponseEntity("Order: " + order + " already exist", HttpStatus.ACCEPTED);
        } else {
            orderService.createOrder(order);
            return new ResponseEntity("Order created properly with id: " + order.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/update-order")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        if (orderService.findOrder(order.getId()) != null) {
            Optional <Order> newOrder = orderService.findOrder(order.getId());
            orderService.updateOrder(order);
            return new ResponseEntity("Order: " + newOrder + " properly updated", HttpStatus.ACCEPTED);
        }
        if (orderService.findOrder(order.getId()) == null) {
            return new ResponseEntity("Order doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Order couldn't be updated", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/delete-order")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Order> deleteOrder(@RequestBody Order order) {
        if (orderService.findOrder(order.getId()) != null) {
            Optional <Order> newOrder = orderService.findOrder(order.getId());
            orderService.deleteOrder(order);
            return new ResponseEntity("Order: " + order + " properly deleted", HttpStatus.ACCEPTED);
        }
        if (orderService.findOrder(order.getId()) == null) {
            return new ResponseEntity("Order doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Order couldn't be deleted", HttpStatus.BAD_REQUEST);
        }
    }



}


