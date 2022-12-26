package com.universityW3.controller;

import com.universityW3.model.Orders;
import com.universityW3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@RestController
public class OrderController {

    @Autowired
    OrderService ordersService;

    @GetMapping(value = "/find-orders/{orders}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Orders> findOrder(@PathVariable(name = "orders", required = true) Long ordersId) {
        if (ordersService.findOrder(ordersId) != null) {
            Orders orders = ordersService.findOrder(ordersId);
            return new ResponseEntity("Order: " + orders + " found", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Order not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/new-orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Orders> newOrder(@RequestBody Orders orders) {
        if (ordersService.findOrder(orders.getId()) != null) {
            return new ResponseEntity("Order: " + orders + " already exist", HttpStatus.ACCEPTED);
        } else {
            ordersService.createOrder(orders);
            return new ResponseEntity("Order created properly with id: " + orders.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/update-orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders orders) {
        if (ordersService.findOrder(orders.getId()) != null) {
            Orders newOrder = ordersService.findOrder(orders.getId());
            ordersService.updateOrder(orders);
            return new ResponseEntity("Order: " + newOrder + " properly updated", HttpStatus.ACCEPTED);
        }
        if (ordersService.findOrder(orders.getId()) == null) {
            return new ResponseEntity("Order doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Order couldn't be updated", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/delete-orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Orders> deleteOrder(@RequestBody Orders orders) {
        if (ordersService.findOrder(orders.getId()) != null) {
            Orders newOrder = ordersService.findOrder(orders.getId());
            ordersService.deleteOrder(orders);
            return new ResponseEntity("Order: " + orders + " properly deleted", HttpStatus.ACCEPTED);
        }
        if (ordersService.findOrder(orders.getId()) == null) {
            return new ResponseEntity("Order doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Order couldn't be deleted", HttpStatus.BAD_REQUEST);
        }
    }



}


